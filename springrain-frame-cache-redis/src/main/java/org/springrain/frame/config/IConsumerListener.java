package org.springrain.frame.config;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;

import java.util.concurrent.Executor;

/**
 * 消费者的监听器
 * @param <T>
 */
public interface IConsumerListener<T> extends StreamListener<String, ObjectRecord<String, T>>  {


    /**
     * 消息队列的名称,redis里就是stream的名称
     * @return
     */
     String getQueueName();

    /**
     * 批量消费的数量
     * @return
     */
     int getBatchSize();

    /**
     * 消费者的名称
     * @return
     */
     String getConsumerName();

    /**
     * group的名称,如果为空,默认是 getTopicName()+"_defaultGroupName"
     * @return
     */
    default String getGroupName(){
        return getQueueName()+"_defaultGroupName";
    };

    /**
     * 指定监听器的线程池
     * @return
     */
    Executor getExecutor();







}
