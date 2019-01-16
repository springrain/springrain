package org.springrain.system.core.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 * @see org.springrain.system.core.entity.UserRole
 */
@Table(name = "t_user_role")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "UserRole"; public static final
	 * String ALIAS_ID = "编号"; public static final String ALIAS_USERID = "用户编号";
	 * public static final String ALIAS_ROLEID = "角色编号";
	 * 
	 */
	// date formats

	// columns START
	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 用户编号
	 */
	private java.lang.String userId;
	/**
	 * 角色编号
	 */
	private java.lang.String roleId;
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
	/**
	 * bak4
	 */
	private java.lang.String bak4;

	/**
	 * bak5
	 */
	private java.lang.String bak5;
	// columns END 数据库字段结束

	// concstructor

	public UserRole() {
	}

	public UserRole(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	@Id
	@WhereSQL(sql = "id=:UserRole_id")
	public java.lang.String getId() {
		return this.id;
	}

	public void setUserId(java.lang.String value) {
		this.userId = value;
	}

	@WhereSQL(sql = "userId=:UserRole_userId")
	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}

	@WhereSQL(sql = "roleId=:UserRole_roleId")
	public java.lang.String getRoleId() {
		return this.roleId;
	}

	/**
	 * bak1
	 */
	public void setBak1(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak1 = value;
	}

	/**
	 * bak1
	 */
	@WhereSQL(sql = "bak1=:UserRole_bak1")
	public java.lang.String getBak1() {
		return this.bak1;
	}

	/**
	 * bak2
	 */
	public void setBak2(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak2 = value;
	}

	/**
	 * bak2
	 */
	@WhereSQL(sql = "bak2=:UserRole_bak2")
	public java.lang.String getBak2() {
		return this.bak2;
	}

	/**
	 * bak3
	 */
	public void setBak3(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak3 = value;
	}

	/**
	 * bak3
	 */
	@WhereSQL(sql = "bak3=:UserRole_bak3")
	public java.lang.String getBak3() {
		return this.bak3;
	}

	/**
	 * bak4
	 */
	public void setBak4(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak4 = value;
	}

	/**
	 * bak4
	 */
	@WhereSQL(sql = "bak4=:UserRole_bak4")
	public java.lang.String getBak4() {
		return this.bak4;
	}

	/**
	 * bak5
	 */
	public void setBak5(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak5 = value;
	}

	/**
	 * bak5
	 */
	@WhereSQL(sql = "bak5=:UserRole_bak5")
	public java.lang.String getBak5() {
		return this.bak5;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("编号[").append(getId()).append("],").append("用户编号[").append(getUserId())
				.append("],").append("角色编号[").append(getRoleId()).append("],").append("bak1[").append(getBak1())
				.append("],").append("bak2[").append(getBak2()).append("],").append("bak3[").append(getBak3())
				.append("],").append("bak4[").append(getBak4()).append("],").append("bak5[").append(getBak5())
				.append("],").toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserRole == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		UserRole other = (UserRole) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
