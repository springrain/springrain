package org.springrain.selenium.entity;

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
 * @version  2017-10-30 15:56:18
 * @see org.springrain.selenium.entity.Htmlfield
 */
@Table(name="tc_htmlfield")
public class Htmlfield  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Htmlfield";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FUNCTIONID = "functionId";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_HTMLID = "htmlId";
	public static final String ALIAS_HTMLNAME = "htmlName";
	public static final String ALIAS_HTMLFIELDTYPE = "1text,2password";
	public static final String ALIAS_HTMLFIELDLENGTH = "字段长度";
	public static final String ALIAS_HTMLMINVALUE = "htmlMinValue";
	public static final String ALIAS_HTMLMAXVALUE = "htmlMaxValue";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * functionId
	 */
	private java.lang.String functionId;
	/**
	 * name
	 */
	private java.lang.String name;
	/**
	 * htmlId
	 */
	private java.lang.String htmlId;
	/**
	 * htmlName
	 */
	private java.lang.String htmlName;
	/**
	 * 1text,2password
	 */
	private java.lang.Integer htmlFieldType;
	/**
	 * 字段长度
	 */
	private java.lang.Integer htmlFieldLength;
	/**
	 * htmlMinValue
	 */
	private java.math.BigDecimal htmlMinValue;
	/**
	 * htmlMaxValue
	 */
	private java.math.BigDecimal htmlMaxValue;
	//columns END 数据库字段结束
	
	//concstructor

	public Htmlfield(){
	}

	public Htmlfield(
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
     @WhereSQL(sql="id=:Htmlfield_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * functionId
		 */
	public void setFunctionId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.functionId = value;
	}
	
	
	
	/**
	 * functionId
	 */
     @WhereSQL(sql="functionId=:Htmlfield_functionId")
	public java.lang.String getFunctionId() {
		return this.functionId;
	}
		/**
		 * name
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * name
	 */
     @WhereSQL(sql="name=:Htmlfield_name")
	public java.lang.String getName() {
		return this.name;
	}
		/**
		 * htmlId
		 */
	public void setHtmlId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.htmlId = value;
	}
	
	
	
	/**
	 * htmlId
	 */
     @WhereSQL(sql="htmlId=:Htmlfield_htmlId")
	public java.lang.String getHtmlId() {
		return this.htmlId;
	}
		/**
		 * htmlName
		 */
	public void setHtmlName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.htmlName = value;
	}
	
	
	
	/**
	 * htmlName
	 */
     @WhereSQL(sql="htmlName=:Htmlfield_htmlName")
	public java.lang.String getHtmlName() {
		return this.htmlName;
	}
		/**
		 * 1text,2password
		 */
	public void setHtmlFieldType(java.lang.Integer value) {
		this.htmlFieldType = value;
	}
	
	
	
	/**
	 * 1text,2password
	 */
     @WhereSQL(sql="htmlFieldType=:Htmlfield_htmlFieldType")
	public java.lang.Integer getHtmlFieldType() {
		return this.htmlFieldType;
	}
		/**
		 * 字段长度
		 */
	public void setHtmlFieldLength(java.lang.Integer value) {
		this.htmlFieldLength = value;
	}
	
	
	
	/**
	 * 字段长度
	 */
     @WhereSQL(sql="htmlFieldLength=:Htmlfield_htmlFieldLength")
	public java.lang.Integer getHtmlFieldLength() {
		return this.htmlFieldLength;
	}
		/**
		 * htmlMinValue
		 */
	public void setHtmlMinValue(java.math.BigDecimal value) {
		this.htmlMinValue = value;
	}
	
	
	
	/**
	 * htmlMinValue
	 */
     @WhereSQL(sql="htmlMinValue=:Htmlfield_htmlMinValue")
	public java.math.BigDecimal getHtmlMinValue() {
		return this.htmlMinValue;
	}
		/**
		 * htmlMaxValue
		 */
	public void setHtmlMaxValue(java.math.BigDecimal value) {
		this.htmlMaxValue = value;
	}
	
	
	
	/**
	 * htmlMaxValue
	 */
     @WhereSQL(sql="htmlMaxValue=:Htmlfield_htmlMaxValue")
	public java.math.BigDecimal getHtmlMaxValue() {
		return this.htmlMaxValue;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("functionId[").append(getFunctionId()).append("],")
			.append("name[").append(getName()).append("],")
			.append("htmlId[").append(getHtmlId()).append("],")
			.append("htmlName[").append(getHtmlName()).append("],")
			.append("1text,2password[").append(getHtmlFieldType()).append("],")
			.append("字段长度[").append(getHtmlFieldLength()).append("],")
			.append("htmlMinValue[").append(getHtmlMinValue()).append("],")
			.append("htmlMaxValue[").append(getHtmlMaxValue()).append("],")
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
		if(obj instanceof Htmlfield == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		Htmlfield other = (Htmlfield)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
