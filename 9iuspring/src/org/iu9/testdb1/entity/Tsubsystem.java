package org.iu9.testdb1.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.persistence.Id;
import javax.persistence.Table;
import org.iu9.frame.annotation.WhereSQL;

import org.iu9.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 15:28:18
 * @see org.iu9.testdb1.entity.Tsubsystem
 */
@Table(name="t_subsystem")
public class Tsubsystem  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Tsubsystem";
	public static final String ALIAS_ID = "子系统编号";
	public static final String ALIAS_NAME = "子系统名称";
	public static final String ALIAS_SORTNO = "子系统排序";
	public static final String ALIAS_URI = "子系统访问路径";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_STATE = "状态(0:不可用1:可用)";
    */
	//date formats
	
	//columns START
	/**
	 * 子系统编号
	 */
	private java.lang.String id;
	/**
	 * 子系统名称
	 */
	private java.lang.String name;
	/**
	 * 子系统排序
	 */
	private java.lang.Integer sortno;
	/**
	 * 子系统访问路径
	 */
	private java.lang.String uri;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 状态(0:不可用1:可用)
	 */
	private java.lang.String state;
	//columns END 数据库字段结束
	
	//concstructor

	public Tsubsystem(){
	}

	public Tsubsystem(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Tsubsystem_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Tsubsystem_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
     @WhereSQL(sql="sortno=:Tsubsystem_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	public void setUri(java.lang.String value) {
		this.uri = value;
	}
	
     @WhereSQL(sql="uri=:Tsubsystem_uri")
	public java.lang.String getUri() {
		return this.uri;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:Tsubsystem_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:Tsubsystem_state")
	public java.lang.String getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("子系统编号[").append(getId()).append("],")
			.append("子系统名称[").append(getName()).append("],")
			.append("子系统排序[").append(getSortno()).append("],")
			.append("子系统访问路径[").append(getUri()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("状态(0:不可用1:可用)[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Tsubsystem == false) return false;
		if(this == obj) return true;
		Tsubsystem other = (Tsubsystem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
