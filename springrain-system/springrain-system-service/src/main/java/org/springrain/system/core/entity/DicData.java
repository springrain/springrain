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
import org.springrain.system.cms.entity.CmsProperty;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link springrain}
 * @author weicms.net<Auto generate>
 * @version 2013-08-02 16:32:29
 * @see org.springrain.system.core.entity.DicData
 */
@Table(name = "t_dic_data")
public class DicData extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "公共字典"; public static final String
	 * ALIAS_ID = "id"; public static final String ALIAS_NAME = "名称"; public static
	 * final String ALIAS_CODE = "编码"; public static final String ALIAS_DESCRIPTION
	 * = "描述"; public static final String ALIAS_SORT = "sort"; public static final
	 * String ALIAS_ACTIVE = "是否有效"; public static final String ALIAS_TYPEKEY =
	 * "类型";
	 */
	// date formats

	// columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 编码
	 */
	private java.lang.String code;

	/**
	 * 值
	 */
	private String val;

	/**
	 * 父ID
	 */
	private String pid;

	/**
	 * 描述
	 */
	private java.lang.String remark;

	/**
	 * 是否有效
	 */
	private java.lang.Integer active;
	/**
	 * 类型
	 */
	private java.lang.String typekey;

	private Integer sortno;
	/**
	 * typekey=medicinedesType时使用，存储药品类型的图片路径
	 */
	private java.lang.String bak1;
	/**
	 * typekey=medicinedesType时使用，前台是否显示药品类型图标(0不显示，1显示)
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

	/**
	 * 自定义参数
	 */
	private List<CmsProperty> propertyList;

	// concstructor

	public DicData() {
	}

	public DicData(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	@Id
	@WhereSQL(sql = "id=:DicData_id")
	public java.lang.String getId() {
		return this.id;
	}

	public void setName(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.name = value;
	}

	@WhereSQL(sql = "val=:DicData_val")
	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	@WhereSQL(sql = "name=:DicData_name")
	public java.lang.String getName() {
		return this.name;
	}

	public void setCode(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.code = value;
	}

	@WhereSQL(sql = "pid=:DicData_pid")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@WhereSQL(sql = "code=:DicData_code")
	public java.lang.String getCode() {
		return this.code;
	}

	public void setRemark(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.remark = value;
	}

	@WhereSQL(sql = "remark=:DicData_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setActive(java.lang.Integer value) {
		this.active = value;
	}

	@WhereSQL(sql = "active=:DicData_active")
	public java.lang.Integer getActive() {
		return this.active;
	}

	public void setTypekey(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.typekey = value;
	}

	@WhereSQL(sql = "typekey=:DicData_typekey")
	public java.lang.String getTypekey() {
		return this.typekey;
	}

	@WhereSQL(sql = "sortno=:DicData_sortno")
	public Integer getSortno() {
		return sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
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
	@WhereSQL(sql = "bak1=:DicData_bak1")
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
	@WhereSQL(sql = "bak2=:DicData_bak2")
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
	@WhereSQL(sql = "bak3=:DicData_bak3")
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
	@WhereSQL(sql = "bak4=:DicData_bak4")
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
	@WhereSQL(sql = "bak5=:DicData_bak5")
	public java.lang.String getBak5() {
		return this.bak5;
	}

	/**
	 * 自定义参数
	 * 
	 * @return
	 */
	@Transient
	public List<CmsProperty> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<CmsProperty> propertyList) {
		this.propertyList = propertyList;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("主键id[").append(getId()).append("],").append("名称[").append(getName())
				.append("],").append("编码[").append(getCode()).append("],").append("父ID[").append(getPid()).append("],")
				.append("排序[").append(getSortno()).append("],").append("描述[").append(getRemark()).append("],")
				.append("是否有效(0否,1是)[").append(getActive()).append("],").append("类型[").append(getTypekey()).append("],")
				.append("typekey=medicinedesType时使用，存储药品类型的图片路径[").append(getBak1()).append("],")
				.append("typekey=medicinedesType时使用，前台是否显示药品类型图标(0不显示，1显示)[").append(getBak2()).append("],")
				.append("bak3[").append(getBak3()).append("],").append("bak4[").append(getBak4()).append("],")
				.append("bak5[").append(getBak5()).append("],").toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DicData == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		DicData other = (DicData) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
