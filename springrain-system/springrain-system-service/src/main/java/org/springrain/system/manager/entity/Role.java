package org.springrain.system.manager.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
 * @see org.springrain.system.manager.entity.Role
 */
@Table(name = "t_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "Role"; public static final String
	 * ALIAS_ID = "角色ID"; public static final String ALIAS_NAME = "角色名称"; public
	 * static final String ALIAS_PERMISSIONS = "权限"; public static final String
	 * ALIAS_PID = "所属部门"; public static final String ALIAS_REMARK = "备注"; public
	 * static final String ALIAS_ACTIVE = "状态(0:禁用2:启用)";
	 */
	// date formats

	// columns START
	/**
	 * 角色ID
	 */
	private java.lang.String id;
	/**
	 * 角色名称
	 */
	private java.lang.String name;
	/**
	 * 角色编码，用于生成权限框架的惟一标识权限
	 */
	private java.lang.String code;
	/**
	 * 父角色
	 */
	private java.lang.String pid;
	/**
	 * 角色类型
	 */
	private Integer roleType;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 状态(0:禁用1:启用)
	 */
	private java.lang.Integer active;
	/**
	 * 所属部门
	 * 
	 */
	private String orgId;
	// columns END 数据库字段结束

	private List<Menu> menus;
	// 归属部门名称
	private String orgname;
	// 对应目录名称 逗号分隔
	private String menunames;

	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 创建人
	 */
	private java.lang.String createUserId;
	/**
	 * 修改时间
	 */
	private java.util.Date updateTime;
	/**
	 * 修改人
	 */
	private java.lang.String updateUserId;

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

	// concstructor

	/**
	 * 该角色下的用户数
	 */
	private java.lang.Integer userCount;

	/**
	 * 有用权限的菜单的数据，格式为：自营管理(50/50),第三方店铺(0),平台(50/70),数据监控(20/30),财务管理(10/30)
	 */
	private java.lang.String menusInfo;

	/**
	 * 角色-菜单关联
	 */
	private List<RoleMenu> roleMenus;

	public Role() {
	}

	public Role(java.lang.String id) {
		this.id = id;
	}

	@Transient
	public String getMenunames() {
		return menunames;
	}

	public void setMenunames(String menunames) {
		this.menunames = menunames;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	@Transient
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Id
	@WhereSQL(sql = "id=:Role_id")
	public java.lang.String getId() {
		return this.id;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@WhereSQL(sql = "name=:Role_name")
	public java.lang.String getName() {
		return this.name;
	}

	public void setPid(java.lang.String value) {
		this.pid = value;
	}

	@WhereSQL(sql = "pid=:Role_pid")
	public java.lang.String getPid() {
		return this.pid;
	}

	@WhereSQL(sql = "roleType=:Role_roleType")
	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	@WhereSQL(sql = "remark=:Role_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setActive(Integer value) {
		this.active = value;
	}

	@WhereSQL(sql = "active=:Role_active")
	public Integer getActive() {
		return this.active;
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
	@WhereSQL(sql = "bak1=:Role_bak1")
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
	@WhereSQL(sql = "bak2=:Role_bak2")
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
	@WhereSQL(sql = "bak3=:Role_bak3")
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
	@WhereSQL(sql = "bak4=:Role_bak4")
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
	@WhereSQL(sql = "bak5=:Role_bak5")
	public java.lang.String getBak5() {
		return this.bak5;
	}

	@Transient
	public java.lang.Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(java.lang.Integer userCount) {
		this.userCount = userCount;
	}

	@Transient
	public java.lang.String getMenusInfo() {
		return menusInfo;
	}

	public void setMenusInfo(java.lang.String menusInfo) {
		this.menusInfo = menusInfo;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("角色ID[").append(getId()).append("],").append("角色名称[").append(getName())
				.append("],").append("权限编码[").append(getCode()).append("],").append("上级角色ID,暂时不实现[").append(getPid())
				.append("],").append("0系统角色,1部门主管,2业务岗位[").append(getRoleType()).append("],").append("备注[")
				.append(getRemark()).append("],").append("是否有效(0否,1是)[").append(getActive()).append("],")
				.append("归属的部门Id[").append(getOrgId()).append("],").append("bak1[").append(getBak1()).append("],")
				.append("bak2[").append(getBak2()).append("],").append("bak3[").append(getBak3()).append("],")
				.append("bak4[").append(getBak4()).append("],").append("bak5[").append(getBak5()).append("],")
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Role == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		Role other = (Role) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Transient
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@WhereSQL(sql = "createTime=:Role_createTime")
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@WhereSQL(sql = "createUserId=:Role_createUserId")
	public java.lang.String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
	}

	@WhereSQL(sql = "updateTime=:Role_updateTime")
	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	@WhereSQL(sql = "updateUserId=:Role_updateUserId")
	public java.lang.String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(java.lang.String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Transient
	public List<RoleMenu> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}

}
