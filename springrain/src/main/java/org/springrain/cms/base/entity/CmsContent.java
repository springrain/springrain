package org.springrain.cms.base.entity;

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
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-12 10:44:56
 * @see org.springrain.demo.entity.CmsContent
 */
@Table(name="cms_content")
public class CmsContent  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "内容表";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_KEYWORDS = "关键字";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_MINTITLE = "小标题";
	public static final String ALIAS_LOOKCOUNT = "打开次数";
	public static final String ALIAS_CREATEPERSON = "创建人";
	public static final String ALIAS_CREATEDATE = "创建时间";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_SOURCE = "来源";
	public static final String ALIAS_SOURCEURL = "来源地址";
	public static final String ALIAS_SORTNO = "排序";
	public static final String ALIAS_ACTIVE = "状态 0不可用,1可用";
    */
	//date formats
	//public static final String FORMAT_CREATEDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * ID
	 */
	private java.lang.String id;
	/**
	 * title
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
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 小标题
	 */
	private java.lang.String mintitle;
	/**
	 * 打开次数
	 */
	private java.lang.Integer lookcount;
	/**
	 * 创建人
	 */
	private java.lang.String createPerson;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 内容
	 */
	private java.lang.String content;
	/**
	 * 来源
	 */
	private java.lang.String source;
	/**
	 * 来源地址
	 */
	private java.lang.String sourceurl;

	//columns END 数据库字段结束
	
	//站点ID
	private String siteId;
	//栏目Id
	private java.lang.String channelId;
	/**
	 * 排序
	 */
	private java.lang.Integer sortno;
	/**
	 * 状态 0不可用,1可用
	 */
	private java.lang.Integer active;

	
	//concstructor

	public CmsContent(){
	}

	public CmsContent(
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
     @WhereSQL(sql="id=:CmsContent_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:CmsContent_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setKeywords(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.keywords = value;
	}
	
     @WhereSQL(sql="keywords=:CmsContent_keywords")
	public java.lang.String getKeywords() {
		return this.keywords;
	}
	public void setDescription(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.description = value;
	}
	
     @WhereSQL(sql="description=:CmsContent_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:CmsContent_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setMintitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mintitle = value;
	}
	
     @WhereSQL(sql="mintitle=:CmsContent_mintitle")
	public java.lang.String getMintitle() {
		return this.mintitle;
	}
	public void setLookcount(java.lang.Integer value) {
		this.lookcount = value;
	}
	
     @WhereSQL(sql="lookcount=:CmsContent_lookcount")
	public java.lang.Integer getLookcount() {
		return this.lookcount;
	}
	public void setCreatePerson(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.createPerson = value;
	}
	
     @WhereSQL(sql="createPerson=:CmsContent_createPerson")
	public java.lang.String getCreatePerson() {
		return this.createPerson;
	}
		/*
	public String getcreateDateString() {
		return DateUtils.convertDate2String(FORMAT_CREATEDATE, getcreateDate());
	}
	public void setcreateDateString(String value) throws ParseException{
		setcreateDate(DateUtils.convertString2Date(FORMAT_CREATEDATE,value));
	}*/
	
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
     @WhereSQL(sql="createDate=:CmsContent_createDate")
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:CmsContent_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setSource(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.source = value;
	}
	
     @WhereSQL(sql="source=:CmsContent_source")
	public java.lang.String getSource() {
		return this.source;
	}
	public void setSourceurl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sourceurl = value;
	}
	
     @WhereSQL(sql="sourceurl=:CmsContent_sourceurl")
	public java.lang.String getSourceurl() {
		return this.sourceurl;
	}
    
 	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
 	 @WhereSQL(sql="active=:CmsContent_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	
     
     
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("title[").append(getTitle()).append("],")
			.append("关键字[").append(getKeywords()).append("],")
			.append("描述[").append(getDescription()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("小标题[").append(getMintitle()).append("],")
			.append("打开次数[").append(getLookcount()).append("],")
			.append("创建人[").append(getCreatePerson()).append("],")
			.append("创建时间[").append(getCreateDate()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("来源[").append(getSource()).append("],")
			.append("来源地址[").append(getSourceurl()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsContent == false) return false;
		if(this == obj) return true;
		CmsContent other = (CmsContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
    public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	@Transient
	public java.lang.String getChannelId() {
		return channelId;
	}

	public void setChannelId(java.lang.String channelId) {
		this.channelId = channelId;
	}
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	@Transient
	public java.lang.Integer getSortno() {
		return this.sortno;
	}

}

	
