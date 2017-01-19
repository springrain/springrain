package org.springrain.frame.util;

/**
 * db配置文件
 * 
 * @author wulei
 *
 */
public class DbPropertyUtil {
	public static CerpTabMessage tab = null;

	static {
		tab = new CerpTabMessage("db");
	}

	public static String getValue(String key) {
		return tab.getString(key);
	}

}
