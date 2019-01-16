package org.springrain.frame.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 shiro<br>
 * shiroFilter是入口的filter,springboot已经默认拦截所有请求<br>
 * springboot会把所有的filter列为平级,造成shiro的子拦截器和shiroFilter同级,造成访问异常,所以shiro的子Filter需要手动disable
 * 
 * @author caomei
 *
 */
@Configuration("configuration-MemoryCacheShiroConfig")
public class MemoryCacheShiroConfig {
	/**
	 * 单机session
	 * 
	 * @return
	 */
	@Bean("shiroCacheManager")
	public CacheManager shiroCacheManager() {
		MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
		return cacheManager;
	}




}
