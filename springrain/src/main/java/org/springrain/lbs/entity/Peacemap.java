package org.springrain.lbs.entity;

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
 * @version  2017-03-14 10:44:41
 * @see org.springrain.lbs.entity.base.entity.Peacemap
 */
@Table(name="t_peacemap")
public class Peacemap  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Peacemap";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_NAME = "地点名称";
	public static final String ALIAS_CITY = "地点所在市";
	public static final String ALIAS_DISTRICT = "所在区";
	public static final String ALIAS_TEL = "电话号码";
	public static final String ALIAS_POIADDRESS = "地点详细地址";
	public static final String ALIAS_LAT = "纬度";
	public static final String ALIAS_LNG = "经度";
	public static final String ALIAS_CATEGORY = "分类（1:派出所, 2:报警点, 3:快处快赔点）";
	public static final String ALIAS_SITEID = "站点Id";
	public static final String ALIAS_CHANNELID = "分类Id";
	public static final String ALIAS_OBLIGATEA = "预留字段A";
	public static final String ALIAS_OBLIGATEB = "预留字段B";
    */
	//date formats
	
	//columns START
	/**
	 * ID
	 */
	private java.lang.String id;
	/**
	 * 地点名称
	 */
	private java.lang.String name;
	/**
	 * 地点所在市
	 */
	private java.lang.String city;
	/**
	 * 所在区
	 */
	private java.lang.String district;
	/**
	 * 电话号码
	 */
	private java.lang.String tel;
	/**
	 * 地点详细地址
	 */
	private java.lang.String poiaddress;
	/**
	 * 纬度
	 */
	private java.lang.String lat;
	/**
	 * 经度
	 */
	private java.lang.String lng;
	/**
	 * 分类（1:派出所, 2:报警点, 3:快处快赔点）
	 */
	private java.lang.String category;
	/**
	 * 站点Id
	 */
	private java.lang.String siteId;
	/**
	 * 分类Id
	 */
	private java.lang.String channelId;
	/**
	 * 排序字段
	 */
	private java.lang.String sort;
	/**
	 * 预留字段A
	 */
	private java.lang.String obligateA;
	/**
	 * 预留字段B
	 */
	private java.lang.String obligateB;
	//columns END 数据库字段结束
	
	//concstructor

	public Peacemap(){
	}

	public Peacemap(
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
     @WhereSQL(sql="id=:Peacemap_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 地点名称
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * 地点名称
	 */
     @WhereSQL(sql="name=:Peacemap_name")
	public java.lang.String getName() {
		return this.name;
	}
		/**
		 * 地点所在市
		 */
	public void setCity(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.city = value;
	}
	
	
	
	/**
	 * 地点所在市
	 */
     @WhereSQL(sql="city=:Peacemap_city")
	public java.lang.String getCity() {
		return this.city;
	}
		/**
		 * 所在区
		 */
	public void setDistrict(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.district = value;
	}
	
	
	
	/**
	 * 所在区
	 */
     @WhereSQL(sql="district=:Peacemap_district")
	public java.lang.String getDistrict() {
		return this.district;
	}
		/**
		 * 电话号码
		 */
	public void setTel(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tel = value;
	}
	
	
	
	/**
	 * 电话号码
	 */
     @WhereSQL(sql="tel=:Peacemap_tel")
	public java.lang.String getTel() {
		return this.tel;
	}
		/**
		 * 地点详细地址
		 */
	public void setPoiaddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.poiaddress = value;
	}
	
	
	
	/**
	 * 地点详细地址
	 */
     @WhereSQL(sql="poiaddress=:Peacemap_poiaddress")
	public java.lang.String getPoiaddress() {
		return this.poiaddress;
	}
		/**
		 * 纬度
		 */
	public void setLat(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lat = value;
	}
	
	
	
	/**
	 * 纬度
	 */
     @WhereSQL(sql="lat=:Peacemap_lat")
	public java.lang.String getLat() {
		return this.lat;
	}
		/**
		 * 经度
		 */
	public void setLng(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lng = value;
	}
	
	
	
	/**
	 * 经度
	 */
     @WhereSQL(sql="lng=:Peacemap_lng")
	public java.lang.String getLng() {
		return this.lng;
	}
		/**
		 * 分类（1:派出所, 2:报警点, 3:快处快赔点）
		 */
	public void setCategory(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.category = value;
	}
	
	
	
	/**
	 * 分类（1:派出所, 2:报警点, 3:快处快赔点）
	 */
     @WhereSQL(sql="category=:Peacemap_category")
	public java.lang.String getSort() {
		return this.sort;
	}
 	/**
		 * 分类（1:派出所, 2:报警点, 3:快处快赔点）
		 */
	public void setSort(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sort = value;
	}
	
	
	
	/**
	 * 分类（1:派出所, 2:报警点, 3:快处快赔点）
	 */
  @WhereSQL(sql="category=:Peacemap_category")
	public java.lang.String getCategory() {
		return this.category;
	}
		/**
		 * 站点Id
		 */
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
	
	
	/**
	 * 站点Id
	 */
     @WhereSQL(sql="siteId=:Peacemap_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
		/**
		 * 分类Id
		 */
	public void setChannelId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.channelId = value;
	}
	
	
	
	/**
	 * 分类Id
	 */
     @WhereSQL(sql="channelId=:Peacemap_channelId")
	public java.lang.String getChannelId() {
		return this.channelId;
	}
		/**
		 * 预留字段A
		 */
	public void setObligateA(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.obligateA = value;
	}
	
	
	
	/**
	 * 预留字段A
	 */
     @WhereSQL(sql="obligateA=:Peacemap_obligateA")
	public java.lang.String getObligateA() {
		return this.obligateA;
	}
		/**
		 * 预留字段B
		 */
	public void setObligateB(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.obligateB = value;
	}
	
	
	
	/**
	 * 预留字段B
	 */
     @WhereSQL(sql="obligateB=:Peacemap_obligateB")
	public java.lang.String getObligateB() {
		return this.obligateB;
	}
	
	public String toString() {
		return new StringBuilder()
			.append("{\"id\":\"").append(getId()).append("\",")
			.append("\"name\":\"").append(getName()).append("\",")
			.append("\"city\":\"").append(getCity()).append("\",")
			.append("\"tel\":\"").append(getTel()).append("\",")
			.append("\"poiaddress\":\"").append(getPoiaddress()).append("\",")
			.append("\"lat\":\"").append(getLat()).append("\",")
			.append("\"lng\":\"").append(getLng()).append("\",")
			.append("\"siteId\":\"").append(getSiteId()).append("\",")
			.append("\"channelId\":\"").append(getChannelId()).append("\"}")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Peacemap == false){
			return false;
		} 
		if(this == obj){
			return true;
		} 
		Peacemap other = (Peacemap)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
