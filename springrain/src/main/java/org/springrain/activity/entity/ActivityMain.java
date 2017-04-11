package org.springrain.activity.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.cms.entity.CmsContent;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * 活动信息
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-13 17:20:47
 * @see org.springrain.activity.entity.ActivityMain
 */
@Table(name="t_activity_main")
public class ActivityMain  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "活动信息，其他字段从cms_content表继承";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ACTIVITYSTARTTIME = "活动开始时间";
	public static final String ALIAS_ACTIVITYENDTIME = "活动结束时间";
	public static final String ALIAS_ACTIVITYADDRESS = "活动地址";
	public static final String ALIAS_ACTIVITYSCORE = "参与活动可得积分";
	public static final String ALIAS_TELPHONE = "联系电话";
	public static final String ALIAS_CATEGORY = "分类(为避免有活动的分类，通过 URL判断即可)";
	public static final String ALIAS_SITEID = "站点ID";
	public static final String ALIAS_BUSINESSID = "业务ID（默认和siteID一致，以后可能会要求在某个站点下的某个分类下）";
	public static final String ALIAS_SIGNUPSTARTTIME = "报名开始时间";
	public static final String ALIAS_SIGNUPENDTIME = "报名结束时间";
	public static final String ALIAS_TOTALNUMBER = "参与活动的总人数";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
	public static final String ALIAS_BAK4 = "bak4";
    */
	//date formats
	//public static final String FORMAT_ACTIVITYSTARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ACTIVITYENDTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SIGNUPSTARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SIGNUPENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	
	/**
	 * 活动开始时间
	 */
	private java.util.Date activityStartTime;
	/**
	 * 活动结束时间
	 */
	private java.util.Date activityEndTime;
	/**
	 * 活动地址
	 */
	private java.lang.String activityAddress;
	/**
	 * 参与活动可得积分
	 */
	private java.lang.Integer activityScore;
	/**
	 * 联系电话
	 */
	private java.lang.String telPhone;
	/**
	 * 分类(为避免有活动的分类，通过 URL判断即可)
	 */
	private java.lang.Integer category;
	/**
	 * 站点ID
	 */
	private java.lang.String siteId;
	/**
	 * 业务ID（默认和siteID一致，以后可能会要求在某个站点下的某个分类下）
	 */
	private java.lang.String businessId;
	/**
	 * 报名开始时间
	 */
	private java.util.Date signupStartTime;
	/**
	 * 报名结束时间
	 */
	private java.util.Date signupEndTime;
	/**
	 * 参与活动的总人数
	 */
	private java.lang.Integer totalNumber;
	/**
	 * bak1
	 */
	private java.lang.String bak1;
	/**
	 * bak2
	 */
	private java.lang.String bak2;
	/**
	 * bak3
	 */
	private java.lang.String bak3;
	/**
	 * bak4
	 */
	private java.lang.String bak4;
	//columns END 数据库字段结束
	
	private CmsContent cmsContent;
	
	//concstructor

	public ActivityMain(){
	}

	public ActivityMain(
		java.lang.String id
	){
		this.id = id;
	}
	
	@Transient
	public CmsContent getCmsContent() {
		return cmsContent;
	}

	public void setCmsContent(CmsContent cmsContent) {
		this.cmsContent = cmsContent;
	}

	@Id
	@WhereSQL(sql="id=:ActivityMain_id")
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

		/**
		 * 活动开始时间
		 */
	public void setActivityStartTime(java.util.Date value) {
		this.activityStartTime = value;
	}
	
	
	
	/**
	 * 活动开始时间
	 */
     @WhereSQL(sql="activityStartTime=:ActivityMain_activityStartTime")
	public java.util.Date getActivityStartTime() {
		return this.activityStartTime;
	}
	
	/**
	 * 活动结束时间
	 */
	public void setActivityEndTime(java.util.Date value) {
		this.activityEndTime = value;
	}
	
	
	
	/**
	 * 活动结束时间
	 */
     @WhereSQL(sql="activityEndTime=:ActivityMain_activityEndTime")
	public java.util.Date getActivityEndTime() {
		return this.activityEndTime;
	}
		/**
		 * 活动地址
		 */
	public void setActivityAddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.activityAddress = value;
	}
	
	
	
	/**
	 * 活动地址
	 */
     @WhereSQL(sql="activityAddress=:ActivityMain_activityAddress")
	public java.lang.String getActivityAddress() {
		return this.activityAddress;
	}
		/**
		 * 参与活动可得积分
		 */
	public void setActivityScore(java.lang.Integer value) {
		this.activityScore = value;
	}
	
	
	
	/**
	 * 参与活动可得积分
	 */
     @WhereSQL(sql="activityScore=:ActivityMain_activityScore")
	public java.lang.Integer getActivityScore() {
		return this.activityScore;
	}
		/**
		 * 联系电话
		 */
	public void setTelPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.telPhone = value;
	}
	
	
	
	/**
	 * 联系电话
	 */
     @WhereSQL(sql="telPhone=:ActivityMain_telPhone")
	public java.lang.String getTelPhone() {
		return this.telPhone;
	}
		/**
		 * 分类(为避免有活动的分类，通过 URL判断即可)
		 */
	public void setCategory(java.lang.Integer value) {
		this.category = value;
	}
	
	
	
	/**
	 * 分类(为避免有活动的分类，通过 URL判断即可)
	 */
     @WhereSQL(sql="category=:ActivityMain_category")
	public java.lang.Integer getCategory() {
		return this.category;
	}
		/**
		 * 站点ID
		 */
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
	
	
	/**
	 * 站点ID
	 */
     @WhereSQL(sql="siteId=:ActivityMain_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
		/**
		 * 业务ID（默认和siteID一致，以后可能会要求在某个站点下的某个分类下）
		 */
	public void setBusinessId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.businessId = value;
	}
	
	
	
	/**
	 * 业务ID（默认和siteID一致，以后可能会要求在某个站点下的某个分类下）
	 */
     @WhereSQL(sql="businessId=:ActivityMain_businessId")
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
		/*
	public String getsignupStartTimeString() {
		return DateUtils.convertDate2String(FORMAT_SIGNUPSTARTTIME, getsignupStartTime());
	}
	public void setsignupStartTimeString(String value) throws ParseException{
		setsignupStartTime(DateUtils.convertString2Date(FORMAT_SIGNUPSTARTTIME,value));
	}*/
	
		/**
		 * 报名开始时间
		 */
	public void setSignupStartTime(java.util.Date value) {
		this.signupStartTime = value;
	}
	
	
	
	/**
	 * 报名开始时间
	 */
     @WhereSQL(sql="signupStartTime=:ActivityMain_signupStartTime")
	public java.util.Date getSignupStartTime() {
		return this.signupStartTime;
	}
		/*
	public String getsignupEndTimeString() {
		return DateUtils.convertDate2String(FORMAT_SIGNUPENDTIME, getsignupEndTime());
	}
	public void setsignupEndTimeString(String value) throws ParseException{
		setsignupEndTime(DateUtils.convertString2Date(FORMAT_SIGNUPENDTIME,value));
	}*/
	
		/**
		 * 报名结束时间
		 */
	public void setSignupEndTime(java.util.Date value) {
		this.signupEndTime = value;
	}
	
	
	
	/**
	 * 报名结束时间
	 */
     @WhereSQL(sql="signupEndTime=:ActivityMain_signupEndTime")
	public java.util.Date getSignupEndTime() {
		return this.signupEndTime;
	}
		/**
		 * 参与活动的总人数
		 */
	public void setTotalNumber(java.lang.Integer value) {
		this.totalNumber = value;
	}
	
	
	
	/**
	 * 参与活动的总人数
	 */
     @WhereSQL(sql="totalNumber=:ActivityMain_totalNumber")
	public java.lang.Integer getTotalNumber() {
		return this.totalNumber;
	}
		/**
		 * bak1
		 */
	public void setBak1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak1 = value;
	}
	
	
	
	/**
	 * bak1
	 */
     @WhereSQL(sql="bak1=:ActivityMain_bak1")
	public java.lang.String getBak1() {
		return this.bak1;
	}
		/**
		 * bak2
		 */
	public void setBak2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak2 = value;
	}
	
	
	
	/**
	 * bak2
	 */
     @WhereSQL(sql="bak2=:ActivityMain_bak2")
	public java.lang.String getBak2() {
		return this.bak2;
	}
		/**
		 * bak3
		 */
	public void setBak3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak3 = value;
	}
	
	
	
	/**
	 * bak3
	 */
     @WhereSQL(sql="bak3=:ActivityMain_bak3")
	public java.lang.String getBak3() {
		return this.bak3;
	}
		/**
		 * bak4
		 */
	public void setBak4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak4 = value;
	}
	
	
	
	/**
	 * bak4
	 */
     @WhereSQL(sql="bak4=:ActivityMain_bak4")
	public java.lang.String getBak4() {
		return this.bak4;
	}
	
    @Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("活动开始时间[").append(getActivityStartTime()).append("],")
			.append("活动结束时间[").append(getActivityEndTime()).append("],")
			.append("活动地址[").append(getActivityAddress()).append("],")
			.append("参与活动可得积分[").append(getActivityScore()).append("],")
			.append("联系电话[").append(getTelPhone()).append("],")
			.append("分类(为避免有活动的分类，通过 URL判断即可)[").append(getCategory()).append("],")
			.append("站点ID[").append(getSiteId()).append("],")
			.append("业务ID（默认和siteID一致，以后可能会要求在某个站点下的某个分类下）[").append(getBusinessId()).append("],")
			.append("报名开始时间[").append(getSignupStartTime()).append("],")
			.append("报名结束时间[").append(getSignupEndTime()).append("],")
			.append("参与活动的总人数[").append(getTotalNumber()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
			.append("bak4[").append(getBak4()).append("],")
			.toString();
	}
	
    @Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
    @Override
	public boolean equals(Object obj) {
		if(obj instanceof ActivityMain == false){
			return false;
		} 
		if(this == obj){
			return true;
		} 
			
		ActivityMain other = (ActivityMain)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
