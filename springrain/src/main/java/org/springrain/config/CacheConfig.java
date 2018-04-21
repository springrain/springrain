package org.springrain.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存的配置,自定义 cacheManager 用于实现替换.
 * 
 * @author caomei
 *
 */
@Configuration
public class CacheConfig {

    @Value("${springrain.redis.hostport:redis://127.0.0.1:6379}")
    private String redisHostPort;

    @Value("${springrain.redis.pool.max-active:1024}")
    private Integer maxActive = 1024;

    @Value("${springrain.redis.pool.min-idle:200}")
    private Integer minIdle = 200;

    /**
     * 基于内存的cacheManager
     * 
     * @return
     * @throws IOException
     */

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        CacheManager cacheManager = new ConcurrentMapCacheManager();

        return cacheManager;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // --------基于redis的cacheManager 开始--------//

    /**
     * 基于redis的cacheManager,使用redisson客户端
     * 
     * @return
     * @throws IOException
     */
/*
    @Bean("cacheManager")
    public CacheManager cacheManager() {
        return new RedissonSpringCacheManager(redissonClient());
    }

    @Bean("redissonClient")
    public RedissonClient redissonClient()  {

        // 连接超时时间
        int connectTimeOut = 10000;
        // 重试次数
        int retryAttempts = 6;

        if (StringUtils.isBlank(redisHostPort)) {
            return null;
        }
        redisHostPort=redisHostPort.trim();
        
        Config config = new Config();
        String[] ipports = redisHostPort.split(",");

        if (ipports.length <= 1) {// 单机redis模式
            config.useSingleServer().setAddress(redisHostPort).setConnectTimeout(connectTimeOut)
                    .setRetryAttempts(retryAttempts).setConnectionPoolSize(maxActive)
                    .setConnectionMinimumIdleSize(minIdle);
        } else {// redis 集群.默认读slave
            config.useClusterServers().addNodeAddress(ipports).setConnectTimeout(connectTimeOut)
                    .setRetryAttempts(retryAttempts).setMasterConnectionPoolSize(maxActive)
                    .setSlaveConnectionPoolSize(maxActive).setMasterConnectionMinimumIdleSize(minIdle)
                    .setSlaveConnectionMinimumIdleSize(minIdle).setReadMode(ReadMode.SLAVE).setScanInterval(3000);
        }
        // 只能使用JDK的序列化,其他的shiro不兼容
        config.setCodec(new SerializationCodec());

        return Redisson.create(config);
    }
    
    
    //redis的操作,声明注入,避免出现依赖错误
    @Bean("redisOperation")
    public RedisOperation redisOperation() {
        RedisOperation redisOperation=new RedisOperation();
        redisOperation.setRedissonClient(redissonClient());
        return redisOperation;
    }
*/
    
    // --------基于redis的cacheManager 结束--------//
    
    
    
    
    
    
    
    
    

}
