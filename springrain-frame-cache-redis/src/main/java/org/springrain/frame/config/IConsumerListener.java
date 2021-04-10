package org.springrain.frame.config;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springrain.frame.cache.RedisOperation;
import org.springrain.frame.util.SpringUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * 消费者的监听器
 * @param <T> 需要接收数据的对象,例如 User
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
    default int getBatchSize() {
        return 100;
    };

    /**
     * 消费者的名称
     * @return
     */
     String getConsumerName();

    /**
     * group的名称,如果为空,默认是 getQueueName()+"_defaultGroupName"
     * @return
     */
    default String getGroupName(){
        return getQueueName()+"_defaultGroupName";
    };

    /**
     * 指定监听器的线程池
     * @return
     */
    default Executor getExecutor(){
        return null;
    }



    @PostConstruct
    default void registerConsumerListener(){
        RedisOperation redisOperation= (RedisOperation) SpringUtils.getBean("redisOperation");
        redisOperation.receiveAutoAckConsumerListener(this);

    }




}
