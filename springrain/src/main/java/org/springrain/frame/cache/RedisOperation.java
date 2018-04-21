package org.springrain.frame.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springrain.frame.common.BaseLogger;

/**
 * 基于 Redisson的 redis操作,包含lock和queue
 * @author caomei
 *
 */

public class RedisOperation extends BaseLogger {
    
    private RedissonClient redissonClient;
    
    private int queueCapacity=1000;
    
    //是否接收队列,默认是true,如果是false就不再接受消息,用于tomcat重启之前,保证tomcat不再接受队列
    private boolean receiveQueue=true;
    
    
    /**
     * 根据Key 和超时时间加锁
     * @param key
     * @param expire
     * @return
     */
    public boolean lock(String key,long expire){
        if(StringUtils.isBlank(key)) {
            return false;
        }
    
        try {
            RLock rLock = redissonClient.getLock(key+"_lock");
            //不做任何等待,抢不到就返回false
            if(rLock.tryLock(-1,expire, TimeUnit.MILLISECONDS)) {
                return true;
            }
            return false;
        } catch (InterruptedException e) {
            logger.error("locking error",e);
            return false;
        }
      
        
    }
    
    /**
     * 根据Key解锁
     * @param key
     */
    public  void unlock(String key) {
        
        if(StringUtils.isBlank(key)) {
            return;
        }
        try {
            RLock rLock = redissonClient.getLock(key+"_lock");
            if(rLock.isLocked()){
                rLock.unlock();
            }
        } catch (Throwable e) {
            logger.error("unlock error",e);
        }
    }
    

    
    
    public  <T> BlockingQueue<T> getBlockingQueue(String queueName ,Class<T> clazz) {
        RBlockingQueue<T> queue = redissonClient.getBlockingQueue(queueName);
        return queue;
    }
    
    public RAtomicLong getAtomicLong(String name){
        RAtomicLong atomicLong = redissonClient.getAtomicLong(name);
        return atomicLong;
        
    }
    
    public RAtomicLong getAtomicLong(String name,Long initValue){
        RAtomicLong atomicLong = redissonClient.getAtomicLong(name);
        atomicLong.set(initValue);
        return atomicLong;
        
    }

    public boolean getReceiveQueue() {
        return receiveQueue;
    }

    public void setReceiveQueue(boolean receiveQueue) {
        this.receiveQueue = receiveQueue;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    
    
    
    

}
