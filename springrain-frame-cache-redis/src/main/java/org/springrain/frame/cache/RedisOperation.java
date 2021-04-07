package org.springrain.frame.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redisson的 redis操作,包含lock和queue
 *
 * @author caomei
 */

@Component("redisOperation")
public class RedisOperation {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisTemplate redisTemplate;

    //远程Service默认的工作并发
    private int remoteServiceWorkersAmount = 1000;

    private int queueCapacity = 1000;

    //是否接收队列,默认是true,如果是false就不再接受消息,用于tomcat重启之前,保证tomcat不再接受队列
    private boolean receiveQueue = false;


    /**
     * 根据Key 和超时时间加锁
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean lock(String key, long expire) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {

            Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, System.currentTimeMillis(), expire, TimeUnit.MILLISECONDS);
            return lock;
        } catch (Exception e) {
            logger.error("locking error", e);
            return false;
        }


    }

    /**
     * 根据Key解锁
     *
     * @param key
     */
    public void unlock(String key) {

        if (StringUtils.isBlank(key)) {
            return;
        }
        try {
            redisTemplate.delete(key);

        } catch (Throwable e) {
            logger.error("unlock error", e);
        }
    }



    public Long getAtomicLong(String name) {
        Long increment = redisTemplate.opsForValue().increment(name);
        return increment;


    }

    public Long getAtomicLong(String name, Long initValue) {
        Long increment = redisTemplate.opsForValue().increment(name, initValue);
        return increment;

    }



}
