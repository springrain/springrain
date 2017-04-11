package org.springrain.questionnaire.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * 答题记录
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:46
 * @see org.springrain.cms.base.entity.QuestionnaireAnswerRecord
 */
@Table(name="t_questionnaire_answer_record")
public class QuestionnaireAnswerRecord  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "答题记录表";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_SITEID = "站点id";
	public static final String ALIAS_BUSINESSID = "业务Id （所属问卷的id）";
	public static final String ALIAS_USERID = "答题人ID（可以是userId，也可以是openId）";
	public static final String ALIAS_USERNAME = "用户名称（微信昵称）";
	public static final String ALIAS_QUESTIONID = "所属问题ID";
	public static final String ALIAS_ANSWERIDS = "选择的答案的ID集合，以","分隔  ";
	public static final String ALIAS_ANSWERCONTENT = "答案内容";
	public static final String ALIAS_CREATEDATE = "创建时间（提交答案的时间）";
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
	 * 答题人ID（可以是userId，也可以是openId）
	 */
	private java.lang.String userId;
	/**
	 * 用户名称（微信昵称）
	 */
	private java.lang.String userName;
	/**
	 * 所属问题ID
	 */
	private java.lang.String questionId;
	/**
	 * 选择的答案的ID集合，以","分隔  
	 */
	private java.lang.String answerIds;
	/**
	 * 答案内容
	 */
	private java.lang.String answerContent;
	/**
	 * 创建时间（提交答案的时间）
	 */
	private java.util.Date createDate;
	//columns END 数据库字段结束
	
	//concstructor

	public QuestionnaireAnswerRecord(){
	}

	public QuestionnaireAnswerRecord(
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
     @WhereSQL(sql="id=:QuestionnaireAnswerRecord_id")
	public java.lang.String getId() {
		return this.id;
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
     @WhereSQL(sql="siteId=:QuestionnaireAnswerRecord_siteId")
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
     @WhereSQL(sql="businessId=:QuestionnaireAnswerRecord_businessId")
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
		/**
		 * 答题人ID（可以是userId，也可以是openId）
		 */
	public void setUserId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userId = value;
	}
	
	
	
	/**
	 * 答题人ID（可以是userId，也可以是openId）
	 */
     @WhereSQL(sql="userId=:QuestionnaireAnswerRecord_userId")
	public java.lang.String getUserId() {
		return this.userId;
	}
		/**
		 * 用户名称（微信昵称）
		 */
	public void setUserName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userName = value;
	}
	
	
	
	/**
	 * 用户名称（微信昵称）
	 */
     @WhereSQL(sql="userName=:QuestionnaireAnswerRecord_userName")
	public java.lang.String getUserName() {
		return this.userName;
	}
		/**
		 * 所属问题ID
		 */
	public void setQuestionId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.questionId = value;
	}
	
	
	
	/**
	 * 所属问题ID
	 */
     @WhereSQL(sql="questionId=:QuestionnaireAnswerRecord_questionId")
	public java.lang.String getQuestionId() {
		return this.questionId;
	}
		/**
		 * 选择的答案的ID集合，以","分隔  
		 */
	public void setAnswerIds(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerIds = value;
	}
	
	
	
	/**
	 * 选择的答案的ID集合，以","分隔  
	 */
     @WhereSQL(sql="answerIds=:QuestionnaireAnswerRecord_answerIds")
	public java.lang.String getAnswerIds() {
		return this.answerIds;
	}
		/**
		 * 答案内容
		 */
	public void setAnswerContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerContent = value;
	}
	
	
	
	/**
	 * 答案内容
	 */
     @WhereSQL(sql="answerContent=:QuestionnaireAnswerRecord_answerContent")
	public java.lang.String getAnswerContent() {
		return this.answerContent;
	}
		/*
	public String getcreateDateString() {
		return DateUtils.convertDate2String(FORMAT_CREATEDATE, getcreateDate());
	}
	public void setcreateDateString(String value) throws ParseException{
		setcreateDate(DateUtils.convertString2Date(FORMAT_CREATEDATE,value));
	}*/
	
		/**
		 * 创建时间（提交答案的时间）
		 */
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	
	
	/**
	 * 创建时间（提交答案的时间）
	 */
     @WhereSQL(sql="createDate=:QuestionnaireAnswerRecord_createDate")
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("主键[").append(getId()).append("],")
			.append("站点id[").append(getSiteId()).append("],")
			.append("业务Id （所属问卷的id）[").append(getBusinessId()).append("],")
			.append("答题人ID（可以是userId，也可以是openId）[").append(getUserId()).append("],")
			.append("用户名称（微信昵称）[").append(getUserName()).append("],")
			.append("所属问题ID[").append(getQuestionId()).append("],")
			.append("选择的答案的ID集合，以\",\"分隔  [").append(getAnswerIds()).append("],")
			.append("答案内容[").append(getAnswerContent()).append("],")
			.append("创建时间（提交答案的时间）[").append(getCreateDate()).append("],")
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
		if(obj instanceof QuestionnaireAnswerRecord == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		QuestionnaireAnswerRecord other = (QuestionnaireAnswerRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
