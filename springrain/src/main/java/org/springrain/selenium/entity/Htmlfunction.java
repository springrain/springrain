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
 * @version  2017-10-30 15:56:28
 * @see org.springrain.selenium.entity.Htmlfunction
 */
@Table(name="tc_htmlfunction")
public class Htmlfunction  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Htmlfunction";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "功能名称";
	public static final String ALIAS_URL = "功能URL";
	public static final String ALIAS_PID = "pid";
	public static final String ALIAS_BTNID = "btnId";
	public static final String ALIAS_BTNTEXT = "btnText";
	public static final String ALIAS_SORTNO = "sortno";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 功能名称
	 */
	private java.lang.String name;
	/**
	 * 功能URL
	 */
	private java.lang.String url;
	/**
	 * pid
	 */
	private java.lang.String pid;
	/**
	 * btnId
	 */
	private java.lang.String btnId;
	/**
	 * btnText
	 */
	private java.lang.String btnText;
	/**
	 * sortno
	 */
	private java.lang.Integer sortno;
	//columns END 数据库字段结束
	
	//concstructor

	public Htmlfunction(){
	}

	public Htmlfunction(
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
     @WhereSQL(sql="id=:Htmlfunction_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 功能名称
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * 功能名称
	 */
     @WhereSQL(sql="name=:Htmlfunction_name")
	public java.lang.String getName() {
		return this.name;
	}
		/**
		 * 功能URL
		 */
	public void setUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.url = value;
	}
	
	
	
	/**
	 * 功能URL
	 */
     @WhereSQL(sql="url=:Htmlfunction_url")
	public java.lang.String getUrl() {
		return this.url;
	}
		/**
		 * pid
		 */
	public void setPid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pid = value;
	}
	
	
	
	/**
	 * pid
	 */
     @WhereSQL(sql="pid=:Htmlfunction_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
		/**
		 * btnId
		 */
	public void setBtnId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.btnId = value;
	}
	
	
	
	/**
	 * btnId
	 */
     @WhereSQL(sql="btnId=:Htmlfunction_btnId")
	public java.lang.String getBtnId() {
		return this.btnId;
	}
		/**
		 * btnText
		 */
	public void setBtnText(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.btnText = value;
	}
	
	
	
	/**
	 * btnText
	 */
     @WhereSQL(sql="btnText=:Htmlfunction_btnText")
	public java.lang.String getBtnText() {
		return this.btnText;
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
     @WhereSQL(sql="sortno=:Htmlfunction_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("功能名称[").append(getName()).append("],")
			.append("功能URL[").append(getUrl()).append("],")
			.append("pid[").append(getPid()).append("],")
			.append("btnId[").append(getBtnId()).append("],")
			.append("btnText[").append(getBtnText()).append("],")
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
		if(obj instanceof Htmlfunction == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		Htmlfunction other = (Htmlfunction)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
