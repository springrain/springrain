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
 * @version  2016-11-12 11:20:24
 * @see org.springrain.demo.entity.CmsAttachment
 */
@Table(name="cms_attachment")
public class CmsAttachment  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "附件表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SITEID = "站点Id";
	public static final String ALIAS_BUSINESSID = "业务Id";
	public static final String ALIAS_PROPERTYCODE = "扩展属性code";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_FILEPATH = "文件物理路径";
	public static final String ALIAS_FILESUFFIX = "文件后缀";
	public static final String ALIAS_FILEURL = "文件路径";
	public static final String ALIAS_THUMBNAIL = "缩略图";
	public static final String ALIAS_CREATEDATE = "创建时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_MODELTYPE = "0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)";
	public static final String ALIAS_SORTNO = "排序";
	public static final String ALIAS_LOOKCOUNT = "打开次数";
	public static final String ALIAS_ACTIVE = "状态 0不可用,1可用";
    */
	//date formats
	//public static final String FORMAT_CREATEDATE = DateUtils.DATETIME_FORMAT;
	
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
	 * 业务Id
	 */
	private java.lang.String businessId;
	/**
	 * 扩展属性code
	 */
	private java.lang.String propertyCode;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 文件物理路径
	 */
	private java.lang.String filepath;
	/**
	 * 文件后缀
	 */
	private java.lang.String filesuffix;
	/**
	 * 文件路径
	 */
	private java.lang.String fileurl;
	/**
	 * 缩略图
	 */
	private java.lang.String thumbnail;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)
	 */
	private java.lang.Integer modelType;
	/**
	 * 排序
	 */
	private java.lang.Integer sortno;
	/**
	 * 打开次数
	 */
	private java.lang.Integer lookcount;
	/**
	 * 状态 0不可用,1可用
	 */
	private java.lang.Integer active;
	//columns END 数据库字段结束
	
	//concstructor

	public CmsAttachment(){
	}

	public CmsAttachment(
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
     @WhereSQL(sql="id=:CmsAttachment_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
     @WhereSQL(sql="siteId=:CmsAttachment_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
	public void setBusinessId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.businessId = value;
	}
	
     @WhereSQL(sql="businessId=:CmsAttachment_businessId")
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
	public void setPropertyCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.propertyCode = value;
	}
	
     @WhereSQL(sql="propertyCode=:CmsAttachment_propertyCode")
	public java.lang.String getPropertyCode() {
		return this.propertyCode;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:CmsAttachment_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setFilepath(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.filepath = value;
	}
	
     @WhereSQL(sql="filepath=:CmsAttachment_filepath")
	public java.lang.String getFilepath() {
		return this.filepath;
	}
	public void setFilesuffix(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.filesuffix = value;
	}
	
     @WhereSQL(sql="filesuffix=:CmsAttachment_filesuffix")
	public java.lang.String getFilesuffix() {
		return this.filesuffix;
	}
	public void setFileurl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fileurl = value;
	}
	
     @WhereSQL(sql="fileurl=:CmsAttachment_fileurl")
	public java.lang.String getFileurl() {
		return this.fileurl;
	}
	public void setThumbnail(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.thumbnail = value;
	}
	
     @WhereSQL(sql="thumbnail=:CmsAttachment_thumbnail")
	public java.lang.String getThumbnail() {
		return this.thumbnail;
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
	
     @WhereSQL(sql="createDate=:CmsAttachment_createDate")
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:CmsAttachment_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setModelType(java.lang.Integer value) {
		this.modelType = value;
	}
	
     @WhereSQL(sql="modelType=:CmsAttachment_modelType")
	public java.lang.Integer getModelType() {
		return this.modelType;
	}
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
     @WhereSQL(sql="sortno=:CmsAttachment_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	public void setLookcount(java.lang.Integer value) {
		this.lookcount = value;
	}
	
     @WhereSQL(sql="lookcount=:CmsAttachment_lookcount")
	public java.lang.Integer getLookcount() {
		return this.lookcount;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:CmsAttachment_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("站点Id[").append(getSiteId()).append("],")
			.append("业务Id[").append(getBusinessId()).append("],")
			.append("扩展属性code[").append(getPropertyCode()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("文件物理路径[").append(getFilepath()).append("],")
			.append("文件后缀[").append(getFilesuffix()).append("],")
			.append("文件路径[").append(getFileurl()).append("],")
			.append("缩略图[").append(getThumbnail()).append("],")
			.append("创建时间[").append(getCreateDate()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)[").append(getModelType()).append("],")
			.append("排序[").append(getSortno()).append("],")
			.append("打开次数[").append(getLookcount()).append("],")
			.append("状态 0不可用,1可用[").append(getActive()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsAttachment == false) return false;
		if(this == obj) return true;
		CmsAttachment other = (CmsAttachment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
