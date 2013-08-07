package org.springrain.frame.entity;

import javax.persistence.Transient;



/**
 *  Entity基类,所有的entity必须继承此类
 * @copyright {@link 9iu.org}
 * @author springrain<9iuorg@gmail.com>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.frame.entity.BaseEntity
 */
public class BaseEntity  implements IBaseEntity {


	private static final long serialVersionUID = 1L;
	
	private int page = 1;     //页数

	private int rows = 10;	  //行数
	
	/**
	 * 表的别名,用于处理复杂的where 条件拼接
	 */
	private String frameTableAlias=null;
	
	@Transient
	public static long isSerialVersionUID() {
		return serialVersionUID;
	}
	
	public void setSerialVersionUID(long l) {
		//return serialVersionUID;
	}
	@Transient
	public String getFrameTableAlias() {
		return frameTableAlias;
	}
	
	@Transient
	public String isFrameTableAlias() {
		return frameTableAlias;
	}

	public void setFrameTableAlias(String frameTableAlias) {
		this.frameTableAlias = frameTableAlias;
	}

	/**
	 * @return 获取 page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param 设置 page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return 获取 rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param 设置 rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	
}
