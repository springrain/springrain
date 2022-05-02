package org.springrain.frame.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

import org.springrain.frame.mq.IMessageProducerConsumerListener;

/**
 * 基于 Redisson的 redis操作,包含lock和自增,基于stream的mq <br>
 * 要求:redis 5.0+ ,springBoot 2.3+
 *
 * @author springrain
 */

@Component("redisOperation")
public class RedisOperation {
    // 注册所有的队列,方便业务重试
    public static List<IMessageProducerConsumerListener> messageProducerConsumerListenerList = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(getClass());
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
     * Redis Sadd 命令将一个或多个成员元素加入到集合中,已经存在于集合的成员元素将被忽略.
     * 假如集合 key 不存在,则创建一个只包含添加的元素作成员的集合.
     *
     * @param setName
     * @param value
     * @return 影响的行数, 如果返回0 就说明已经存在,忽略了
     */
    public Long setsadd(String setName, Object value) {
        if (StringUtils.isBlank(setName) || value == null) {
            return null;
        }
        Long add = redisTemplate.opsForSet().add(setName, value);
        return add;
    }

    /**
     * Redis Spop 命令用于移除集合中的指定 key 的一个或多个随机元素,移除后会返回移除的元素.
     *
     * @param setName
     * @return 所需的对象, 已经完成了序列化, 类型强制转换接收即可
     */
    public Object setspop(String setName) {
        if (StringUtils.isBlank(setName)) {
            return null;
        }
        return redisTemplate.opsForSet().pop(setName);
    }


    /**
     * 从set中删除指定的值,返回影响的记录数,值不存在返回0
     *
     * @param setName
     * @param value
     * @return
     */
    public Long setsrem(String setName, String value) {
        if (StringUtils.isBlank(setName) || StringUtils.isBlank(value)) {
            return null;
        }
        return redisTemplate.opsForSet().remove(setName, value);
    }


}
