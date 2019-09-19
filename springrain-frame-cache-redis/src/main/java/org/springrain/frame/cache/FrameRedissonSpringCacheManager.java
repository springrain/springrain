package org.springrain.frame.cache;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;

/**
 * redisson的SpringCache 用于扩展实现Cache超时, 加上超时,吞吐量下降非常厉害,原因待查,暂时废弃
 *
 * @author caomei
 */
//@Deprecated
public class FrameRedissonSpringCacheManager extends RedissonSpringCacheManager {

    private long cacheTimeOut = 0L;

    public FrameRedissonSpringCacheManager(RedissonClient redisson) {
        super(redisson);
    }

    @Override
    protected CacheConfig createDefaultConfig() {

        CacheConfig _config = new CacheConfig();
        _config.setMaxIdleTime(cacheTimeOut);

        return _config;

    }

    public long getCacheTimeOut() {
        return cacheTimeOut;
    }

    public void setCacheTimeOut(long cacheTimeOut) {
        this.cacheTimeOut = cacheTimeOut;
    }

}
