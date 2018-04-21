package org.springrain.frame.cache;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;


/**
 * redisson的SpringCache 用于扩展实现Cache超时, 加上超时,吞吐量下降非常厉害,原因待查,暂时废弃
 * @author caomei
 *
 */
@Deprecated
public class FrameRedissonSpringCacheManager extends RedissonSpringCacheManager {

    private CacheConfig defaultConfig;
    
    
    public FrameRedissonSpringCacheManager(RedissonClient redisson) {
        super(redisson);
    }


    public CacheConfig getDefaultConfig() {
        return defaultConfig;
    }


    public void setDefaultConfig(CacheConfig defaultConfig) {
        this.defaultConfig = defaultConfig;
    }
    
    @Override
    protected CacheConfig createDefaultConfig() {
        
        CacheConfig _config= new CacheConfig();
        
        
        if(defaultConfig!=null) {
            _config.setTTL(defaultConfig.getTTL());
            _config.setMaxIdleTime(defaultConfig.getMaxIdleTime());
            _config.setMaxSize(defaultConfig.getMaxSize());
        }
        
        return _config;
      
    }
    

}
