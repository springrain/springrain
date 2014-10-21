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
 * @version  2014-10-21 09:36:14
 * @see org.springrain.cms.entity.CmsChannel
 */
@Table(name="cms_channel")
public class CmsChannel  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "栏目表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PID = "上级Id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_KEYWORDS = "关键字";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_LOOKCOUNT = "打开次数";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 上级Id
	 */
	private java.lang.String pid;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 关键字
	 */
	private java.lang.String keywords;
	/**
	 * 描述
	 */
	private java.lang.String description;
	/**
	 * 打开次数
	 */
	private java.lang.Integer lookcount;
	//columns END 数据库字段结束
	
	//concstructor

	public CmsChannel(){
	}

	public CmsChannel(
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
     @WhereSQL(sql="id=:CmsChannel_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setPid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pid = value;
	}
	
     @WhereSQL(sql="pid=:CmsChannel_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:CmsChannel_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:CmsChannel_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setKeywords(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.keywords = value;
	}
	
     @WhereSQL(sql="keywords=:CmsChannel_keywords")
	public java.lang.String getKeywords() {
		return this.keywords;
	}
	public void setDescription(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.description = value;
	}
	
     @WhereSQL(sql="description=:CmsChannel_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setLookcount(java.lang.Integer value) {
		this.lookcount = value;
	}
	
     @WhereSQL(sql="lookcount=:CmsChannel_lookcount")
	public java.lang.Integer getLookcount() {
		return this.lookcount;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("上级Id[").append(getPid()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("关键字[").append(getKeywords()).append("],")
			.append("描述[").append(getDescription()).append("],")
			.append("打开次数[").append(getLookcount()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsChannel == false) return false;
		if(this == obj) return true;
		CmsChannel other = (CmsChannel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
