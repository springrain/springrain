package org.springrain.demo.dto;

import java.util.List;

public class MenuTreeJSON {
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
