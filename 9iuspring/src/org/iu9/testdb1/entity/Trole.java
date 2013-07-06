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
 * @version  2013-07-06 15:28:17
 * @see org.iu9.testdb1.entity.Trole
 */
@Table(name="t_role")
public class Trole  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Trole";
	public static final String ALIAS_ID = "角色ID";
	public static final String ALIAS_NAME = "角色名称";
	public static final String ALIAS_PERMISSIONS = "权限";
	public static final String ALIAS_PID = "所属部门";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_STATE = "状态(0:禁用2:启用)";
    */
	//date formats
	
	//columns START
	/**
	 * 角色ID
	 */
	private java.lang.String id;
	/**
	 * 角色名称
	 */
	private java.lang.String name;
	/**
	 * 权限
	 */
	private java.lang.String permissions;
	/**
	 * 所属部门
	 */
	private java.lang.String pid;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 状态(0:禁用2:启用)
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public Trole(){
	}

	public Trole(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Trole_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Trole_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setPermissions(java.lang.String value) {
		this.permissions = value;
	}
	
     @WhereSQL(sql="permissions=:Trole_permissions")
	public java.lang.String getPermissions() {
		return this.permissions;
	}
	public void setPid(java.lang.String value) {
		this.pid = value;
	}
	
     @WhereSQL(sql="pid=:Trole_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:Trole_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:Trole_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("角色ID[").append(getId()).append("],")
			.append("角色名称[").append(getName()).append("],")
			.append("权限[").append(getPermissions()).append("],")
			.append("所属部门[").append(getPid()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("状态(0:禁用2:启用)[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Trole == false) return false;
		if(this == obj) return true;
		Trole other = (Trole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
