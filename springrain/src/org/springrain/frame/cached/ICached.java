package org.springrain.frame.cached;

import java.util.List;


public interface ICached {
	/**
	 * 删除 缓存
	 * @param key
	 * @return
	 * @throws Exception
	 */
	String deleteCached(String key)throws Exception;
	/**
	 * 更新 缓存
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	Object updateCached(String key,Object value)throws Exception;
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Object getCached(String key)throws Exception;
	
	/**
	 * 根据 正则表达式key 获取 列表
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	List getKeys(String keys)throws Exception;
	
	
}
