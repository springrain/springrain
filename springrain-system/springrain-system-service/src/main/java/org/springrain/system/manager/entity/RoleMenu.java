package org.springrain.system.manager.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.base.annotation.WhereSQL;
import org.springrain.frame.base.entity.BaseEntity;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:02:59
 * @see org.springrain.system.manager.entity.RoleMenu
 */
@Table(name = "t_role_menu")
public class RoleMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "RoleMenu"; public static final
	 * String ALIAS_ID = "编号"; public static final String ALIAS_ROLEID = "角色编号";
	 * public static final String ALIAS_MENUID = "菜单编号";
	 */
	// date formats

	// columns START
	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 角色编号
	 */
	private java.lang.String roleId;
	/**
	 * 菜单编号
	 */
	private java.lang.String menuId;
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

	public RoleMenu() {
	}

	public RoleMenu(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	@Id
	@WhereSQL(sql = "id=:RoleMenu_id")
	public java.lang.String getId() {
		return this.id;
	}

	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}

	@WhereSQL(sql = "roleId=:RoleMenu_roleId")
	public java.lang.String getRoleId() {
		return this.roleId;
	}

	public void setMenuId(java.lang.String value) {
		this.menuId = value;
	}

	@WhereSQL(sql = "menuId=:RoleMenu_menuId")
	public java.lang.String getMenuId() {
		return this.menuId;
	}

	/**
	 * bak1
	 */
	@WhereSQL(sql = "bak1=:RoleMenu_bak1")
	public java.lang.String getBak1() {
		return this.bak1;
	}

	public void setBak1(java.lang.String bak1) {
		this.bak1 = bak1;
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
	@WhereSQL(sql = "bak2=:RoleMenu_bak2")
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
	@WhereSQL(sql = "bak3=:RoleMenu_bak3")
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
	@WhereSQL(sql = "bak4=:RoleMenu_bak4")
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
	@WhereSQL(sql = "bak5=:RoleMenu_bak5")
	public java.lang.String getBak5() {
		return this.bak5;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("编号[").append(getId()).append("],").append("角色编号[").append(getRoleId())
				.append("],").append("菜单编号[").append(getMenuId()).append("],").append("bak1[").append(getBak1())
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
		if (obj instanceof RoleMenu == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		RoleMenu other = (RoleMenu) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
