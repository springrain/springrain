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
 * @version  2016-11-12 10:44:59
 * @see org.springrain.demo.entity.CmsSiteChannel
 */
@Table(name="cms_site_channel")
public class CmsSiteChannel  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "网站栏目中间表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SITEID = "网站ID";
	public static final String ALIAS_CHANNELID = "channelId";
	public static final String ALIAS_POSITIONLEVEL = "0导航,1-10个级别";
	public static final String ALIAS_CHANNELTYPE = "栏目类型分为 导航菜单(0) 内容类似标签(1) ";
	public static final String ALIAS_SORTNO = "排序";
	public static final String ALIAS_STATE = "状态 0不可用,1可用";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 网站ID
	 */
	private java.lang.String siteId;
	/**
	 * channelId
	 */
	private java.lang.String channelId;
	/**
	 * 0导航,1-10个级别
	 */
	private java.lang.Integer positionLevel;
	/**
	 * 栏目类型分为 导航菜单(0) 内容类似标签(1) 
	 */
	private java.lang.Integer channelType;
	/**
	 * 排序
	 */
	private java.lang.Integer sortno;
	/**
	 * 状态 0不可用,1可用
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public CmsSiteChannel(){
	}

	public CmsSiteChannel(
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
     @WhereSQL(sql="id=:CmsSiteChannel_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
     @WhereSQL(sql="siteId=:CmsSiteChannel_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
	public void setChannelId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.channelId = value;
	}
	
     @WhereSQL(sql="channelId=:CmsSiteChannel_channelId")
	public java.lang.String getChannelId() {
		return this.channelId;
	}
	public void setPositionLevel(java.lang.Integer value) {
		this.positionLevel = value;
	}
	
     @WhereSQL(sql="positionLevel=:CmsSiteChannel_positionLevel")
	public java.lang.Integer getPositionLevel() {
		return this.positionLevel;
	}
	public void setChannelType(java.lang.Integer value) {
		this.channelType = value;
	}
	
     @WhereSQL(sql="channelType=:CmsSiteChannel_channelType")
	public java.lang.Integer getChannelType() {
		return this.channelType;
	}
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
     @WhereSQL(sql="sortno=:CmsSiteChannel_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:CmsSiteChannel_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("网站ID[").append(getSiteId()).append("],")
			.append("channelId[").append(getChannelId()).append("],")
			.append("0导航,1-10个级别[").append(getPositionLevel()).append("],")
			.append("栏目类型分为 导航菜单(0) 内容类似标签(1) [").append(getChannelType()).append("],")
			.append("排序[").append(getSortno()).append("],")
			.append("状态 0不可用,1可用[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsSiteChannel == false) return false;
		if(this == obj) return true;
		CmsSiteChannel other = (CmsSiteChannel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
