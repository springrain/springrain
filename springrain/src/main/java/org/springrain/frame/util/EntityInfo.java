package org.springrain.frame.util;

/**
* 记录缓存数据库一个表对应的Entity的信息
*
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.frame.util.EntityInfo
*/
public class EntityInfo {
	
	private String tableName=null;
	private String className=null;
	private String tableSuffix="";

	private Class<?> pkReturnType;
	
	
	private String pkName=null;

	//是否分表
	private boolean sharding=false;
	/**
	 * 主键序列
	 */
	private String pksequence=null;
	/**
	 * 是否不记录日志,默认false 为记录
	 */
	private boolean notLog=false;
	
	
	/**
	 * 数据库的表名
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 数据库表映射的实体类名
	 * @return
	 */
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	/**
	 * 数据库分表的后缀名 例如 _history_2012
	 * @return
	 */
	public String getTableSuffix() {
		return tableSuffix;
	}
	public void setTableSuffix(String tableSuffix) {
		this.tableSuffix = tableSuffix;
	}
	/**
	 * 获取table主键对应Enitty属性名称
	 * @return String
	 */
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	public boolean isSharding() {
		return sharding;
	}
	public void setSharding(boolean sharding) {
		this.sharding = sharding;
	}
	public String getPksequence() {
		return pksequence;
	}
	public void setPksequence(String pksequence) {
		this.pksequence = pksequence;
	}
	public boolean isNotLog() {
		return notLog;
	}
	public void setNotLog(boolean notLog) {
		this.notLog = notLog;
	}
	@SuppressWarnings("rawtypes")
	public Class getPkReturnType() {
		return pkReturnType;
	}
	@SuppressWarnings("rawtypes")
	public void setPkReturnType(Class pkReturnType) {
		this.pkReturnType = pkReturnType;
	}


}
