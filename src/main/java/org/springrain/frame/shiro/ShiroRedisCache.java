package org.springrain.frame.shiro;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springrain.frame.common.BaseLogger;

/**
 * Shiro 实现的缓存
 * 
 * @author caomei
 *
 * @param <K>
 * @param <V>
 */
public class ShiroRedisCache<K, V> extends BaseLogger implements Cache<K, V> {
	private String name;
	private RedissonClient redissonClient;
	
    private RMap<Object, Object> mapCache;
	

	public ShiroRedisCache(String name, RedissonClient redissonClient) {
		this.name = name;
		this.redissonClient = redissonClient;
		mapCache = redissonClient.getMap(name);
	}

	
	
	
	
	@Override
	public V get(K key) throws CacheException {
		if (logger.isDebugEnabled()){
			logger.debug("根据key从Redis中获取对象 key [{}]",key);
		}
		try {
			if (key == null) {
				return null;
			} else {
			    
			  V value=  (V)  mapCache.get(key);
			    
			  return   value;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CacheException(e);
		}

	}

	@Override
	public V put(K key, V value) throws CacheException {
		if (logger.isDebugEnabled()){
			logger.debug("根据key从存储 key [{}]",key);
		}
		try {
		    mapCache.fastPut(key, value);
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CacheException(e);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		if (logger.isDebugEnabled()){
			logger.debug("从redis中删除 key [{}]",key);
		}
		try {
		    mapCache.fastRemove(key);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CacheException(e);
		}
	}

	@Override
	public void clear() throws CacheException {
		if (logger.isDebugEnabled()){
			logger.debug("从redis中删除所有元素");
		}
		try {
		    mapCache.delete();
		   // mapCache=null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CacheException(e);
		}
	}

	@Override
	public int size() {
		try {
		    
		    return mapCache.size();
		    
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CacheException(e);
		}
	}

	
	@Override
	public Set<K> keys() {
		try {
		    
		    Set<K> keys = (Set<K>) mapCache.keySet();
			return keys;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	
	@Override
	public Collection<V> values() {
		try {
		    Collection<V> values = (Collection<V>) mapCache.values();
			return values;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}



}
