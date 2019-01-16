package org.springrain.system.core.entity;

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
 * @version 2013-07-06 16:02:58
 * @see org.springrain.system.core.entity.Org
 */
@Table(name = "t_org")
public class Org extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "Org"; public static final String
	 * ALIAS_ID = "编号"; public static final String ALIAS_NAME = "名称"; public static
	 * final String ALIAS_COMCODE = "代码"; public static final String ALIAS_PID =
	 * "上级部门ID"; public static final String ALIAS_SYSID = "子系统ID"; public static
	 * final String ALIAS_TYPE = "0,组织机构 1.部门"; public static final String
	 * ALIAS_LEAF = "叶子节点(0:树枝节点;1:叶子节点)"; public static final String ALIAS_SORTNO =
	 * "排序号"; public static final String ALIAS_DESCRIPTION = "描述"; public static
	 * final String ALIAS_ACTIVE = "0.失效 1.有效";
	 */
	// date formats

	// columns START
	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 代码
	 */
	private java.lang.String comcode;
	/**
	 * 上级部门ID
	 */
	private java.lang.String pid;
	/**
	 * 0,组织机构 1.部门,2.虚拟部门
	 */
	private java.lang.Integer orgType;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	private java.lang.Integer leaf;
	/**
	 * 排序号
	 */
	private java.lang.Integer sortno;
	/**
	 * 描述
	 */
	private java.lang.String description;
	/**
	 * 0.失效 1.有效
	 */
	private java.lang.Integer active;

	private List<Org> leafOrg;
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

	public Org() {
	}

	public Org(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	@Id
	@WhereSQL(sql = "id=:Org_id")
	public java.lang.String getId() {
		return this.id;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@WhereSQL(sql = "name=:Org_name")
	public java.lang.String getName() {
		return this.name;
	}

	public void setComcode(java.lang.String value) {
		this.comcode = value;
	}

	@WhereSQL(sql = "comcode=:Org_comcode")
	public java.lang.String getComcode() {
		return this.comcode;
	}

	public void setPid(java.lang.String value) {
		this.pid = value;
	}

	@WhereSQL(sql = "pid=:Org_pid")
	public java.lang.String getPid() {
		return this.pid;
	}

	public void setOrgType(java.lang.Integer value) {
		this.orgType = value;
	}

	@WhereSQL(sql = "orgType=:Org_orgType")
	public java.lang.Integer getOrgType() {
		return this.orgType;
	}

	public void setLeaf(java.lang.Integer value) {
		this.leaf = value;
	}

	@WhereSQL(sql = "leaf=:Org_leaf")
	public java.lang.Integer getLeaf() {
		return this.leaf;
	}

	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}

	@WhereSQL(sql = "sortno=:Org_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	@WhereSQL(sql = "description=:Org_description")
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setActive(Integer value) {
		this.active = value;
	}

	@WhereSQL(sql = "active=:Org_active")
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
	@WhereSQL(sql = "bak1=:Org_bak1")
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
	@WhereSQL(sql = "bak2=:Org_bak2")
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
	@WhereSQL(sql = "bak3=:Org_bak3")
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
	@WhereSQL(sql = "bak4=:Org_bak4")
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
	@WhereSQL(sql = "bak5=:Org_bak5")
	public java.lang.String getBak5() {
		return this.bak5;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("编号[").append(getId()).append("],").append("名称[").append(getName())
				.append("],").append("代码[").append(getComcode()).append("],").append("上级部门ID[").append(getPid())
				.append("],").append("1.部门,2.虚拟权限组,10站长部门,11微信,12企业号,13pc,14wap,15投票[").append(getOrgType())
				.append("],").append("叶子节点(0:树枝节点;1:叶子节点)[").append(getLeaf()).append("],").append("排序号[")
				.append(getSortno()).append("],").append("描述[").append(getDescription()).append("],")
				.append("是否有效(0否,1是)[").append(getActive()).append("],").append("bak1[").append(getBak1()).append("],")
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
		if (obj instanceof Org == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		Org other = (Org) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Transient
	public List<Org> getLeafOrg() {
		return leafOrg;
	}

	public void setLeafOrg(List<Org> leafOrg) {
		this.leafOrg = leafOrg;
	}
}
