package org.springrain.demo.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.springrain.demo.entity.User
 */
@Table(name="t_user")
public class User  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_WORKNO = "工号";
	public static final String ALIAS_ACCOUNT = "账号";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_CARDNO = "身份证";
	public static final String ALIAS_AGE = "年龄";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_PHONE = "电话号码";
	public static final String ALIAS_MOBILE = "手机号码";
	public static final String ALIAS_EAMIL = "邮箱";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_GRADEID = "级别";
	public static final String ALIAS_EDUNAME = "学历";
	public static final String ALIAS_ENTRYDATE = "入职日期";
	public static final String ALIAS_STARTDATE = "转正日期";
	public static final String ALIAS_ENDDATE = "离职日期";
	public static final String ALIAS_FIRENAME = "紧急联系人";
	public static final String ALIAS_FIREPHONE = "紧急联系电话";
	public static final String ALIAS_DESCRIPTION = "备注";
	public static final String ALIAS_STATE = "是否有效,是/否/离职";
    */
	//date formats
	//public static final String FORMAT_ENTRYDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_STARTDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDDATE = DateUtils.DATETIME_FORMAT;
	
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
	 * 工号
	 */
	private java.lang.String workno;
	/**
	 * 账号
	 */
	private java.lang.String account;
	/**
	 * 密码
	 */
	private java.lang.String password;
	/**
	 * 身份证
	 */
	private java.lang.String cardno;
	/**
	 * 年龄
	 */
	private java.lang.Integer age;
	/**
	 * 性别
	 */
	private java.lang.String sex;
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
	 * 级别
	 */
	private java.lang.String gradeId;
	/**
	 * 学历
	 */
	private java.lang.String eduName;
	/**
	 * 入职日期
	 */
	private java.util.Date entryDate;
	/**
	 * 转正日期
	 */
	private java.util.Date startDate;
	/**
	 * 离职日期
	 */
	private java.util.Date endDate;
	/**
	 * 紧急联系人
	 */
	private java.lang.String fireName;
	/**
	 * 紧急联系电话
	 */
	private java.lang.String firePhone;
	/**
	 * 备注
	 */
	private java.lang.String description;
	/**
	 * 是否有效,是/否/离职
	 */
	private java.lang.String state;
	//columns END 数据库字段结束
	
	private List<Role> roles;
	
	private Set<Menu> menus;
	private String roleIds;
	private String roleIdNames;
	private String gradeName;
	@Transient
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Transient
	public String getRoleIdNames() {
		return roleIdNames;
	}

	public void setRoleIdNames(String roleIdNames) {
		this.roleIdNames = roleIdNames;
	}

	@Transient
	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

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
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.id = value;
		}
		
		@Id
	     @WhereSQL(sql="id=:User_id")
		public java.lang.String getId() {
			return this.id;
		}
		public void setName(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.name = value;
		}
		
	     @WhereSQL(sql="name=:User_name")
		public java.lang.String getName() {
			return this.name;
		}
		public void setWorkno(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.workno = value;
		}
		
	     @WhereSQL(sql="workno=:User_workno")
		public java.lang.String getWorkno() {
			return this.workno;
		}
		public void setAccount(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.account = value;
		}
		
	     @WhereSQL(sql="account=:User_account")
		public java.lang.String getAccount() {
			return this.account;
		}
		public void setPassword(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.password = value;
		}
		
	     @WhereSQL(sql="password=:User_password")
		public java.lang.String getPassword() {
			return this.password;
		}
		public void setCardno(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.cardno = value;
		}
		
	     @WhereSQL(sql="cardno=:User_cardno")
		public java.lang.String getCardno() {
			return this.cardno;
		}
		public void setAge(java.lang.Integer value) {
			this.age = value;
		}
		
	     @WhereSQL(sql="age=:User_age")
		public java.lang.Integer getAge() {
			return this.age;
		}
		public void setSex(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.sex = value;
		}
		
	     @WhereSQL(sql="sex=:User_sex")
		public java.lang.String getSex() {
			return this.sex;
		}
		public void setPhone(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.phone = value;
		}
		
	     @WhereSQL(sql="phone=:User_phone")
		public java.lang.String getPhone() {
			return this.phone;
		}
		public void setMobile(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.mobile = value;
		}
		
	     @WhereSQL(sql="mobile=:User_mobile")
		public java.lang.String getMobile() {
			return this.mobile;
		}
		public void setEamil(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.eamil = value;
		}
		
	     @WhereSQL(sql="eamil=:User_eamil")
		public java.lang.String getEamil() {
			return this.eamil;
		}
		public void setAddress(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.address = value;
		}
		
	     @WhereSQL(sql="address=:User_address")
		public java.lang.String getAddress() {
			return this.address;
		}
		public void setGradeId(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.gradeId = value;
		}
		
	     @WhereSQL(sql="gradeId=:User_gradeId")
		public java.lang.String getGradeId() {
			return this.gradeId;
		}
		public java.lang.String getEduName() {
			return eduName;
		}

		public void setEduName(java.lang.String eduName) {
			this.eduName = eduName;
		}

		public java.util.Date getEntryDate() {
			return entryDate;
		}

		public void setEntryDate(java.util.Date entryDate) {
			this.entryDate = entryDate;
		}

		public java.util.Date getStartDate() {
			return startDate;
		}

		public void setStartDate(java.util.Date startDate) {
			this.startDate = startDate;
		}

		public java.util.Date getEndDate() {
			return endDate;
		}

		public void setEndDate(java.util.Date endDate) {
			this.endDate = endDate;
		}

		public void setFireName(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.fireName = value;
		}
		
	     @WhereSQL(sql="fireName=:User_fireName")
		public java.lang.String getFireName() {
			return this.fireName;
		}
		public void setFirePhone(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.firePhone = value;
		}
		
	     @WhereSQL(sql="firePhone=:User_firePhone")
		public java.lang.String getFirePhone() {
			return this.firePhone;
		}
		public void setDescription(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.description = value;
		}
		
	     @WhereSQL(sql="description=:User_description")
		public java.lang.String getDescription() {
			return this.description;
		}
		public void setState(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.state = value;
		}
		
	     @WhereSQL(sql="state=:User_state")
		public java.lang.String getState() {
			return this.state;
		}
		
		public String toString() {
			return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("姓名[").append(getName()).append("],")
			.append("工号[").append(getWorkno()).append("],")
			.append("账号[").append(getAccount()).append("],")
			.append("密码[").append(getPassword()).append("],")
			.append("身份证[").append(getCardno()).append("],")
			.append("年龄[").append(getAge()).append("],")
			.append("性别[").append(getSex()).append("],")
			.append("电话号码[").append(getPhone()).append("],")
			.append("手机号码[").append(getMobile()).append("],")
			.append("邮箱[").append(getEamil()).append("],")
			.append("地址[").append(getAddress()).append("],")
			.append("级别[").append(getGradeId()).append("],")
			.append("学历[").append(getEduName()).append("],")
			.append("入职日期[").append(getEntryDate()).append("],")
			.append("转正日期[").append(getStartDate()).append("],")
			.append("离职日期[").append(getEndDate()).append("],")
			.append("紧急联系人[").append(getFireName()).append("],")
			.append("紧急联系电话[").append(getFirePhone()).append("],")
			.append("备注[").append(getDescription()).append("],")
			.append("是否有效,是/否/离职[").append(getState()).append("],")
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
			if(StringUtils.isBlank(m.getPageurl())){
				continue;
			}
			set.add(m.getPageurl());
		}
		return set;
	}
}

	
