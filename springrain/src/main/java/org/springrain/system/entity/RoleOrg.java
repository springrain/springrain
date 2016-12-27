package org.springrain.system.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-12-05 13:26:18
 * @see org.springrain.demo.entity.RoleOrg
 */
@Table(name="t_role_org")
public class RoleOrg  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "角色部门中间表";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_ROLEID = "角色编号";
	public static final String ALIAS_ORGID = "部门编号";
	public static final String ALIAS_HASLEAF = "是否包含子部门,0不包含,1包含";
	public static final String ALIAS_ACTIVE = "是否可用,0不可用,1不可用";
    */
	//date formats
	
	//columns START
	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 角色编号
	 */
	private java.lang.String roleId;
	/**
	 * 部门编号
	 */
	private java.lang.String orgId;
	
	
	private Integer orgType;
	
	
	
	/**
	 * 是否包含子部门,0不包含,1包含
	 */
	private java.lang.Integer hasLeaf;
	/**
	 * 是否可用,0不可用,1不可用
	 */
	private java.lang.Integer active;
	//columns END 数据库字段结束
	
	//concstructor

	public RoleOrg(){
	}

	public RoleOrg(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:RoleOrg_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setRoleId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.roleId = value;
	}
	
     @WhereSQL(sql="roleId=:RoleOrg_roleId")
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	public void setOrgId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orgId = value;
	}
	
     @WhereSQL(sql="orgId=:RoleOrg_orgId")
	public java.lang.String getOrgId() {
		return this.orgId;
	}
     
     @WhereSQL(sql="orgId=:RoleOrg_orgType")
	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public void setHasLeaf(java.lang.Integer value) {
		this.hasLeaf = value;
	}
	
     @WhereSQL(sql="hasLeaf=:RoleOrg_hasLeaf")
	public java.lang.Integer getHasLeaf() {
		return this.hasLeaf;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:RoleOrg_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("角色编号[").append(getRoleId()).append("],")
			.append("部门编号[").append(getOrgId()).append("],")
			.append("是否包含子部门,0不包含,1包含[").append(getHasLeaf()).append("],")
			.append("是否可用,0不可用,1不可用[").append(getActive()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RoleOrg == false) return false;
		if(this == obj) return true;
		RoleOrg other = (RoleOrg)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
