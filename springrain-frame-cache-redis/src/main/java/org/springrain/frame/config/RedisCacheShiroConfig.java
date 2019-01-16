package org.springrain.frame.config;

import javax.annotation.Resource;

import org.apache.shiro.cache.CacheManager;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springrain.frame.shiro.ShiroRedisCacheManager;

/**
 * 配置 shiro<br>
 * shiroFilter是入口的filter,springboot已经默认拦截所有请求<br>
 * springboot会把所有的filter列为平级,造成shiro的子拦截器和shiroFilter同级,造成访问异常,所以shiro的子Filter需要手动disable
 * 
 * @author caomei
 *
 */
@Configuration("configuration-ShiroConfig")
public class RedisCacheShiroConfig {




	/**
	 * 集群session
	 * 
	 * @return
	 */

	// 集群模式下,需要redis作为cache,注入redissonClient,单机模式需要注释掉

	@Resource
	private RedissonClient redissonClient;

	@Bean("shiroCacheManager")
	public CacheManager shiroCacheManager() {
		ShiroRedisCacheManager cacheManager = new ShiroRedisCacheManager();
		cacheManager.setRedissonClient(redissonClient);
		return cacheManager;
	}



}
