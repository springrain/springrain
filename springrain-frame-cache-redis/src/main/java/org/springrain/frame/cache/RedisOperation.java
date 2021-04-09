package org.springrain.frame.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springrain.frame.config.IConsumerListener;
import org.springrain.frame.util.ClassUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redisson的 redis操作,包含lock和自增,基于stream的mq <br>
 * 要求:redis 5.0+ ,springBoot 2.3+
 *
 * @author springrain
 */

@Component("redisOperation")
public class RedisOperation {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 根据Key 和超时时间加锁
     *
     * @param key         锁的key
     * @param expireMilli 超时时间毫秒数
     * @return
     */
    public boolean lock(String key, long expireMilli) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {
            Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, System.currentTimeMillis() + expireMilli, expireMilli, TimeUnit.MILLISECONDS);
            return lock;
        } catch (Exception e) {
            logger.error("locking error", e);
            return false;
        }


    }

    /**
     * 根据Key解锁
     *
     * @param key 锁的key
     */
    public boolean unlock(String key) {

        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            redisTemplate.delete(key);
            return true;

        } catch (Throwable e) {
            logger.error("unlock error", e);
            return false;
        }
    }


    /**
     * 原子自增
     *
     * @param name 自增的名称
     * @return
     */
    public Long getAtomicLong(String name) {
        Long increment = redisTemplate.opsForValue().increment(name);
        return increment;
    }

    /**
     * 原子自增
     *
     * @param name      自增的名称
     * @param initValue 自增的初始值
     * @return
     */
    public Long getAtomicLong(String name, Long initValue) {
        Long increment = redisTemplate.opsForValue().increment(name, initValue);
        return increment;

    }


    /**
     * 注册自动ACK的消费者监听器
     *
     * @param redisStreamConsumerListener
     * @param <T>
     */
    public <T> void receiveAutoAckConsumerListener(IConsumerListener<T> redisStreamConsumerListener){

        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, T>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                        .batchSize(redisStreamConsumerListener.getBatchSize()) //一批次拉取的最大count数
                        .executor(redisStreamConsumerListener.getExecutor())  //线程池
                        .pollTimeout(Duration.ZERO) //阻塞式轮询
                        .targetType(ClassUtils.getActualTypeArgument(redisStreamConsumerListener.getClass())) //目标类型(消息内容的类型)
                        .build();
        StreamMessageListenerContainer<String, ObjectRecord<String, T>> container = StreamMessageListenerContainer.create(redisConnectionFactory, options);
        prepareChannelAndGroup(redisTemplate.opsForStream(), redisStreamConsumerListener.getQueueName(), redisStreamConsumerListener.getGroupName());
        container.receiveAutoAck(Consumer.from(redisStreamConsumerListener.getGroupName(), redisStreamConsumerListener.getConsumerName()), StreamOffset.create(redisStreamConsumerListener.getQueueName(), ReadOffset.lastConsumed()), redisStreamConsumerListener);
        container.start();

    }

    /**
     * 生产者消息队列发送消息
     * @param queueName
     * @param message
     * @return
     */
    public  String  sendProducerMessage(String queueName,Object message){
        ObjectRecord record = Record.of(message).withStreamKey(queueName);
        RecordId recordId = redisTemplate.opsForStream().add(record);
        return  recordId.getValue();
    }



    /**
     * 在初始化容器时,如果key对应的stream或者group不存在时会抛出异常,所以我们需要提前检查并且初始化.
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

}
