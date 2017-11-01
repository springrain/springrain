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
 * @version  2017-11-01 23:24:08
 * @see org.springrain.selenium.entity.Validaterule
 */
@Table(name="tc_validaterule")
public class Validaterule  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Validaterule";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FIELDID = "业务Id";
	public static final String ALIAS_RESULTTYPE = "1字段为空,2字段格式不对,3字段范围不对,4内容错误,5字段正常";
	public static final String ALIAS_VALIDATEVALUE = "期望结果,例如判断网页的标题";
	public static final String ALIAS_FINDTYPE = "0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert";
	public static final String ALIAS_ELEMENTKEY = "元素的Key,例如 userName 或者xpath的表达式";
	public static final String ALIAS_COMPARE = "eq,lt,";
	public static final String ALIAS_ELEMENTVALUE = "期望结果,例如判断网页的标题";
	public static final String ALIAS_XPATH = "实际的xpath表达式";
	public static final String ALIAS_SORTNO = "sortno";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 业务Id
	 */
	private java.lang.String fieldId;
	/**
	 * 1字段为空,2字段格式不对,3字段范围不对,4内容错误,5字段正常
	 */
	private java.lang.Integer resultType;
	/**
	 * 期望结果,例如判断网页的标题
	 */
	private java.lang.String validateValue;
	/**
	 * 0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert
	 */
	private java.lang.Integer findType;
	/**
	 * 元素的Key,例如 userName 或者xpath的表达式
	 */
	private java.lang.String elementKey;
	/**
	 * eq,lt,
	 */
	private java.lang.String compare;
	/**
	 * 期望结果,例如判断网页的标题
	 */
	private java.lang.String elementValue;
	/**
	 * 实际的xpath表达式
	 */
	private java.lang.String xpath;
	/**
	 * sortno
	 */
	private java.lang.Integer sortno;
	//columns END 数据库字段结束
	
	//concstructor

	public Validaterule(){
	}

	public Validaterule(
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
     @WhereSQL(sql="id=:Validaterule_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 业务Id
		 */
	public void setFieldId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fieldId = value;
	}
	
	
	
	/**
	 * 业务Id
	 */
     @WhereSQL(sql="fieldId=:Validaterule_fieldId")
	public java.lang.String getFieldId() {
		return this.fieldId;
	}
		/**
		 * 1字段为空,2字段格式不对,3字段范围不对,4内容错误,5字段正常
		 */
	public void setResultType(java.lang.Integer value) {
		this.resultType = value;
	}
	
	
	
	/**
	 * 1字段为空,2字段格式不对,3字段范围不对,4内容错误,5字段正常
	 */
     @WhereSQL(sql="resultType=:Validaterule_resultType")
	public java.lang.Integer getResultType() {
		return this.resultType;
	}
		/**
		 * 期望结果,例如判断网页的标题
		 */
	public void setValidateValue(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.validateValue = value;
	}
	
	
	
	/**
	 * 期望结果,例如判断网页的标题
	 */
     @WhereSQL(sql="validateValue=:Validaterule_validateValue")
	public java.lang.String getValidateValue() {
		return this.validateValue;
	}
		/**
		 * 0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert
		 */
	public void setFindType(java.lang.Integer value) {
		this.findType = value;
	}
	
	
	
	/**
	 * 0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert
	 */
     @WhereSQL(sql="findType=:Validaterule_findType")
	public java.lang.Integer getFindType() {
		return this.findType;
	}
		/**
		 * 元素的Key,例如 userName 或者xpath的表达式
		 */
	public void setElementKey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.elementKey = value;
	}
	
	
	
	/**
	 * 元素的Key,例如 userName 或者xpath的表达式
	 */
     @WhereSQL(sql="elementKey=:Validaterule_elementKey")
	public java.lang.String getElementKey() {
		return this.elementKey;
	}
		/**
		 * eq,lt,
		 */
	public void setCompare(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.compare = value;
	}
	
	
	
	/**
	 * eq,lt,
	 */
     @WhereSQL(sql="compare=:Validaterule_compare")
	public java.lang.String getCompare() {
		return this.compare;
	}
		/**
		 * 期望结果,例如判断网页的标题
		 */
	public void setElementValue(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.elementValue = value;
	}
	
	
	
	/**
	 * 期望结果,例如判断网页的标题
	 */
     @WhereSQL(sql="elementValue=:Validaterule_elementValue")
	public java.lang.String getElementValue() {
		return this.elementValue;
	}
		/**
		 * 实际的xpath表达式
		 */
	public void setXpath(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.xpath = value;
	}
	
	
	
	/**
	 * 实际的xpath表达式
	 */
     @WhereSQL(sql="xpath=:Validaterule_xpath")
	public java.lang.String getXpath() {
		return this.xpath;
	}
		/**
		 * sortno
		 */
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
	
	
	/**
	 * sortno
	 */
     @WhereSQL(sql="sortno=:Validaterule_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("业务Id[").append(getFieldId()).append("],")
			.append("1字段为空,2字段格式不对,3字段范围不对,4内容错误,5字段正常[").append(getResultType()).append("],")
			.append("期望结果,例如判断网页的标题[").append(getValidateValue()).append("],")
			.append("0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert[").append(getFindType()).append("],")
			.append("元素的Key,例如 userName 或者xpath的表达式[").append(getElementKey()).append("],")
			.append("eq,lt,[").append(getCompare()).append("],")
			.append("期望结果,例如判断网页的标题[").append(getElementValue()).append("],")
			.append("实际的xpath表达式[").append(getXpath()).append("],")
			.append("sortno[").append(getSortno()).append("],")
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
		if(obj instanceof Validaterule == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		Validaterule other = (Validaterule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
