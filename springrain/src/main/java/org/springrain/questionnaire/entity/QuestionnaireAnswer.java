package org.springrain.questionnaire.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * 答案
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:32
 * @see org.springrain.cms.base.entity.QuestionnaireAnswer
 */
@Table(name="t_questionnaire_answer")
public class QuestionnaireAnswer  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "答案表";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_SITEID = "站点id";
	public static final String ALIAS_BUSINESSID = "业务Id （所属问卷的id）";
	public static final String ALIAS_QUESTIONID = "所属问题的id";
	public static final String ALIAS_NEXTQUESTIONIDS = "此答案开启的问题的ID集合，以","分隔";
	public static final String ALIAS_CONTENT = "答案内容";
	public static final String ALIAS_ANSWERTYPE = "答案类型，1 固定内容，2 可填写";
	public static final String ALIAS_CORRECT = "是否是正确答案，1 是，2 不是 （调查问卷类型的问题的答案默认为1）";
	public static final String ALIAS_ACTIVE = "是否可用，0 不可用，1 可用";
	public static final String ALIAS_CREATEDATE = "创建时间";
	public static final String ALIAS_CREATEPERSON = "创建人ID";
	public static final String ALIAS_SORTNO = "序号 ";
    */
	//date formats
	//public static final String FORMAT_CREATEDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 站点id
	 */
	private java.lang.String siteId;
	/**
	 * 业务Id （所属问卷的id）
	 */
	private java.lang.String businessId;
	/**
	 * 所属问题的id
	 */
	private java.lang.String questionId;
	/**
	 * 此答案开启的问题的ID集合，以","分隔
	 */
	private java.lang.String nextQuestionIds;
	/**
	 * 答案内容
	 */
	private java.lang.String content;
	/**
	 * 答案类型，1 固定内容，2 可填写
	 */
	private java.lang.Integer answerType;
	/**
	 * 是否是正确答案，1 是，2 不是 （调查问卷类型的问题的答案默认为1）
	 */
	private java.lang.Integer correct;
	/**
	 * 是否可用，0 不可用，1 可用
	 */
	private java.lang.Integer active;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 创建人ID
	 */
	private java.lang.String createPerson;
	/**
	 * 序号 
	 */
	private java.lang.Integer sortno;
	//columns END 数据库字段结束
	
	private String createPersonName;
	
	/**
	 * 选择此答案的统计数
	 */
	private Integer selCount;
	
	//concstructor
	
	public QuestionnaireAnswer(){
	}

	public QuestionnaireAnswer(
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
     @WhereSQL(sql="id=:QuestionnaireAnswer_id")
	public java.lang.String getId() {
		return this.id;
	}
	
	@Transient
	public Integer getSelCount() {
		return selCount;
	}

	public void setSelCount(Integer selCount) {
		this.selCount = selCount;
	}

	@Transient
	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
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
     @WhereSQL(sql="siteId=:QuestionnaireAnswer_siteId")
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
     @WhereSQL(sql="businessId=:QuestionnaireAnswer_businessId")
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
		/**
		 * 所属问题的id
		 */
	public void setQuestionId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.questionId = value;
	}
	
	
	
	/**
	 * 所属问题的id
	 */
     @WhereSQL(sql="questionId=:QuestionnaireAnswer_questionId")
	public java.lang.String getQuestionId() {
		return this.questionId;
	}
		/**
		 * 此答案开启的问题的ID集合，以","分隔
		 */
	public void setNextQuestionIds(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nextQuestionIds = value;
	}
	
	
	
	/**
	 * 此答案开启的问题的ID集合，以","分隔
	 */
     @WhereSQL(sql="nextQuestionIds=:QuestionnaireAnswer_nextQuestionIds")
	public java.lang.String getNextQuestionIds() {
		return this.nextQuestionIds;
	}
		/**
		 * 答案内容
		 */
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
	
	
	/**
	 * 答案内容
	 */
     @WhereSQL(sql="content like :%QuestionnaireAnswer_content%")
	public java.lang.String getContent() {
		return this.content;
	}
		/**
		 * 答案类型，1 固定内容，2 可填写
		 */
	public void setAnswerType(java.lang.Integer value) {
		this.answerType = value;
	}
	
	
	
	/**
	 * 答案类型，1 固定内容，2 可填写
	 */
     @WhereSQL(sql="answerType=:QuestionnaireAnswer_answerType")
	public java.lang.Integer getAnswerType() {
		return this.answerType;
	}
		/**
		 * 是否是正确答案，1 是，2 不是 （调查问卷类型的问题的答案默认为1）
		 */
	public void setCorrect(java.lang.Integer value) {
		this.correct = value;
	}
	
	
	
	/**
	 * 是否是正确答案，1 是，2 不是 （调查问卷类型的问题的答案默认为1）
	 */
     @WhereSQL(sql="right=:QuestionnaireAnswer_correct")
	public java.lang.Integer getCorrect() {
		return this.correct;
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
     @WhereSQL(sql="active=:QuestionnaireAnswer_active")
	public java.lang.Integer getActive() {
		return this.active;
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
     @WhereSQL(sql="createDate=:QuestionnaireAnswer_createDate")
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
     @WhereSQL(sql="createPerson=:QuestionnaireAnswer_createPerson")
	public java.lang.String getCreatePerson() {
		return this.createPerson;
	}
		/**
		 * 序号 
		 */
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
	
	
	/**
	 * 序号 
	 */
     @WhereSQL(sql="sortno=:QuestionnaireAnswer_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("主键[").append(getId()).append("],")
			.append("站点id[").append(getSiteId()).append("],")
			.append("业务Id （所属问卷的id）[").append(getBusinessId()).append("],")
			.append("所属问题的id[").append(getQuestionId()).append("],")
			.append("此答案开启的问题的ID集合，以\",\"分隔[").append(getNextQuestionIds()).append("],")
			.append("答案内容[").append(getContent()).append("],")
			.append("答案类型，1 固定内容，2 可填写[").append(getAnswerType()).append("],")
			.append("是否是正确答案，1 是，2 不是 （调查问卷类型的问题的答案默认为1）[").append(getContent()).append("],")
			.append("是否可用，0 不可用，1 可用[").append(getActive()).append("],")
			.append("创建时间[").append(getCreateDate()).append("],")
			.append("创建人ID[").append(getCreatePerson()).append("],")
			.append("序号 [").append(getSortno()).append("],")
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
		if(obj instanceof QuestionnaireAnswer == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		QuestionnaireAnswer other = (QuestionnaireAnswer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
