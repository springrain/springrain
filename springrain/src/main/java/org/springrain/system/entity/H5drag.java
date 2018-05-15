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
import org.springrain.frame.util.JsonUtils;
import org.springrain.system.dto.H5dragDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-05-14 15:58:54
 * @see org.springrain.system.entity.H5drag
 */
@Table(name="t_h5drag")
public class H5drag  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "H5drag";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_AJAXURL = "ajax请求的URL";
	public static final String ALIAS_PUBTITLE = "大标题";
	public static final String ALIAS_MODULE = "组件";
	public static final String ALIAS_BGCOLOR = "背景色";
	public static final String ALIAS_CLASSARR = "类名";
	public static final String ALIAS_LISTARR = "数据 array";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * ajax请求的URL
	 */
	private java.lang.String ajaxURL;
	/**
	 * 大标题
	 */
	private java.lang.String pubTitle;
	/**
	 * 组件
	 */
	private java.lang.String module;
	/**
	 * 背景色
	 */
	private java.lang.String bgColor;
	/**
	 * 类名
	 */
	@JsonIgnore
	private java.lang.String classArrStr;
	/**
	 * 数据 array
	 */
	@JsonIgnore
	private java.lang.String listArrStr;

	
	
	
	//columns END 数据库字段结束
	   
    private List<H5dragDto> listArr;
    
    /**
     * 类名
     */
    private List<String> classArr;
    
    
	//concstructor

	public H5drag(){
	}

	public H5drag(
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
     @WhereSQL(sql="id=:H5drag_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * ajax请求的URL
		 */
	public void setAjaxURL(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ajaxURL = value;
	}
	
	
	
	/**
	 * ajax请求的URL
	 */
     @WhereSQL(sql="ajaxURL=:H5drag_ajaxURL")
	public java.lang.String getAjaxURL() {
		return this.ajaxURL;
	}
		/**
		 * 大标题
		 */
	public void setPubTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pubTitle = value;
	}
	
	
	
	/**
	 * 大标题
	 */
     @WhereSQL(sql="pubTitle=:H5drag_pubTitle")
	public java.lang.String getPubTitle() {
		return this.pubTitle;
	}
		/**
		 * 组件
		 */
	public void setModule(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.module = value;
	}
	
	
	
	/**
	 * 组件
	 */
     @WhereSQL(sql="module=:H5drag_module")
	public java.lang.String getModule() {
		return this.module;
	}
		/**
		 * 背景色
		 */
	public void setBgColor(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bgColor = value;
	}
	
	
	
	/**
	 * 背景色
	 */
     @WhereSQL(sql="bgColor=:H5drag_bgColor")
	public java.lang.String getBgColor() {
		return this.bgColor;
	}
		/**
		 * 类名
		 */
	public void setClassArrStr(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			 this.classArr=JsonUtils.readValues(value, String.class);
			}
		this.classArrStr = value;
	}
	
	
	
	/**
	 * 类名
	 */
     @WhereSQL(sql="classArr=:H5drag_classArrStr")
	public java.lang.String getClassArrStr() {
		return this.classArrStr;
	}
     
     
     public java.lang.String getListArrStr() {
         return listArrStr;
     }

     public void setListArrStr(java.lang.String listArrStr) {
         this.listArrStr = listArrStr;
         if(StringUtils.isNotBlank(listArrStr)) {
             this.listArr=JsonUtils.readValues(listArrStr, H5dragDto.class);
         }
       
     }
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("ajax请求的URL[").append(getAjaxURL()).append("],")
			.append("大标题[").append(getPubTitle()).append("],")
			.append("组件[").append(getModule()).append("],")
			.append("背景色[").append(getBgColor()).append("],")
			.append("类名[").append(getClassArr()).append("],")
			.append("数据 array[").append(getListArrStr()).append("],")
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
		if(obj instanceof H5drag == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		H5drag other = (H5drag)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}


    @Transient
    public List<H5dragDto> getListArr() {
        return listArr;
    }
    
    public void setListArr(List<H5dragDto> listArr) {
        this.listArr = listArr;
        this.listArrStr=JsonUtils.writeValueAsString(listArr);
    }

    @Transient
    public List<String> getClassArr() {
        return classArr;
    }

    public void setClassArr(List<String> classArr) {
        this.classArrStr=JsonUtils.writeValueAsString(classArr);
        this.classArr = classArr;
    }
    
    
}

	
