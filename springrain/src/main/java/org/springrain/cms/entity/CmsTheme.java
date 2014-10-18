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
 * @version  2014-10-18 15:49:22
 * @see org.springrain.demo.entity.CmsTheme
 */
@Table(name="cms_theme")
public class CmsTheme  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "CmsTheme";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_BUSINESSTYPEID = "行业类型";
	public static final String ALIAS_DESCRIPTION = "备注";
	public static final String ALIAS_THEMEDIR = "主题路径";
	public static final String ALIAS_USECOUNT = "使用次数";
	public static final String ALIAS_OSTYPE = "pc,pad,mobile,app 四个平台的linkURL";
    */
	//date formats
	
	//columns START
	/**
	 * ID
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 行业类型
	 */
	private java.lang.String businessTypeId;
	/**
	 * 备注
	 */
	private java.lang.String description;
	/**
	 * 主题路径
	 */
	private java.lang.String themedir;
	/**
	 * 使用次数
	 */
	private java.lang.Integer usecount;
	/**
	 * pc,pad,mobile,app 四个平台的linkURL
	 */
	private java.lang.String ostype;
	//columns END 数据库字段结束
	
	//concstructor

	public CmsTheme(){
	}

	public CmsTheme(
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
     @WhereSQL(sql="id=:CmsTheme_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:CmsTheme_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setBusinessTypeId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.businessTypeId = value;
	}
	
     @WhereSQL(sql="businessTypeId=:CmsTheme_businessTypeId")
	public java.lang.String getBusinessTypeId() {
		return this.businessTypeId;
	}
	public void setDescription(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.description = value;
	}
	
     @WhereSQL(sql="description=:CmsTheme_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setThemedir(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.themedir = value;
	}
	
     @WhereSQL(sql="themedir=:CmsTheme_themedir")
	public java.lang.String getThemedir() {
		return this.themedir;
	}
	public void setUsecount(java.lang.Integer value) {
		this.usecount = value;
	}
	
     @WhereSQL(sql="usecount=:CmsTheme_usecount")
	public java.lang.Integer getUsecount() {
		return this.usecount;
	}
	public void setOstype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ostype = value;
	}
	
     @WhereSQL(sql="ostype=:CmsTheme_ostype")
	public java.lang.String getOstype() {
		return this.ostype;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("行业类型[").append(getBusinessTypeId()).append("],")
			.append("备注[").append(getDescription()).append("],")
			.append("主题路径[").append(getThemedir()).append("],")
			.append("使用次数[").append(getUsecount()).append("],")
			.append("pc,pad,mobile,app 四个平台的linkURL[").append(getOstype()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTheme == false) return false;
		if(this == obj) return true;
		CmsTheme other = (CmsTheme)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
