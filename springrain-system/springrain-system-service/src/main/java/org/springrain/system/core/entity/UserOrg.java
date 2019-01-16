package org.springrain.system.core.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
 * @see org.springrain.system.core.entity.UserOrg
 */
@Table(name = "t_user_org")
public class UserOrg extends BaseEntity {

	private static final long serialVersionUID = 1L;

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
	 * 机构编号
	 */
	private java.lang.String orgId;
	/**
	 * 是否主管(0会员 10 员工 11主管 12代理主管 13虚拟主管)
	 */
	private java.lang.Integer managerType;
	/**
	 * 是否包含子部门(0不包含1包含)
	 */
	private java.lang.Integer hasleaf;
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

	private String orgName;

	// concstructor

	public UserOrg() {
	}

	public UserOrg(java.lang.String id) {
		this.id = id;
	}

	// get and set
	/**
	 * 编号
	 */
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	/**
	 * 编号
	 */
	@Id
	@WhereSQL(sql = "id=:UserOrg_id")
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 用户编号
	 */
	public void setUserId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.userId = value;
	}

	/**
	 * 用户编号
	 */
	@WhereSQL(sql = "userId=:UserOrg_userId")
	public java.lang.String getUserId() {
		return this.userId;
	}

	/**
	 * 机构编号
	 */
	public void setOrgId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.orgId = value;
	}

	/**
	 * 机构编号
	 */
	@WhereSQL(sql = "orgId=:UserOrg_orgId")
	public java.lang.String getOrgId() {
		return this.orgId;
	}

	/**
	 * 是否主管(0会员 10 员工 11主管 12代理主管 13虚拟主管)
	 */
	public void setManagerType(java.lang.Integer value) {
		this.managerType = value;
	}

	/**
	 * 是否主管(0会员 10 员工 11主管 12代理主管 13虚拟主管)
	 */
	@WhereSQL(sql = "managerType=:UserOrg_managerType")
	public java.lang.Integer getManagerType() {
		return this.managerType;
	}

	/**
	 * 是否包含子部门(0不包含1包含)
	 */
	public void setHasleaf(java.lang.Integer value) {
		this.hasleaf = value;
	}

	/**
	 * 是否包含子部门(0不包含1包含)
	 */
	@WhereSQL(sql = "hasleaf=:UserOrg_hasleaf")
	public java.lang.Integer getHasleaf() {
		return this.hasleaf;
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
	@WhereSQL(sql = "bak1=:UserOrg_bak1")
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
	@WhereSQL(sql = "bak2=:UserOrg_bak2")
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
	@WhereSQL(sql = "bak3=:UserOrg_bak3")
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
	@WhereSQL(sql = "bak4=:UserOrg_bak4")
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
	@WhereSQL(sql = "bak5=:UserOrg_bak5")
	public java.lang.String getBak5() {
		return this.bak5;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("编号[").append(getId()).append("],").append("用户编号[").append(getUserId())
				.append("],").append("机构编号[").append(getOrgId()).append("],")
				.append("是否主管(0会员 10 员工 11主管 12代理主管 13虚拟主管)[").append(getManagerType()).append("],")
				.append("是否包含子部门(0不包含1包含)[").append(getHasleaf()).append("],").append("bak1[").append(getBak1())
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
		if (obj instanceof UserOrg == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		UserOrg other = (UserOrg) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
