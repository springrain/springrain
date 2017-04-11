package org.springrain.police.entity;

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
 * @version  2017-03-23 09:35:23
 * @see org.springrain.police.entity.base.entity.Queryname
 */
@Table(name="t_queryname")
public class Queryname  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Queryname";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_SITEID = "站点id";
	public static final String ALIAS_BUSSINISSID = "栏目id";
	public static final String ALIAS_NAME = "名字";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_CITY = "所属城市";
	public static final String ALIAS_DISTRICT = "所属区";
	public static final String ALIAS_YEAR = "出生年";
	public static final String ALIAS_MONTH = "出生月";
	public static final String ALIAS_DAY = "出生日";
	public static final String ALIAS_LEADTIME = "数据导入时间";
	public static final String ALIAS_RESERVEA = "预留字段A";
	public static final String ALIAS_RESERVEB = "预留字段B";
    */
	//date formats
	//public static final String FORMAT_LEADTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * ID
	 */
	private java.lang.String id;
	/**
	 * 站点id
	 */
	private java.lang.String siteId;
	/**
	 * 栏目id
	 */
	private java.lang.String businessId;
	/**
	 * 名字
	 */
	private java.lang.String name;
	/**
	 * 性别(0:女 1:男)
	 */
	private java.lang.String sex;
	/**
	 * 所属城市
	 */
	private java.lang.String city;
	/**
	 * 所属区
	 */
	private java.lang.String district;
	/**
	 * 出生年月日
	 */
	private java.lang.String birthday;
	
	/**
	 * 数据导入时间
	 */
	private java.util.Date leadTime;
	/**
	 * 预留字段A
	 */
	private java.lang.String reserveA;
	/**
	 * 预留字段B
	 */
	private java.lang.String reserveB;
	//columns END 数据库字段结束
	
	//concstructor

	public Queryname(){
	}

	public Queryname(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
		/**
		 * ID
		 */
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	
	
	/**
	 * ID
	 */
	@Id
     @WhereSQL(sql="id=:Queryname_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 站点id
		 */
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
	
	
	/**
	 * 站点id
	 */
     @WhereSQL(sql="siteId=:Queryname_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
     @WhereSQL(sql="businessId=:Queryname_businessId")
	public java.lang.String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

		/**
		 * 名字
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * 名字
	 */
     @WhereSQL(sql="name=:Queryname_name")
	public java.lang.String getName() {
		return this.name;
	}
		/**
		 * 性别
		 */
	public void setSex(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sex = value;
	}
	
	
	
	/**
	 * 性别
	 */
     @WhereSQL(sql="sex=:Queryname_sex")
	public java.lang.String getSex() {
		return this.sex;
	}
		/**
		 * 所属城市
		 */
	public void setCity(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.city = value;
	}
	
	
	
	/**
	 * 所属城市
	 */
     @WhereSQL(sql="city=:Queryname_city")
	public java.lang.String getCity() {
		return this.city;
	}
		/**
		 * 所属区
		 */
	public void setDistrict(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.district = value;
	}
	
	
	
	/**
	 * 所属区
	 */
     @WhereSQL(sql="district=:Queryname_district")
	public java.lang.String getDistrict() {
		return this.district;
	}
		/**
		 * 出生年
		 */
	public void setBirthday(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.birthday = value;
	}
	
	
	
	/**
	 * 出生年月日
	 */
     @WhereSQL(sql="birthday=:Queryname_birthday")
	public java.lang.String getBirthday() {
		return this.birthday;
	}
		/*
	public String getleadTimeString() {
		return DateUtils.convertDate2String(FORMAT_LEADTIME, getleadTime());
	}
	public void setleadTimeString(String value) throws ParseException{
		setleadTime(DateUtils.convertString2Date(FORMAT_LEADTIME,value));
	}*/
	
		/**
		 * 数据导入时间
		 */
	public void setLeadTime(java.util.Date value) {
		this.leadTime = value;
	}
	
	
	
	/**
	 * 数据导入时间
	 */
     @WhereSQL(sql="leadTime=:Queryname_leadTime")
	public java.util.Date getLeadTime() {
		return this.leadTime;
	}
		/**
		 * 预留字段A
		 */
	public void setReserveA(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.reserveA = value;
	}
	
	
	
	/**
	 * 预留字段A
	 */
     @WhereSQL(sql="reserveA=:Queryname_reserveA")
	public java.lang.String getReserveA() {
		return this.reserveA;
	}
		/**
		 * 预留字段B
		 */
	public void setReserveB(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.reserveB = value;
	}
	
	
	
	/**
	 * 预留字段B
	 */
     @WhereSQL(sql="reserveB=:Queryname_reserveB")
	public java.lang.String getReserveB() {
		return this.reserveB;
	}
	
	public String toString() {
		return new StringBuilder()
			.append("ID[").append(getId()).append("],")
			.append("站点id[").append(getSiteId()).append("],")
			.append("栏目id[").append(getBusinessId()).append("],")
			.append("名字[").append(getName()).append("],")
			.append("性别(0:女 1:男)[").append(getSex()).append("],")
			.append("所属城市[").append(getCity()).append("],")
			.append("所属区[").append(getDistrict()).append("],")
			.append("出生年[").append(getBirthday()).append("],")
			.append("数据导入时间[").append(getLeadTime()).append("],")
			.append("预留字段A[").append(getReserveA()).append("],")
			.append("预留字段B[").append(getReserveB()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Queryname == false){
			return false;
		} 
		if(this == obj){
			return true;
		} 
		Queryname other = (Queryname)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
