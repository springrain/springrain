package org.springrain.cms.utils;

import java.lang.reflect.Field;

/**
 * 对象处理工具类
 * @author AngeryFeather
 *
 */
public class ObjectUtils {
	
	/**
	 * 该方法判断一个对象中的所有字段是否全部为空，如果全部为空返回false，否则返回true
	 * @param object
	 * @return
	 */
	public static boolean checkAllField(Object obj){
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if(f.get(obj) != null)
					return true;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return false;
			}
		}
		return false;
	}
}
