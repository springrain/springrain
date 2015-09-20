package org.springrain.system.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.LuceneField;
import org.springrain.frame.annotation.LuceneSearch;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.springrain.system.entity.User
 */
@Table(name="t_user")
@LuceneSearch
public class User  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 姓名
	 */
	private java.lang.String name;
	/**
	 * 账号
	 */
	private java.lang.String account;
	/**
	 * 密码
	 */
	private java.lang.String password;
	
	/**
	 * 性别
	 */
	private java.lang.String sex;

	/**
	 * 手机号码
	 */
	private java.lang.String mobile;
	/**
	 * 邮箱
	 */
	private java.lang.String email;
	
	/**
	 * 是否有效,是/否/离职
	 */
	private java.lang.String state;
	/**
	 * 微信Id
	 */
	private String weixinId;
	
	/**
	 * 用户类型,0:后台管理员,1是教师,2是家长,3是学生
	 */
	private Integer userType;
	
	
	//columns END 数据库字段结束
	


	//用户所有的部门
	private List<Org> userOrgs;
	//用户的所有角色
	private List<Role> userRoles;
	
	
	

	

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
		@LuceneField
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
	     @LuceneField
		public java.lang.String getName() {
			return this.name;
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
		public void setEmail(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.email = value;
		}
		
	     @WhereSQL(sql="eamil=:User_email")
		public java.lang.String getEmail() {
			return this.email;
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
	     @WhereSQL(sql="userType=:User_userType")
		public Integer getUserType() {
			return userType;
		}

		public void setUserType(Integer userType) {
			this.userType = userType;
		}

		public String toString() {
			return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("姓名[").append(getName()).append("],")
			.append("账号[").append(getAccount()).append("],")
			.append("密码[").append(getPassword()).append("],")
			.append("性别[").append(getSex()).append("],")
			.append("手机号码[").append(getMobile()).append("],")
			.append("邮箱[").append(getEmail()).append("],")
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
		public List<Org> getUserOrgs() {
			return userOrgs;
		}

		public void setUserOrgs(List<Org> userOrgs) {
			this.userOrgs = userOrgs;
		}

		@Transient
		public List<Role> getUserRoles() {
			return userRoles;
		}

		public void setUserRoles(List<Role> userRoles) {
			this.userRoles = userRoles;
		}

		public String getWeixinId() {
			return weixinId;
		}

		public void setWeixinId(String weixinId) {
			this.weixinId = weixinId;
		}
	
	
	
}

	
