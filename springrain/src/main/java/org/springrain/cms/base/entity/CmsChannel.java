package org.springrain.cms.base.entity;

import java.util.List;

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
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-18 11:53:33
 * @see org.springrain.demo.entity.CmsChannel
 */
@Table(name="cms_channel")
public class CmsChannel  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "栏目表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_PID = "pid";
	public static final String ALIAS_COMCODE = "comcode";
	public static final String ALIAS_SITEID = "网站ID";
	public static final String ALIAS_POSITIONLEVEL = "0导航,1-10个级别";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_KEYWORDS = "关键字";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_LOOKCOUNT = "打开次数";
	public static final String ALIAS_SORTNO = "排序";
	public static final String ALIAS_ACTIVE = "状态 0不可用,1可用";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * pid
	 */
	private java.lang.String pid;
	/**
	 * comcode
	 */
	private java.lang.String comcode;
	/**
	 * 网站ID
	 */
	private java.lang.String siteId;
	/**
	 * 0导航,1-10个级别
	 */
	private java.lang.Integer positionLevel;
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
	/**
	 * 排序
	 */
	private java.lang.Integer sortno;
	/**
	 * 状态 0不可用,1可用
	 */
	private java.lang.Integer active;
	//columns END 数据库字段结束
	
	private List<CmsChannel> leaf;
	
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
	public void setComcode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.comcode = value;
	}
	
     @WhereSQL(sql="comcode=:CmsChannel_comcode")
	public java.lang.String getComcode() {
		return this.comcode;
	}
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
     @WhereSQL(sql="siteId=:CmsChannel_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
	public void setPositionLevel(java.lang.Integer value) {
		this.positionLevel = value;
	}
	
     @WhereSQL(sql="positionLevel=:CmsChannel_positionLevel")
	public java.lang.Integer getPositionLevel() {
		return this.positionLevel;
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
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
     @WhereSQL(sql="sortno=:CmsChannel_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:CmsChannel_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("pid[").append(getPid()).append("],")
			.append("comcode[").append(getComcode()).append("],")
			.append("网站ID[").append(getSiteId()).append("],")
			.append("0导航,1-10个级别[").append(getPositionLevel()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("关键字[").append(getKeywords()).append("],")
			.append("描述[").append(getDescription()).append("],")
			.append("打开次数[").append(getLookcount()).append("],")
			.append("排序[").append(getSortno()).append("],")
			.append("状态 0不可用,1可用[").append(getActive()).append("],")
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
	
	
	@Transient
	public List<CmsChannel> getLeaf() {
		return leaf;
	}

	public void setLeaf(List<CmsChannel> leaf) {
		this.leaf = leaf;
	}
}

	
