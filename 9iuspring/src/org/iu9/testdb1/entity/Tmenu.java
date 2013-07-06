package org.iu9.testdb1.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.persistence.Id;
import javax.persistence.Table;
import org.iu9.frame.annotation.WhereSQL;

import org.iu9.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 15:28:16
 * @see org.iu9.testdb1.entity.Tmenu
 */
@Table(name="t_menu")
public class Tmenu  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Tmenu";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_PID = "pid";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_PAGEURL = "pageurl";
	public static final String ALIAS_TYPE = "0.普通资源1.菜单资源";
	public static final String ALIAS_SYSTEMID = "systemId";
	public static final String ALIAS_STATE = "state";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * name
	 */
	private java.lang.String name;
	/**
	 * pid
	 */
	private java.lang.String pid;
	/**
	 * description
	 */
	private java.lang.String description;
	/**
	 * pageurl
	 */
	private java.lang.String pageurl;
	/**
	 * 0.普通资源1.菜单资源
	 */
	private java.lang.Integer type;
	/**
	 * systemId
	 */
	private java.lang.String systemId;
	/**
	 * state
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public Tmenu(){
	}

	public Tmenu(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Tmenu_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Tmenu_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setPid(java.lang.String value) {
		this.pid = value;
	}
	
     @WhereSQL(sql="pid=:Tmenu_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
     @WhereSQL(sql="description=:Tmenu_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setPageurl(java.lang.String value) {
		this.pageurl = value;
	}
	
     @WhereSQL(sql="pageurl=:Tmenu_pageurl")
	public java.lang.String getPageurl() {
		return this.pageurl;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Tmenu_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setSystemId(java.lang.String value) {
		this.systemId = value;
	}
	
     @WhereSQL(sql="systemId=:Tmenu_systemId")
	public java.lang.String getSystemId() {
		return this.systemId;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:Tmenu_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("name[").append(getName()).append("],")
			.append("pid[").append(getPid()).append("],")
			.append("description[").append(getDescription()).append("],")
			.append("pageurl[").append(getPageurl()).append("],")
			.append("0.普通资源1.菜单资源[").append(getType()).append("],")
			.append("systemId[").append(getSystemId()).append("],")
			.append("state[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Tmenu == false) return false;
		if(this == obj) return true;
		Tmenu other = (Tmenu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
