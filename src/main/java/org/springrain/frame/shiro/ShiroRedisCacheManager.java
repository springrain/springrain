package org.springrain.frame.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.redisson.api.RedissonClient;
/**
 * redis的缓存管理器
 * @author caomei
 *
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {
    private RedissonClient redissonClient;
	@Override
	protected Cache createCache(String cacheName) throws CacheException {
		return new ShiroRedisCache<String, Object>(cacheName,redissonClient);
	}
	public RedissonClient getRedissonClient() {
		return redissonClient;
	}
	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

}
