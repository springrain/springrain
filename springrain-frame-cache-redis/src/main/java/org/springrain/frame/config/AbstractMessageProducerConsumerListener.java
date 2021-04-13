package org.springrain.frame.config;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.util.Assert;
import org.springrain.frame.util.ClassUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 因为接口不能注入springBean,使用抽象类实现,主要用于隔离了Redis Stream API,方便后期更换MQ的实现.
 * 如果未确认消息消费,Redis Stream 暂时没有重试的API,使用 retryFailMessage() 启动重试,业务代码可以自行调度retryFailMessage()方法
 * @param <T> 需要放入队列的对象
 */
public abstract class AbstractMessageProducerConsumerListener<T> implements StreamListener<String, ObjectRecord<String, T>>, Closeable {

    //默认的线程池
    private static final Executor excutor = new ThreadPoolExecutor(1000, 1000,
            10L, TimeUnit.SECONDS,
            //使用一个基于FIFO排序的阻塞队列，在所有corePoolSize线程都忙时新任务将在队列中等待
            new LinkedBlockingQueue<Runnable>());

    //泛型的类型
    private final Class<T> clazz = ClassUtils.getActualTypeArgument(getClass());


    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    //监听的容器
    private StreamMessageListenerContainer<String, ObjectRecord<String, T>> container = null;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 消息队列的名称,redis里就是stream的名称
     *
     * @return
     */
    public abstract String getQueueName();

    /**
     * 批量消费的数量
     *
     * @return
     */
    public int getBatchSize() {
        return 100;
    }

    /**
     * 消费者的名称
     *
     * @return
     */
    public abstract String getConsumerName();

    /**
     * group的名称,如果为空,默认是 getQueueName()+"_defaultGroupName"
     *
     * @return
     */
    public String getGroupName() {
        return getQueueName() + "_defaultGroupName";
    }

    /**
     * 指定监听器的线程池
     *
     * @return
     */
    public Executor getExecutor() {
        return excutor;
    }


    @Override
    public  void onMessage(ObjectRecord<String, T> message) {
        RecordId recordId = isMessageSuccess(message);
        if (recordId != null) {
            //消息确认ack
            redisTemplate.opsForStream().acknowledge(getQueueName(), getGroupName(), recordId);
        }

    }


    /**
     * 消费消息,隔离Redis API,如果返回true则自动应答,如果返回false,认为消息处理失败
     *
     * @param value
     * @param queueName
     * @param messageId
     * @param messageTime
     * @return
     */
    public abstract boolean onMessage(T value, String queueName, String messageId, Long messageTime);

    @PostConstruct
    private void registerConsumerListener() {

        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, T>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                        .batchSize(getBatchSize()) //一批次拉取的最大count数
                        .executor(getExecutor())  //线程池
                        .pollTimeout(Duration.ZERO) //阻塞式轮询
                        //设置默认的序列化器,要和 redisTemplate 保持一致!!!!!!!!!!!!!!!!!!!!!
                        //默认 targetType 会设置序列化器是  RedisSerializer.byteArray,这里手动初始化objectMapper,并设置序列化器.
                        .objectMapper(ObjectHashMapper.getSharedInstance())
                        .keySerializer(RedisCacheConfig.stringRedisSerializer)
                        .hashKeySerializer(RedisCacheConfig.stringRedisSerializer)
                        .hashValueSerializer(RedisCacheConfig.fstSerializer)
                        //.serializer(RedisCacheConfig.fstSerializer)
                        .targetType(clazz) //目标类型(消息内容的类型),如果objectMapper为空,会设置默认的ObjectHashMapper
                        .build();
        container = StreamMessageListenerContainer.create(redisConnectionFactory, options);
        prepareChannelAndGroup(redisTemplate.opsForStream(), getQueueName(), getGroupName());

        // 通过xread命令也就是非消费者组模式直接读取,或者使用xreadgroup命令在消费者组中命令一个消费者去消费一条记录,
        // 我们可以通过0、>、$分别表示第一条记录、最后一次未被消费的记录和最新一条记录,
        // 比如创建消费者组时不能使用>表示最后一次未被消费的记录,比如0表示从第一条开始并且包括第一条,
        // $表示从最新一条开始但并不是指当前Stream的最后一条记录,是表示下一个xadd添加的那一条记录,所以说$在非消费者组模式的阻塞读取下才有意义!


        Consumer consumer = Consumer.from(getGroupName(), getConsumerName());

        // 需要手动回复应答 ACK
        // container.receive(consumer, StreamOffset.fromStart(getQueueName()), this);
        // container.receive(consumer, StreamOffset.create(getQueueName(),ReadOffset.latest()), this);
        container.receive(consumer, StreamOffset.create(getQueueName(), ReadOffset.lastConsumed()), this);
        container.start();

        //重试失败的消息
        retryFailMessage();


    }


