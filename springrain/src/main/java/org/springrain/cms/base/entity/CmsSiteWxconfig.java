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
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-12 10:44:59
 * @see org.springrain.demo.entity.CmsSiteWxconfig
 */
@Table(name="cms_site_wxconfig")
public class CmsSiteWxconfig  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "微信号需要的配置信息";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SITEID = "站点Id";
	public static final String ALIAS_APPID = "开发者Id";
	public static final String ALIAS_APPSECRET = "应用密钥";
	public static final String ALIAS_TOKEN = "开发者Id";
	public static final String ALIAS_ENCODINGAESKEY = "消息加解密密钥";
	public static final String ALIAS_WXID = "原始ID";
	public static final String ALIAS_ACTIVE = "状态 0不可用,1可用";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 站点Id
	 */
	private java.lang.String siteId;
	/**
	 * 开发者Id
	 */
	private java.lang.String appId;
	/**
	 * 应用密钥
	 */
	private java.lang.String appSecret;
	/**
	 * 开发者Id
	 */
	private java.lang.String token;
	/**
	 * 消息加解密密钥
	 */
	private java.lang.String encodingAESKey;
	/**
	 * 原始ID
	 */
	private java.lang.String wxId;
	/**
	 * 状态 0不可用,1可用
	 */
	private java.lang.Integer active;
	//columns END 数据库字段结束
	
	//concstructor

	public CmsSiteWxconfig(){
	}

	public CmsSiteWxconfig(
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
     @WhereSQL(sql="id=:CmsSiteWxconfig_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
     @WhereSQL(sql="siteId=:CmsSiteWxconfig_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
	public void setAppId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.appId = value;
	}
	
     @WhereSQL(sql="appId=:CmsSiteWxconfig_appId")
	public java.lang.String getAppId() {
		return this.appId;
	}
	public void setAppSecret(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.appSecret = value;
	}
	
     @WhereSQL(sql="appSecret=:CmsSiteWxconfig_appSecret")
	public java.lang.String getAppSecret() {
		return this.appSecret;
	}
	public void setToken(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.token = value;
	}
	
     @WhereSQL(sql="token=:CmsSiteWxconfig_token")
	public java.lang.String getToken() {
		return this.token;
	}
	public void setEncodingAESKey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.encodingAESKey = value;
	}
	
     @WhereSQL(sql="encodingAESKey=:CmsSiteWxconfig_encodingAESKey")
	public java.lang.String getEncodingAESKey() {
		return this.encodingAESKey;
	}
	public void setWxId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wxId = value;
	}
	
     @WhereSQL(sql="wxId=:CmsSiteWxconfig_wxId")
	public java.lang.String getWxId() {
		return this.wxId;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:CmsSiteWxconfig_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("站点Id[").append(getSiteId()).append("],")
			.append("开发者Id[").append(getAppId()).append("],")
			.append("应用密钥[").append(getAppSecret()).append("],")
			.append("开发者Id[").append(getToken()).append("],")
			.append("消息加解密密钥[").append(getEncodingAESKey()).append("],")
			.append("原始ID[").append(getWxId()).append("],")
			.append("状态 0不可用,1可用[").append(getActive()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsSiteWxconfig == false) return false;
		if(this == obj) return true;
		CmsSiteWxconfig other = (CmsSiteWxconfig)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
