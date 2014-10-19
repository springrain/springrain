package org.springrain.cms.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2014-10-19 11:17:53
 * @see org.springrain.cms.entity.CmsPropertyvalue
 */
@Table(name="cms_propertyvalue")
public class CmsPropertyvalue  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "属性表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PROPERTYID = "propertyId";
	public static final String ALIAS_VALUE = "value";
	public static final String ALIAS_SITEID = "siteId";
	public static final String ALIAS_BUSINESSID = "业务Id";
	public static final String ALIAS_SORT = "排序";
	public static final String ALIAS_STATE = "0失效,1有效";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * propertyId
	 */
	private java.lang.Integer propertyId;
	/**
	 * value
	 */
	private java.lang.String value;
	/**
	 * siteId
	 */
	private java.lang.String siteId;
	/**
	 * 业务Id
	 */
	private java.lang.String businessId;
	/**
	 * 排序
	 */
	private java.lang.Integer sort;
	/**
	 * 0失效,1有效
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public CmsPropertyvalue(){
	}

	public CmsPropertyvalue(
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
     @WhereSQL(sql="id=:CmsPropertyvalue_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setPropertyId(java.lang.Integer value) {
		this.propertyId = value;
	}
	
     @WhereSQL(sql="propertyId=:CmsPropertyvalue_propertyId")
	public java.lang.Integer getPropertyId() {
		return this.propertyId;
	}
	public void setValue(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.value = value;
	}
	
     @WhereSQL(sql="value=:CmsPropertyvalue_value")
	public java.lang.String getValue() {
		return this.value;
	}
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
     @WhereSQL(sql="siteId=:CmsPropertyvalue_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
	public void setBusinessId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.businessId = value;
	}
	
     @WhereSQL(sql="businessId=:CmsPropertyvalue_businessId")
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}
	
     @WhereSQL(sql="sort=:CmsPropertyvalue_sort")
	public java.lang.Integer getSort() {
		return this.sort;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:CmsPropertyvalue_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("propertyId[").append(getPropertyId()).append("],")
			.append("value[").append(getValue()).append("],")
			.append("siteId[").append(getSiteId()).append("],")
			.append("业务Id[").append(getBusinessId()).append("],")
			.append("排序[").append(getSort()).append("],")
			.append("0失效,1有效[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsPropertyvalue == false) return false;
		if(this == obj) return true;
		CmsPropertyvalue other = (CmsPropertyvalue)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
