package org.springrain.questionnaire.entity;

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
 * 问卷详情（问题）表
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:16
 * @see org.springrain.cms.base.entity.QuestionnaireDetails
 */
@Table(name="t_questionnaire_details")
public class QuestionnaireDetails  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "问卷详情（问题）表，问卷表使用cms_content替代";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_PID = "上一题id";
	public static final String ALIAS_SITEID = "站点id";
	public static final String ALIAS_BUSINESSID = "业务Id （所属问卷的id）";
	public static final String ALIAS_TITLE = "问题标题";
	public static final String ALIAS_QUESTIONTYPE = "问题类型，1 选择题，2 简答题";
	public static final String ALIAS_CREATEDATE = "创建时间";
	public static final String ALIAS_CREATEPERSON = "创建人ID";
	public static final String ALIAS_ACTIVE = "是否可用，0 不可用，1 可用";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_SORTNO = "序号";
    */
	//date formats
	//public static final String FORMAT_CREATEDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 上一题id
	 */
	private java.lang.String pid;
	/**
	 * 站点id
	 */
	private java.lang.String siteId;
	/**
	 * 业务Id （所属问卷的id）
	 */
	private java.lang.String businessId;
	/**
	 * 问题标题
	 */
	private java.lang.String title;
	/**
	 * 问题类型，1 选择题，2 简答题
	 */
	private java.lang.Integer questionType;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 创建人ID
	 */
	private java.lang.String createPerson;
	/**
	 * 是否可用，0 不可用，1 可用
	 */
	private java.lang.Integer active;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 序号
	 */
	private java.lang.Integer sortno;
	//columns END 数据库字段结束
	
	/**
	 * 答案列表
	 */
	private List<QuestionnaireAnswer> questionnaireAnswerList;
	
	/**
	 * 创建人名称
	 */
	private String createPersonName;
	//concstructor

	public QuestionnaireDetails(){
	}

	public QuestionnaireDetails(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 主键
		 */
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	
	
	/**
	 * 主键
	 */
	@Id
     @WhereSQL(sql="id=:QuestionnaireDetails_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 上一题id
		 */
	public void setPid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pid = value;
	}
	
	
	
	/**
	 * 上一题id
	 */
     @WhereSQL(sql="pid=:QuestionnaireDetails_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
		/**
		 * 站点id
		 */
	public void setSiteId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.siteId = value;
	}
	
	
	
	/**
	 * 站点id
	 */
     @WhereSQL(sql="siteId=:QuestionnaireDetails_siteId")
	public java.lang.String getSiteId() {
		return this.siteId;
	}
		/**
		 * 业务Id （所属问卷的id）
		 */
	public void setBusinessId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.businessId = value;
	}
	
	
	
	/**
	 * 业务Id （所属问卷的id）
	 */
     @WhereSQL(sql="businessId=:QuestionnaireDetails_businessId")
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
		/**
		 * 问题标题
		 */
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
	
	
	/**
	 * 问题标题
	 */
     @WhereSQL(sql="title like :%QuestionnaireDetails_title% ")
	public java.lang.String getTitle() {
		return this.title;
	}
		/**
		 * 问题类型，1 选择题，2 简答题
		 */
	public void setQuestionType(java.lang.Integer value) {
		this.questionType = value;
	}
	
	
	
	/**
	 * 问题类型，1 选择题，2 简答题
	 */
     @WhereSQL(sql="questionType=:QuestionnaireDetails_questionType")
	public java.lang.Integer getQuestionType() {
		return this.questionType;
	}
		/*
	public String getcreateDateString() {
		return DateUtils.convertDate2String(FORMAT_CREATEDATE, getcreateDate());
	}
	public void setcreateDateString(String value) throws ParseException{
		setcreateDate(DateUtils.convertString2Date(FORMAT_CREATEDATE,value));
	}*/
	
		/**
		 * 创建时间
		 */
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	
	
	/**
	 * 创建时间
	 */
     @WhereSQL(sql="createDate=:QuestionnaireDetails_createDate")
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
		/**
		 * 创建人ID
		 */
	public void setCreatePerson(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.createPerson = value;
	}
	
	
	
	/**
	 * 创建人ID
	 */
     @WhereSQL(sql="createPerson=:QuestionnaireDetails_createPerson")
	public java.lang.String getCreatePerson() {
		return this.createPerson;
	}
		/**
		 * 是否可用，0 不可用，1 可用
		 */
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
	
	
	/**
	 * 是否可用，0 不可用，1 可用
	 */
     @WhereSQL(sql="active=:QuestionnaireDetails_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
		/**
		 * 备注
		 */
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
	
	
	/**
	 * 备注
	 */
     @WhereSQL(sql="remark=:QuestionnaireDetails_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
		/**
		 * 序号
		 */
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
	@Transient
	public String getCreatePersonName() {
			return createPersonName;
		}

		public void setCreatePersonName(String createPersonName) {
			this.createPersonName = createPersonName;
		}

	/**
	 * 序号
	 */
     @WhereSQL(sql="sortno=:QuestionnaireDetails_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}

     @Transient
	public List<QuestionnaireAnswer> getQuestionnaireAnswerList() {
		return questionnaireAnswerList;
	}

	public void setQuestionnaireAnswerList(
			List<QuestionnaireAnswer> questionnaireAnswerList) {
		this.questionnaireAnswerList = questionnaireAnswerList;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("主键[").append(getId()).append("],")
			.append("上一题id[").append(getPid()).append("],")
			.append("站点id[").append(getSiteId()).append("],")
			.append("业务Id （所属问卷的id）[").append(getBusinessId()).append("],")
			.append("问题标题[").append(getTitle()).append("],")
			.append("问题类型，1 选择题，2 简答题[").append(getQuestionType()).append("],")
			.append("创建时间[").append(getCreateDate()).append("],")
			.append("创建人ID[").append(getCreatePerson()).append("],")
			.append("是否可用，0 不可用，1 可用[").append(getActive()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("序号[").append(getSortno()).append("],")
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
		if(obj instanceof QuestionnaireDetails == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		QuestionnaireDetails other = (QuestionnaireDetails)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
