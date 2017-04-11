package org.springrain.activity.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * 活动参与 人员
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-13 16:48:04
 * @see org.springrain.activity.entity.ActivityPartIn
 */
@Table(name="t_activity_part_in")
public class ActivityPartIn  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "ActivityPartIn";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "参与人姓名";
	public static final String ALIAS_TELPHONE = "参与人电话 ";
	public static final String ALIAS_PARTINTIME = "参与时间";
	public static final String ALIAS_ACTIVITYID = "活动Id";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_PARTINTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 参与人姓名
	 */
	private java.lang.String name;
	/**
	 * 参与人电话 
	 */
	private java.lang.String telPhone;
	/**
	 * 参与时间
	 */
	private java.util.Date partInTime;
	/**
	 * 活动Id
	 */
	private java.lang.String activityId;
	/**
	 * 用户ID
	 */
	private java.lang.String userId;
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
	//columns END 数据库字段结束
	
	//concstructor

	public ActivityPartIn(){
	}

	public ActivityPartIn(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
		/**
		 * id
		 */
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	
	
	/**
	 * id
	 */
	@Id
     @WhereSQL(sql="id=:ActivityPartIn_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 参与人姓名
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * 参与人姓名
	 */
     @WhereSQL(sql="name=:ActivityPartIn_name")
	public java.lang.String getName() {
		return this.name;
	}
		/**
		 * 参与人电话 
		 */
	public void setTelPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.telPhone = value;
	}
	
	
	
	/**
	 * 参与人电话 
	 */
     @WhereSQL(sql="telPhone=:ActivityPartIn_telPhone")
	public java.lang.String getTelPhone() {
		return this.telPhone;
	}
		/*
	public String getpartInTimeString() {
		return DateUtils.convertDate2String(FORMAT_PARTINTIME, getpartInTime());
	}
	public void setpartInTimeString(String value) throws ParseException{
		setpartInTime(DateUtils.convertString2Date(FORMAT_PARTINTIME,value));
	}*/
	
		/**
		 * 参与时间
		 */
	public void setPartInTime(java.util.Date value) {
		this.partInTime = value;
	}
	
	
	
	/**
	 * 参与时间
	 */
     @WhereSQL(sql="partInTime=:ActivityPartIn_partInTime")
	public java.util.Date getPartInTime() {
		return this.partInTime;
	}
		/**
		 * 活动Id
		 */
	public void setActivityId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.activityId = value;
	}
	
	
	
	/**
	 * 活动Id
	 */
     @WhereSQL(sql="activityId=:ActivityPartIn_activityId")
	public java.lang.String getActivityId() {
		return this.activityId;
	}
		/**
		 * 用户ID
		 */
	public void setUserId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userId = value;
	}
	
	
	
	/**
	 * 用户ID
	 */
     @WhereSQL(sql="userId=:ActivityPartIn_userId")
	public java.lang.String getUserId() {
		return this.userId;
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
     @WhereSQL(sql="bak1=:ActivityPartIn_bak1")
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
     @WhereSQL(sql="bak2=:ActivityPartIn_bak2")
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
     @WhereSQL(sql="bak3=:ActivityPartIn_bak3")
	public java.lang.String getBak3() {
		return this.bak3;
	}
	
     @Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("参与人姓名[").append(getName()).append("],")
			.append("参与人电话 [").append(getTelPhone()).append("],")
			.append("参与时间[").append(getPartInTime()).append("],")
			.append("活动Id[").append(getActivityId()).append("],")
			.append("用户ID[").append(getUserId()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
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
		if(obj instanceof ActivityPartIn == false){
			return false;
		} 
		if(this == obj){
			return true;
		} 
		ActivityPartIn other = (ActivityPartIn)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
