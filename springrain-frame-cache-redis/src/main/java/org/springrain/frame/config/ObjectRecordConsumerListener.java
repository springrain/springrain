package org.springrain.frame.config;

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
import java.time.Duration;
import java.util.concurrent.Executor;

public abstract class ObjectRecordConsumerListener<T> implements StreamListener<String, ObjectRecord<String, T>> {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

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
        return null;
    }


    @Override
    public  void onMessage(ObjectRecord<String, T> message){
       RecordId recordId= message.getId();
       String messageId=recordId.getValue();
       Long messageTime=recordId.getTimestamp();
       String queueName=message.getStream();
       T value=message.getValue();

        onMessage(value,queueName,messageId,messageTime);
    }


    public abstract void onMessage( T value,String queueName,String messageId,Long messageTime);

    @PostConstruct
    private void registerConsumerListener() {
        Class<T> t = ClassUtils.getActualTypeArgument(getClass());
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, T>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                        .batchSize(getBatchSize()) //一批次拉取的最大count数
                        //.executor(getExecutor())  //线程池
                        .pollTimeout(Duration.ZERO) //阻塞式轮询
                        //设置默认的序列化器,要和 redisTemplate 保持一致!!!!!!!!!!!!!!!!!!!!!
                        //默认 targetType 会设置序列化器是  RedisSerializer.byteArray,这里手动初始化objectMapper,并设置序列化器.
                        .objectMapper(ObjectHashMapper.getSharedInstance())
                        .keySerializer(RedisCacheConfig.stringRedisSerializer)
                        .hashKeySerializer(RedisCacheConfig.stringRedisSerializer)
                        .hashValueSerializer(RedisCacheConfig.fstSerializer)
                        //.serializer(RedisCacheConfig.fstSerializer)


                        .targetType(t) //目标类型(消息内容的类型),如果objectMapper为空,会设置默认的ObjectHashMapper
                        .build();
        StreamMessageListenerContainer<String, ObjectRecord<String, T>> container = StreamMessageListenerContainer.create(redisConnectionFactory, options);
        prepareChannelAndGroup(redisTemplate.opsForStream(), getQueueName(), getGroupName());
        container.receiveAutoAck(Consumer.from(getGroupName(), getConsumerName()), StreamOffset.create(getQueueName(), ReadOffset.lastConsumed()), this);
        container.start();
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
                status = ops.createGroup(queueName, group);
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

}
