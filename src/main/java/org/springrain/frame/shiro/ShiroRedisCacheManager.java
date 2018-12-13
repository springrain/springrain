package org.springrain.frame.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.redisson.api.RedissonClient;
import org.springrain.frame.util.GlobalStatic;
/**
 * redis的缓存管理器
 * @author caomei
 *
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {
    private RedissonClient redissonClient;
	@Override
	protected Cache createCache(String cacheName) throws CacheException {
		return new ShiroRedisCache<String, Object>(GlobalStatic.projectKeyPrefix + cacheName, redissonClient);
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) {

		return super.getCache(GlobalStatic.projectKeyPrefix + name);

	}
	public RedissonClient getRedissonClient() {
		return redissonClient;
	}
	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}


}
