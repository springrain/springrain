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
 * @version  2017-10-30 15:56:37
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
	public static final String ALIAS_RESULTTYPE = "1功能成功,2功能失败,3字段为空,4字段格式不对,5字段范围不对,6内容错误,7字段正常";
	public static final String ALIAS_FINDTYPE = "0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert";
	public static final String ALIAS_ELEMENTKEY = "元素的Key,例如 userName 或者xpath的表达式";
	public static final String ALIAS_COMPARE = "eq,lt,";
	public static final String ALIAS_RESULTVALUE = "期望结果,例如判断网页的标题";
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
	 * 1功能成功,2功能失败,3字段为空,4字段格式不对,5字段范围不对,6内容错误,7字段正常
	 */
	private java.lang.Integer resultType;
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
	private java.lang.String resultValue;
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
		 * 1功能成功,2功能失败,3字段为空,4字段格式不对,5字段范围不对,6内容错误,7字段正常
		 */
	public void setResultType(java.lang.Integer value) {
		this.resultType = value;
	}
	
	
	
	/**
	 * 1功能成功,2功能失败,3字段为空,4字段格式不对,5字段范围不对,6内容错误,7字段正常
	 */
     @WhereSQL(sql="resultType=:Validaterule_resultType")
	public java.lang.Integer getResultType() {
		return this.resultType;
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
	public void setResultValue(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.resultValue = value;
	}
	
	
	
	/**
	 * 期望结果,例如判断网页的标题
	 */
     @WhereSQL(sql="resultValue=:Validaterule_resultValue")
	public java.lang.String getResultValue() {
		return this.resultValue;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("业务Id[").append(getFieldId()).append("],")
			.append("1功能成功,2功能失败,3字段为空,4字段格式不对,5字段范围不对,6内容错误,7字段正常[").append(getResultType()).append("],")
			.append("0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert[").append(getFindType()).append("],")
			.append("元素的Key,例如 userName 或者xpath的表达式[").append(getElementKey()).append("],")
			.append("eq,lt,[").append(getCompare()).append("],")
			.append("期望结果,例如判断网页的标题[").append(getResultValue()).append("],")
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

	
