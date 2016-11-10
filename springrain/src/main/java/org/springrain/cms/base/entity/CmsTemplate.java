package org.springrain.cms.base.entity;

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
 * @version  2016-11-10 11:55:22
 * @see org.springrain.demo.entity.CmsTemplate
 */
@Table(name="cms_template")
public class CmsTemplate  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "模板表";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_DESCRIPTION = "备注";
	public static final String ALIAS_FTLFILE = "渲染使用的模板路径";
	public static final String ALIAS_IMGFILE = "缩略图路径路径";
	public static final String ALIAS_MODELTYPE = "0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)";
	public static final String ALIAS_USECOUNT = "使用次数";
	public static final String ALIAS_SITETYPE = "0微信订阅服务号,1wap,2网站      平台的linkURL";
	public static final String ALIAS_STATE = "状态 0不可用,1可用";
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
	 * 备注
	 */
	private java.lang.String description;
	/**
	 * 渲染使用的模板路径
	 */
	private java.lang.String ftlfile;
	/**
	 * 缩略图路径路径
	 */
	private java.lang.String imgfile;
	/**
	 * 0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)
	 */
	private java.lang.Integer modelType;
	/**
	 * 使用次数
	 */
	private java.lang.Integer usecount;
	/**
	 * 0微信订阅服务号,1wap,2网站      平台的linkURL
	 */
	private java.lang.Integer siteType;
	/**
	 * 状态 0不可用,1可用
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public CmsTemplate(){
	}

	public CmsTemplate(
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
     @WhereSQL(sql="id=:CmsTemplate_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:CmsTemplate_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setDescription(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.description = value;
	}
	
     @WhereSQL(sql="description=:CmsTemplate_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setFtlfile(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ftlfile = value;
	}
	
     @WhereSQL(sql="ftlfile=:CmsTemplate_ftlfile")
	public java.lang.String getFtlfile() {
		return this.ftlfile;
	}
	public void setImgfile(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.imgfile = value;
	}
	
     @WhereSQL(sql="imgfile=:CmsTemplate_imgfile")
	public java.lang.String getImgfile() {
		return this.imgfile;
	}
	public void setModelType(java.lang.Integer value) {
		this.modelType = value;
	}
	
     @WhereSQL(sql="modelType=:CmsTemplate_modelType")
	public java.lang.Integer getModelType() {
		return this.modelType;
	}
	public void setUsecount(java.lang.Integer value) {
		this.usecount = value;
	}
	
     @WhereSQL(sql="usecount=:CmsTemplate_usecount")
	public java.lang.Integer getUsecount() {
		return this.usecount;
	}
	public void setSiteType(java.lang.Integer value) {
		this.siteType = value;
	}
	
     @WhereSQL(sql="siteType=:CmsTemplate_siteType")
	public java.lang.Integer getSiteType() {
		return this.siteType;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:CmsTemplate_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("备注[").append(getDescription()).append("],")
			.append("渲染使用的模板路径[").append(getFtlfile()).append("],")
			.append("缩略图路径路径[").append(getImgfile()).append("],")
			.append("0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)[").append(getModelType()).append("],")
			.append("使用次数[").append(getUsecount()).append("],")
			.append("0微信订阅服务号,1wap,2网站      平台的linkURL[").append(getSiteType()).append("],")
			.append("状态 0不可用,1可用[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTemplate == false) return false;
		if(this == obj) return true;
		CmsTemplate other = (CmsTemplate)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
