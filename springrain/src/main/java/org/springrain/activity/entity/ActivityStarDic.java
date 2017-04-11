package org.springrain.activity.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * 星级策略
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-14 09:34:46
 * @see org.springrain.activity.entity.base.entity.ActivityStarDic
 */
@Table(name="t_activity_star_dic")
public class ActivityStarDic  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "星级策略表";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_STAR = "星级";
	public static final String ALIAS_MINVALUE = "最小值";
	public static final String ALIAS_MAXVALUE = "最大值";
	public static final String ALIAS_SITEID = "站点ID";
	public static final String ALIAS_BUINESSID = "业务ID";
    */
	//date formats
	
	//columns START
	/**
	 * 主键ID
	 */
	private java.lang.String id;
	/**
	 * 星级
	 */
	private java.lang.String star;
	/**
	 * 最小值
	 */
	private java.lang.Integer minVal;
	/**
	 * 最大值
	 */
	private java.lang.Integer maxVal;
	/**
	 * 站点ID
	 */
	private java.lang.String siteId;
	/**
	 * 业务ID
	 */
	private java.lang.String buinessId;
	//columns END 数据库字段结束
	
	//concstructor

	public ActivityStarDic(){
	}

	public ActivityStarDic(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 主键ID
		 */
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	
	
	/**
	 * 主键ID
	 */
	@Id
     @WhereSQL(sql="id=:ActivityStarDic_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 星级
		 */
	public void setStar(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.star = value;
	}
	
	
	
	/**
	 * 星级
	 */
     @WhereSQL(sql="star=:ActivityStarDic_star")
	public java.lang.String getStar() {
		return this.star;
	}
		/**
		 * 最小值
		 */
	public void setMinVal(java.lang.Integer value) {
		this.minVal = value;
	}
	
	
	
	/**
	 * 最小值
	 */
     @WhereSQL(sql="minVal=:ActivityStarDic_minVal")
	public java.lang.Integer getMinVal() {
		return this.minVal;
	}
		/**
		 * 最大值
		 */
	public void setMaxVal(java.lang.Integer val) {
		this.maxVal = val;
	}
	
	
	
	/**
	 * 最大值
	 */
     @WhereSQL(sql="maxVal=:ActivityStarDic_maxVal")
	public java.lang.Integer getMaxVal() {
		return this.maxVal;
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
     @WhereSQL(sql="siteId=:ActivityStarDic_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
		/**
		 * 业务ID
		 */
	public void setBuinessId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.buinessId = value;
	}
	
	
	
	/**
	 * 业务ID
	 */
     @WhereSQL(sql="buinessId=:ActivityStarDic_buinessId")
	public java.lang.String getBuinessId() {
		return this.buinessId;
	}
	
     @Override
	public String toString() {
		return new StringBuilder()
			.append("主键ID[").append(getId()).append("],")
			.append("星级[").append(getStar()).append("],")
			.append("最小值[").append(getMinVal()).append("],")
			.append("最大值[").append(getMaxVal()).append("],")
			.append("站点ID[").append(getSiteId()).append("],")
			.append("业务ID[").append(getBuinessId()).append("],")
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
		if(obj instanceof ActivityStarDic ==  false){
			return false;
		} 
		if(this == obj){
			return true;
		} 
		ActivityStarDic other = (ActivityStarDic)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
