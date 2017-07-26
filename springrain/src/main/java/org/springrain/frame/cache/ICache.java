package org.springrain.frame.cache;

import java.util.List;
import java.util.Set;


public interface ICache {
	/**
	 * 删除给定的一个或多个 key,不存在的 key 会被忽略。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	String del(byte[] key)throws Exception;
	/**
	 * 将字符串值 value 关联到 key,如果 key 已经持有其他值， SET 就覆写旧值，无视类型.
	 * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	
	void set(byte[] key,byte[] value)throws Exception;
	
	/**
	 * 将字符串值 value 关联到 key,如果 key 已经持有其他值， SET 就覆写旧值，无视类型.
	 * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
	 * @param key
	 * @param value
	 * @param expireMillisecond
	 * @return
	 * @throws Exception
	 */
	void set(byte[] key,byte[] value,Long expireMillisecond)throws Exception;
	
	
	Boolean setNX(byte[] key,byte[] value)throws Exception;
	
	
	Boolean setNX(byte[] key,byte[] value,Long expireMillisecond)throws Exception;
	
	
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Object get(byte[] key)throws Exception;
	/**
	 * 根据 正则表达式key 获取 列表
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	
	Set keys(byte[] keys)throws Exception;
	
	/**
	 * 一个包含哈希表中所有域的表。
     * 当 key 不存在时，返回一个空表。
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	Set hKeys(byte[] key)throws Exception;
	
	
	
	/**
	 * 
	 * @param key
	 * @param mapkey
	 * @param value
	 * @param expireMillisecond
	 * @return
	 * @throws Exception
	 */
	Boolean hSet(byte[] key,byte[] mapkey,byte[] value,Long expireMillisecond)throws Exception;
	

	/**
	 * 获取缓存
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Object hGet(byte[] key,byte[] mapkey)throws Exception;
	
	
	/**
	 * 删除 缓存
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	Long hDel(byte[] key,byte[] mapkey)throws Exception;
	
	/**
	 * 获取 map的长度
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Long hLen(byte[] key)throws Exception;
/**
 * 获取 map中的所有值
 * @param key
 * @return
 * @throws Exception
 */
	List hVals(byte[] key)throws Exception;
	
	
	/**
	 * 获取 map的长度
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Long dbSize()throws Exception;
	
	/**
	 * 获取 map的长度
	 * @param key
	 * @return
	 * @throws Exception
	 */
	void flushDb()throws Exception;
}
