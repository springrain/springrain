package org.iu9.frame.shiro;

import java.io.Serializable;

import org.iu9.testdb1.entity.User;

public class ShiroUser implements Serializable {
	/**
	 * 
	 */
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
	 * 代码
	 */
	private java.lang.String code;
	/**
	 * 账号
	 */
	private java.lang.String account;

	/**
	 * 邮箱
	 */
	private java.lang.String eamil;
	/**
	 * 0.女1.男
	 */
	private java.lang.Integer sex;

	public ShiroUser() {

	}

	public ShiroUser(User user) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.name = user.getName();
		this.eamil = user.getEamil();
		this.code = user.getCode();
		this.sex=user.getSex();
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return this.account;
	}

	/**
	 * 重载equals,只计算account;
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShiroUser other = (ShiroUser) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		return true;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getAccount() {
		return account;
	}

	public void setAccount(java.lang.String account) {
		this.account = account;
	}

	public java.lang.String getEamil() {
		return eamil;
	}

	public void setEamil(java.lang.String eamil) {
		this.eamil = eamil;
	}

	public java.lang.Integer getSex() {
		return sex;
	}

	public void setSex(java.lang.Integer sex) {
		this.sex = sex;
	}
}