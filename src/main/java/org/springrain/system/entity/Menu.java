package org.springrain.system.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:02:58
 * @see org.springrain.system.entity.Menu
 */
@Table(name="t_menu")
public class Menu  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Menu";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_PID = "pid";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_PAGEURL = "pageurl";
	public static final String ALIAS_TYPE = "0.普通资源1.菜单资源";
	public static final String ALIAS_SYSTEMID = "systemId";
	public static final String ALIAS_ACTIVE = "active";
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
	private java.lang.Integer menuType;
	/**
	 * 排序
	 */
	
	private Integer sortno;
	/**
	 * 图标样式
	 */
	private String menuIcon;
	

	/**
	 * active
	 */
	private Integer active;
	//columns END 数据库字段结束
	private String pidName;
	
	//路径编码
	private String comcode;
	
	
	
	@Transient
	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	//
	private List<Menu> leaf;
	
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
	/**
	 * bak4
	 */
	private java.lang.String bak4;
	/**
	 * bak5
	 */
	private java.lang.String bak5;
	
	//concstructor

	public Menu(){
	}

	public Menu(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Menu_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Menu_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setPid(java.lang.String value) {
		this.pid = value;
	}
	
     @WhereSQL(sql="pid=:Menu_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
     @WhereSQL(sql="description=:Menu_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setPageurl(java.lang.String value) {
		this.pageurl = value;
	}
	
     @WhereSQL(sql="pageurl=:Menu_pageurl")
	public java.lang.String getPageurl() {
		return this.pageurl;
	}
	public void setMenuType(java.lang.Integer value) {
		this.menuType = value;
	}
	
     @WhereSQL(sql="menuType=:Menu_menuType")
	public java.lang.Integer getMenuType() {
		return this.menuType;
	}
     @WhereSQL(sql="sortno=:Menu_sortno")
	public Integer getSortno() {
		return sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}

	

	public void setActive(Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:Menu_active")
	public Integer getActive() {
		return this.active;
	}
     @WhereSQL(sql="menuIcon=:Menu_menuIcon")
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
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
 @WhereSQL(sql="bak1=:Menu_bak1")
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
 @WhereSQL(sql="bak2=:Menu_bak2")
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
 @WhereSQL(sql="bak3=:Menu_bak3")
public java.lang.String getBak3() {
	return this.bak3;
}
	/**
	 * bak4
	 */
public void setBak4(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
		 value=value.trim();
		}
	this.bak4 = value;
}



/**
 * bak4
 */
 @WhereSQL(sql="bak4=:Menu_bak4")
public java.lang.String getBak4() {
	return this.bak4;
}
	/**
	 * bak5
	 */
public void setBak5(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
		 value=value.trim();
		}
	this.bak5 = value;
}



/**
 * bak5
 */
 @WhereSQL(sql="bak5=:Menu_bak5")
public java.lang.String getBak5() {
	return this.bak5;
}
@Override
public String toString() {
	return new StringBuilder()
		.append("主键id[").append(getId()).append("],")
		.append("菜单名称[").append(getName()).append("],")
		.append("父类id[").append(getPid()).append("],")
		.append("描述[").append(getDescription()).append("],")
		.append("跳转URL[").append(getPageurl()).append("],")
		.append("0.功能按钮,1.导航菜单[").append(getMenuType()).append("],")
		.append("是否有效(0否,1是)[").append(getActive()).append("],")
		.append("排序[").append(getSortno()).append("],")
		.append("菜单图标[").append(getMenuIcon()).append("],")
		.append("bak1[").append(getBak1()).append("],")
		.append("bak2[").append(getBak2()).append("],")
		.append("bak3[").append(getBak3()).append("],")
		.append("bak4[").append(getBak4()).append("],")
		.append("bak5[").append(getBak5()).append("],")
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
	if(obj instanceof Menu == false){
		return false;
	}
		
	if(this == obj){
		return true;
	}
	
	Menu other = (Menu)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}

	@Transient
	public List<Menu> getLeaf() {
		return leaf;
	}

	public void setLeaf(List<Menu> leaf) {
		this.leaf = leaf;
	}
	@Transient
	public String getComcode() {
		return comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
}

	
