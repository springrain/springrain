package org.iu9.testdb1.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.iu9.frame.annotation.WhereSQL;
import org.iu9.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.iu9.testdb1.entity.User
 */
@Table(name="t_user")
public class User  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "User";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_CODE = "代码";
	public static final String ALIAS_ACCOUNT = "账号";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_SALT = "密码校验码";
	public static final String ALIAS_AGE = "年龄";
	public static final String ALIAS_SEX = "0.女1.男";
	public static final String ALIAS_PHONE = "电话号码";
	public static final String ALIAS_MOBILE = "手机号码";
	public static final String ALIAS_EAMIL = "邮箱";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_STATE = "0.无效1.有效";
    */
	//date formats
	
	//columns START
	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 姓名
	 */
	private java.lang.String name;
	/**
	 * 代码
	 */
	private java.lang.String code;
	/**
	 * 账号
	 */
	private java.lang.String account;
	/**
	 * 密码
	 */
	private java.lang.String password;
	/**
	 * 密码校验码
	 */
	private java.lang.String salt;
	/**
	 * 年龄
	 */
	private java.lang.Integer age;
	/**
	 * 0.女1.男
	 */
	private java.lang.Integer sex;
	/**
	 * 电话号码
	 */
	private java.lang.String phone;
	/**
	 * 手机号码
	 */
	private java.lang.String mobile;
	/**
	 * 邮箱
	 */
	private java.lang.String eamil;
	/**
	 * 地址
	 */
	private java.lang.String address;
	/**
	 * 0.无效1.有效
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	private List<Role> roles;
	
	private Set<Menu> menus;
	
	//concstructor

	public User(){
	}

	public User(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:User_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
     @WhereSQL(sql="name=:User_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
     @WhereSQL(sql="code=:User_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setAccount(java.lang.String value) {
		this.account = value;
	}
	
     @WhereSQL(sql="account=:User_account")
	public java.lang.String getAccount() {
		return this.account;
	}
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
     @WhereSQL(sql="password=:User_password")
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setSalt(java.lang.String value) {
		this.salt = value;
	}
	
     @WhereSQL(sql="salt=:User_salt")
	public java.lang.String getSalt() {
		return this.salt;
	}
	public void setAge(java.lang.Integer value) {
		this.age = value;
	}
	
     @WhereSQL(sql="age=:User_age")
	public java.lang.Integer getAge() {
		return this.age;
	}
	public void setSex(java.lang.Integer value) {
		this.sex = value;
	}
	
     @WhereSQL(sql="sex=:User_sex")
	public java.lang.Integer getSex() {
		return this.sex;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:User_phone")
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
     @WhereSQL(sql="mobile=:User_mobile")
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setEamil(java.lang.String value) {
		this.eamil = value;
	}
	
     @WhereSQL(sql="eamil=:User_eamil")
	public java.lang.String getEamil() {
		return this.eamil;
	}
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
     @WhereSQL(sql="address=:User_address")
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:User_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
     
     
     
	public String toString() {
		return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("姓名[").append(getName()).append("],")
			.append("代码[").append(getCode()).append("],")
			.append("账号[").append(getAccount()).append("],")
			.append("密码[").append(getPassword()).append("],")
			.append("密码校验码[").append(getSalt()).append("],")
			.append("年龄[").append(getAge()).append("],")
			.append("0.女1.男[").append(getSex()).append("],")
			.append("电话号码[").append(getPhone()).append("],")
			.append("手机号码[").append(getMobile()).append("],")
			.append("邮箱[").append(getEamil()).append("],")
			.append("地址[").append(getAddress()).append("],")
			.append("0.无效1.有效[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@Transient
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	@Transient
	public Set<String> getRolesAsString(){
	
		List<Role> list = getRoles();
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		Set<String> set=new HashSet<String>();
		for(Role r:list){
			set.add(r.getCode());
		}
	
		return set;
	}
	
	@Transient
	public  Set<String> getPermissionsAsString(){
		Set<Menu> setMenu = getMenus();
		if(CollectionUtils.isEmpty(setMenu)){
			return null;
		}
		
		Set<String> set=new HashSet<String>();
		for(Menu m:setMenu){
			set.add(m.getPageurl());
		}
		return set;
	}
}

	
