package org.springrain.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存的配置,自定义 cacheManager 用于实现替换.
 * @author caomei
 *
 */
@Configuration
public class CacheConfig {
    
    
    //private Long cacheTimeout=1800000L;
    

    /**
     * 基于内存的cacheManager
     * @return
     */
    @Bean("cacheManager")
    public CacheManager cacheManager() {
        CacheManager  cacheManager=new ConcurrentMapCacheManager();
        
        return cacheManager;
    }
    
    //----------------------------------------------基于redis单机的cacheManager 开始-----------------------------------------//
    


    
    
    
    
    //----------------------------------------------基于redis单机的cacheManager 结束-----------------------------------------//
    
    
    
    
    
    //----------------------------------------------基于redis集群的cacheManager 开始-----------------------------------------//
    
    
    //----------------------------------------------基于redis集群的cacheManager 结束-----------------------------------------//
}
