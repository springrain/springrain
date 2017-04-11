package org.springrain.police.entity;

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
 * @version  2017-04-06 10:57:53
 * @see org.springrain.cms.base.entity.Illegalcode
 */
@Table(name="t_illegalcode")
public class Illegalcode  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Illegalcode";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_WFXW = "非法行为";
	public static final String ALIAS_WFNR = "违法内容";
	public static final String ALIAS_WFGD = "违法规定";
	public static final String ALIAS_WFYJ = "违法依据";
	public static final String ALIAS_WFJF = "非法积分数";
	public static final String ALIAS_FKJE = "罚款金额";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	private java.lang.String siteId;
	/**
	 * 非法行为
	 */
	private java.lang.String wfxw;
	/**
	 * 违法内容
	 */
	private java.lang.String wfnr;
	/**
	 * 违法规定
	 */
	private java.lang.String wfgd;
	/**
	 * 违法依据
	 */
	private java.lang.String wfyj;
	/**
	 * 非法积分数
	 */
	private java.lang.String wfjfs;
	/**
	 * 罚款金额最大数
	 */
	private java.lang.String fkje;
	//columns END 数据库字段结束
	
	//concstructor

	public Illegalcode(){
	}

	public Illegalcode(
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
     @WhereSQL(sql="id=:Illegalcode_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 非法行为
		 */
	public void setWfxw(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wfxw = value;
	}
	
	
	
	/**
	 * 非法行为
	 */
     @WhereSQL(sql="wfxw=:Illegalcode_wfxw")
	public java.lang.String getWfxw() {
		return this.wfxw;
	}
		/**
		 * 违法内容
		 */
	public void setWfnr(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wfnr = value;
	}
	
	
	
	/**
	 * 违法内容
	 */
     @WhereSQL(sql="wfnr=:Illegalcode_wfnr")
	public java.lang.String getWfnr() {
		return this.wfnr;
	}
		/**
		 * 违法规定
		 */
	public void setWfgd(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wfgd = value;
	}
	
	
	
	/**
	 * 违法规定
	 */
     @WhereSQL(sql="wfgd=:Illegalcode_wfgd")
	public java.lang.String getWfgd() {
		return this.wfgd;
	}
		/**
		 * 违法依据
		 */
	public void setWfyj(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wfyj = value;
	}
	
	
	
	/**
	 * 违法依据
	 */
     @WhereSQL(sql="wfyj=:Illegalcode_wfyj")
	public java.lang.String getWfyj() {
		return this.wfyj;
	}
		/**
		 * 非法积分数
		 */
	public void setWfjfs(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wfjfs = value;
	}
	
	
	
	/**
	 * 非法积分数
	 */
     @WhereSQL(sql="wfjfs=:Illegalcode_wfjfs")
	public java.lang.String getWfjfs() {
		return this.wfjfs;
	}
     
     @WhereSQL(sql="fkje=:Illegalcode_fkje")
	public java.lang.String getFkje() {
		return fkje;
	}

	public void setFkje(java.lang.String fkje) {
		this.fkje = fkje;
	}
	@WhereSQL(sql="siteId=:Illegalcode_siteId")
	public java.lang.String getSiteId() {
		return siteId;
	}
	
	public void setSiteId(java.lang.String siteId) {
		this.siteId = siteId;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("非法行为[").append(getWfxw()).append("],")
			.append("违法内容[").append(getWfnr()).append("],")
			.append("违法规定[").append(getWfgd()).append("],")
			.append("违法依据[").append(getWfyj()).append("],")
			.append("非法积分数[").append(getWfjfs()).append("],")
			.append("罚款金额大[").append(getFkje()).append("],")
			.append("罚款金额小[").append(getFkje()).append("],")
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
		if(obj instanceof Illegalcode == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		Illegalcode other = (Illegalcode)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
