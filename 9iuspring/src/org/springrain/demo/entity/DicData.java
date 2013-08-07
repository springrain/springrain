package org.springrain.demo.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-08-02 16:32:29
 * @see org.springrain.demo.entity.DicData
 */
@Table(name="t_dic_data")
public class DicData  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "公共字典";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_CODE = "编码";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_SORT = "sort";
	public static final String ALIAS_STATE = "是否有效";
	public static final String ALIAS_TYPEKEY = "类型";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 编码
	 */
	private java.lang.String code;
	/**
	 * 描述
	 */
	private java.lang.String description;
	
	/**
	 * 是否有效
	 */
	private java.lang.String state;
	/**
	 * 类型
	 */
	private java.lang.String typekey;
	//columns END 数据库字段结束
	
	//concstructor

	public DicData(){
	}

	public DicData(
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
     @WhereSQL(sql="id=:DicData_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:DicData_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:DicData_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setDescription(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.description = value;
	}
	
     @WhereSQL(sql="description=:DicData_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setState(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.state = value;
	}
	
     @WhereSQL(sql="state=:DicData_state")
	public java.lang.String getState() {
		return this.state;
	}
	public void setTypekey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.typekey = value;
	}
	
     @WhereSQL(sql="typekey=:DicData_typekey")
	public java.lang.String getTypekey() {
		return this.typekey;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("编码[").append(getCode()).append("],")
			.append("描述[").append(getDescription()).append("],")
			.append("是否有效[").append(getState()).append("],")
			.append("类型[").append(getTypekey()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DicData == false) return false;
		if(this == obj) return true;
		DicData other = (DicData)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
