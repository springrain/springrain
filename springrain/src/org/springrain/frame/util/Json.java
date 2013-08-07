package org.springrain.frame.util;

import java.util.List;


/**
 * 
 * @Title: Json.java
 * @Package org.springrainframe.util
 * @Description: 返回json的包装类
 * @author ZhangNan
 * @date 2013-7-10 上午11:49:50
 * @version V1.0
 *
 */
public class Json {
	private List<?> rows;
	private int total;
	private String message = "";
	private boolean success = false; //结果
	/**
	 * @return 获取 rows
	 */
	public List<?> getRows() {
		return rows;
	}
	/**
	 * @param 设置 rows
	 */
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	/**
	 * @return 获取 total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param 设置 total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return 获取 message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param 设置 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return 获取 success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param 设置 success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
	
}