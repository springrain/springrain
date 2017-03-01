package org.springrain.cms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
import org.springrain.frame.util.JsonUtils;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-24 10:06:38
 * @see org.springrain.cms.base.entity.CmsBanner
 */
@Table(name="cms_banner")
public class CmsBanner  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "CmsBanner";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SITEID = "站点ID";
	public static final String ALIAS_BUSINESSID = "业务id";
	public static final String ALIAS_IMGSRC = "图片路径";
	public static final String ALIAS_IMGALT = "图片提示语";
	public static final String ALIAS_REDIRECTURL = "跳转路径";
	public static final String ALIAS_SORTNO = "排序";
	public static final String ALIAS_ACTIVE = "0 禁用  1启用";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 站点ID
	 */
	private java.lang.String siteId;
	/**
	 * 业务id
	 */
	private java.lang.String businessId;
	/**
	 * 图片路径
	 */
	private java.lang.String imgSrc;
	/**
	 * 图片提示语
	 */
	private java.lang.String imgAlt;
	/**
	 * 跳转路径
	 */
	private java.lang.String redirectUrl;
	/**
	 * 排序
	 */
	private java.lang.Integer sortno;
	/**
	 * 0 禁用  1启用
	 */
	private java.lang.Integer active;
	//columns END 数据库字段结束
	private List<Map<String, String>> imgMap;
	
	//concstructor

	public CmsBanner(){
	}

	public CmsBanner(
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
     @WhereSQL(sql="id=:CmsBanner_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
     @WhereSQL(sql="siteId=:CmsBanner_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
	public void setBusinessId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.businessId = value;
	}
	
     @WhereSQL(sql="businessId=:CmsBanner_businessId")
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
	@SuppressWarnings("unchecked")
	public void setImgSrc(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.imgSrc = value;
		this.imgMap = JsonUtils.readValue(value, ArrayList.class);
	}
	
     @WhereSQL(sql="imgSrc=:CmsBanner_imgSrc")
	public java.lang.String getImgSrc() {
		return this.imgSrc;
	}
	public void setImgAlt(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.imgAlt = value;
		
	}
	
     @WhereSQL(sql="imgAlt=:CmsBanner_imgAlt")
	public java.lang.String getImgAlt() {
		return this.imgAlt;
	}
	public void setRedirectUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.redirectUrl = value;
	}
	
     @WhereSQL(sql="redirectUrl=:CmsBanner_redirectUrl")
	public java.lang.String getRedirectUrl() {
		return this.redirectUrl;
	}
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
     @WhereSQL(sql="sortno=:CmsBanner_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:CmsBanner_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	@Transient
	public List<Map<String, String>> getImgMap() {
		return imgMap;
	}

	public void setImgMap(List<Map<String, String>> imgMap) {
		this.imgMap = imgMap;
	}

	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("站点ID[").append(getSiteId()).append("],")
			.append("业务id[").append(getBusinessId()).append("],")
			.append("图片路径[").append(getImgSrc()).append("],")
			.append("图片提示语[").append(getImgAlt()).append("],")
			.append("跳转路径[").append(getRedirectUrl()).append("],")
			.append("排序[").append(getSortno()).append("],")
			.append("0 禁用  1启用[").append(getActive()).append("],")
			.toString();
	}
	
	

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsBanner == false) return false;
		if(this == obj) return true;
		CmsBanner other = (CmsBanner)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
