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
 * @version  2017-08-25 18:08:07
 * @see org.springrain.system.entity.RoleOrg
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
	public static final String ALIAS_ORGTYPE = "0组织机构 ,1.部门,2.虚拟权限组";
	public static final String ALIAS_HASLEAF = "是否包含子部门,0不包含,1包含";
	public static final String ALIAS_MANAGER = "0:非主管，1:主管";
	public static final String ALIAS_ACTIVE = "是否可用,0不可用,1可用";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
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
	/**
	 * 0组织机构 ,1.部门,2.虚拟权限组
	 */
	private java.lang.Integer orgType;
	/**
	 * 是否包含子部门,0不包含,1包含
	 */
	private java.lang.Integer hasLeaf;
	/**
	 * 0:非主管，1:主管
	 */
	private java.lang.Integer manager;
	/**
	 * 是否可用,0不可用,1可用
	 */
	private java.lang.Integer active;
	/**
	 * bak1
	 */
	private java.lang.String bak1;
	/**
	 * bak2
	 */
	private java.lang.String bak2;
	/**
	 * bak3
	 */
	private java.lang.String bak3;
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
		/**
		 * 编号
		 */
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	
	
	/**
	 * 编号
	 */
	@Id
     @WhereSQL(sql="id=:RoleOrg_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 角色编号
		 */
	public void setRoleId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.roleId = value;
	}
	
	
	
	/**
	 * 角色编号
	 */
     @WhereSQL(sql="roleId=:RoleOrg_roleId")
	public java.lang.String getRoleId() {
		return this.roleId;
	}
		/**
		 * 部门编号
		 */
	public void setOrgId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orgId = value;
	}
	
	
	
	/**
	 * 部门编号
	 */
     @WhereSQL(sql="orgId=:RoleOrg_orgId")
	public java.lang.String getOrgId() {
		return this.orgId;
	}
		/**
		 * 0组织机构 ,1.部门,2.虚拟权限组
		 */
	public void setOrgType(java.lang.Integer value) {
		this.orgType = value;
	}
	
	
	
	/**
	 * 0组织机构 ,1.部门,2.虚拟权限组
	 */
     @WhereSQL(sql="orgType=:RoleOrg_orgType")
	public java.lang.Integer getOrgType() {
		return this.orgType;
	}
		/**
		 * 是否包含子部门,0不包含,1包含
		 */
	public void setHasLeaf(java.lang.Integer value) {
		this.hasLeaf = value;
	}
	
	
	
	/**
	 * 是否包含子部门,0不包含,1包含
	 */
     @WhereSQL(sql="hasLeaf=:RoleOrg_hasLeaf")
	public java.lang.Integer getHasLeaf() {
		return this.hasLeaf;
	}
		/**
		 * 0:非主管，1:主管
		 */
	public void setManager(java.lang.Integer value) {
		this.manager = value;
	}
	
	
	
	/**
	 * 0:非主管，1:主管
	 */
     @WhereSQL(sql="manager=:RoleOrg_manager")
	public java.lang.Integer getManager() {
		return this.manager;
	}
		/**
		 * 是否可用,0不可用,1可用
		 */
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
	
	
	/**
	 * 是否可用,0不可用,1可用
	 */
     @WhereSQL(sql="active=:RoleOrg_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
		/**
		 * bak1
		 */
	public void setBak1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak1 = value;
	}
	
	
	
	/**
	 * bak1
	 */
     @WhereSQL(sql="bak1=:RoleOrg_bak1")
	public java.lang.String getBak1() {
		return this.bak1;
	}
		/**
		 * bak2
		 */
	public void setBak2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak2 = value;
	}
	
	
	
	/**
	 * bak2
	 */
     @WhereSQL(sql="bak2=:RoleOrg_bak2")
	public java.lang.String getBak2() {
		return this.bak2;
	}
		/**
		 * bak3
		 */
	public void setBak3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak3 = value;
	}
	
	
	
	/**
	 * bak3
	 */
     @WhereSQL(sql="bak3=:RoleOrg_bak3")
	public java.lang.String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("编号[").append(getId()).append("],")
			.append("角色编号[").append(getRoleId()).append("],")
			.append("部门编号[").append(getOrgId()).append("],")
			.append("0组织机构 ,1.部门,2.虚拟权限组[").append(getOrgType()).append("],")
			.append("是否包含子部门,0不包含,1包含[").append(getHasLeaf()).append("],")
			.append("0:非主管，1:主管[").append(getManager()).append("],")
			.append("是否可用,0不可用,1可用[").append(getActive()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
			.toString();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RoleOrg == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		RoleOrg other = (RoleOrg)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