    /**
     * 重试消息,启动的时候会重试一次,业务代码自行实现根据调度重试
     * 避免死循环,最多1000次.如果单次返回的所有消息都是异常的,退出循环
     */
    public void retryFailMessage() {
        Consumer consumer = Consumer.from(getGroupName(), getConsumerName());
        //设置配置
        StreamReadOptions streamReadOptions = StreamReadOptions.empty().count(getBatchSize()).block(Duration.ofSeconds(5));
        List<ObjectRecord<String, T>> retryFailMessageList = new ArrayList<>();
        //避免死循环,最多1000次.如果单次返回的所有消息都是异常的,退出循环
        for (int i = 0; i < 1000; i++) {
            List<ObjectRecord<String, T>> readList = redisTemplate.opsForStream().read(clazz, consumer, streamReadOptions, StreamOffset.fromStart(getQueueName()));
            //如果已经没有异常的消息,退出循环
            if (CollectionUtils.isEmpty(readList)) {
                break;
            }
            //如果返回的消息全部都是异常的,退出循环
            if (retryFailMessageList.containsAll(readList)) {
                break;
            }

            // 遍历异常的消息
            for (ObjectRecord<String, T> message : readList) {
                RecordId recordId = isMessageSuccess(message);
                //处理成功
                if (recordId != null) {
                    //消息确认ack
                    redisTemplate.opsForStream().acknowledge(getQueueName(), getGroupName(), recordId);
                } else {//处理失败,记录下来
                    retryFailMessageList.add(message);
                }
            }
        }
    }


    /**
     * 在初始化容器时,如果key对应的stream或者group不存在时会抛出异常,所以我们需要提前检查并且初始化.
     *
     * @param ops
     * @param queueName
     * @param group
     */
    private void prepareChannelAndGroup(StreamOperations<String, ?, ?> ops, String queueName, String group) {
        String status = "OK";
        try {
            StreamInfo.XInfoGroups groups = ops.groups(queueName);
            if (groups.stream().noneMatch(xInfoGroup -> group.equals(xInfoGroup.groupName()))) {
                //status = ops.createGroup(queueName, group);
                status = ops.createGroup(queueName, ReadOffset.from("0-0"), group);
            }
        } catch (Exception exception) {
            RecordId initialRecord = ops.add(ObjectRecord.create(queueName, "Initial Record"));
            Assert.notNull(initialRecord, "Cannot initialize stream with key '" + queueName + "'");
            status = ops.createGroup(queueName, ReadOffset.from(initialRecord), group);
        } finally {
            Assert.isTrue("OK".equals(status), "Cannot create group with name '" + group + "'");
        }
    }


    /**
     * 生产者消息队列发送消息
     *
     * @param message
     * @return
     */
    public String sendProducerMessage(T message) {
        ObjectRecord<String, T> record = Record.of(message).withStreamKey(getQueueName());
        //StreamRecords.newRecord()
        //ObjectRecord record = Record.of(message).withStreamKey(queueName);
        RecordId recordId = redisTemplate.opsForStream().add(record);
        return recordId.getValue();
    }

    /**
     * 消息消费是否成功,成功返回RecordId,失败返回null
     *
     * @param message
     * @return
     */
    private RecordId isMessageSuccess(ObjectRecord<String, T> message) {
        RecordId recordId = message.getId();
        String messageId = recordId.getValue();
        Long messageTime = recordId.getTimestamp();
        String queueName = message.getStream();
        T value = message.getValue();

        boolean ok = onMessage(value, queueName, messageId, messageTime);
        if (ok) {
            return recordId;
        } else {
            return null;
        }
    }


    @Override
    public void close() throws IOException {
        if (container != null) {
            container.stop();
        }

    }

}
