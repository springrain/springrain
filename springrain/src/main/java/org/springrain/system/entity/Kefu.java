package org.springrain.system.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2014-10-23 16:06:12
 * @see com.bibizao.bms.goods.entity.Kefu
 */
@Table(name="t_kefu")
public class Kefu  extends BaseEntity {
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Kefu";
	public static final String ALIAS_ID = "消息Id";
	public static final String ALIAS_TOID = "回复Id";
	public static final String ALIAS_MEMBERID = "用户Id";
	public static final String ALIAS_KEFUMEMBERID = "账户";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_KEFUTYPE = "类型,微信或者web";
    */
	//date formats
	
	//columns START
	/**
	 * 消息Id
	 */
	private java.lang.Integer id;
	/**
	 * 回复Id
	 */
	private java.lang.Integer toId;
	/**
	 * 用户Id
	 */
	private java.lang.String memberId;
	/**
	 * 账户
	 */
	private java.lang.String kefuMemberId;
	/**
	 * 内容
	 */
	private java.lang.String content;
	
	
	/**
	 * 答案
	 */
	private java.lang.String answer;
	
	
	/**
	 * 类型,微信或者web
	 */
	private java.lang.String kefuType;
	//columns END 数据库字段结束
	
	//concstructor

	public Kefu(){
	}

	public Kefu(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Kefu_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setToId(java.lang.Integer value) {
		this.toId = value;
	}
	
     @WhereSQL(sql="toId=:Kefu_toId")
	public java.lang.Integer getToId() {
		return this.toId;
	}
	public void setMemberId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberId = value;
	}
	
     @WhereSQL(sql="memberId=:Kefu_memberId")
	public java.lang.String getMemberId() {
		return this.memberId;
	}
	public void setKefuMemberId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.kefuMemberId = value;
	}
	
     @WhereSQL(sql="kefuMemberId=:Kefu_kefuMemberId")
	public java.lang.String getKefuMemberId() {
		return this.kefuMemberId;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Kefu_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setKefuType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.kefuType = value;
	}
	
     @WhereSQL(sql="kefuType=:Kefu_kefuType")
	public java.lang.String getKefuType() {
		return this.kefuType;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("消息Id[").append(getId()).append("],")
			.append("回复Id[").append(getToId()).append("],")
			.append("用户Id[").append(getMemberId()).append("],")
			.append("账户[").append(getKefuMemberId()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("类型,微信或者web[").append(getKefuType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Kefu == false) return false;
		if(this == obj) return true;
		Kefu other = (Kefu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public java.lang.String getAnswer() {
		return answer;
	}

	public void setAnswer(java.lang.String answer) {
		this.answer = answer;
	}}

	
