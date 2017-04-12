package org.springrain.questionnaire.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.util.ContentConstant;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.questionnaire.directive.QuestionnaireDetailsListDirective;
import org.springrain.questionnaire.entity.QuestionnaireAnswer;
import org.springrain.questionnaire.service.IQuestionnaireAnswerService;
import org.springrain.system.entity.User;
import org.springrain.system.service.BaseSpringrainServiceImpl;

import freemarker.core.Environment;

/**
 * 答案相关
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-04-07 11:27:32
 * @see org.springrain.cms.base.service.impl.QuestionnaireAnswer
 */
@Service("questionnaireAnswerService")
public class QuestionnaireAnswerServiceImpl extends BaseSpringrainServiceImpl
		implements IQuestionnaireAnswerService {

	@Override
	public String save(Object entity) throws Exception {
		QuestionnaireAnswer questionnaireAnswer = (QuestionnaireAnswer) entity;
		return super.save(questionnaireAnswer).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		QuestionnaireAnswer questionnaireAnswer = (QuestionnaireAnswer) entity;
		return super.saveorupdate(questionnaireAnswer).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		QuestionnaireAnswer questionnaireAnswer = (QuestionnaireAnswer) entity;
		return super.update(questionnaireAnswer);
	}

	@Override
	public QuestionnaireAnswer findQuestionnaireAnswerById(Object id)
			throws Exception {
		return super.findById(id, QuestionnaireAnswer.class);
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object o) throws Exception {
		return super.findListDataByFinder(finder, page, clazz, o);
	}

	/**
	 * 根据查询列表的宏,导出Excel
	 * 
	 * @param finder
	 *            为空则只查询 clazz表
	 * @param ftlurl
	 *            类表的模版宏
	 * @param page
	 *            分页对象
	 * @param clazz
	 *            要查询的对象
	 * @param o
	 *            querybean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl,
			Page page, Class<T> clazz, Object o) throws Exception {
		return super.findDataExportExcel(finder, ftlurl, page, clazz, o);
	}

	@Override
	public List<QuestionnaireAnswer> findListByQuestionIds(List<String> qdIdList)
			throws Exception {
		Finder finder = Finder.getSelectFinder(QuestionnaireAnswer.class);
		finder.append(" where questionId in (:questionIds) order by sortno asc ").setParam("questionIds", qdIdList);
		return super.queryForList(finder, QuestionnaireAnswer.class);
	}

	@Override
	public List<QuestionnaireAnswer> findListByIdsForTag(List<String> idList,
			int orderBy) throws Exception {
		if(idList == null || idList.size() == 0){
			return null;
		}
		
		List<QuestionnaireAnswer> qaList;
		Finder finder = Finder.getSelectFinder(QuestionnaireAnswer.class);
		finder.append(" where id in (:ids) ").setParam("ids", idList);
		finder.append(getOrderSql(orderBy));
		
		qaList = super.queryForList(finder,QuestionnaireAnswer.class);
		
		if(qaList == null || qaList.size() == 0){
			return qaList;
		}
		
		return qaList;
	}

	@Override
	public List<QuestionnaireAnswer> findListForTag(Map params,
			Environment env, String siteId, String businessId) throws Exception {
		if(StringUtils.isEmpty(siteId) || StringUtils.isEmpty(businessId) 
				|| StringUtils.isEmpty(DirectiveUtils.getString("questionId", params))){
			return null;
		}
		
		Finder finder = Finder.getSelectFinder(QuestionnaireAnswer.class);
		finder.append(" where siteId = :siteId and businessId = :businessId and questionId = :questionId ")
			.setParam("siteId", siteId).setParam("businessId", businessId)
			.setParam("questionId", DirectiveUtils.getString("questionId", params));

		// 常用参数（表与实体类直接对应的字段）
		QuestionnaireAnswer ccParams = new QuestionnaireAnswer();
		BeanUtils.populate(ccParams, params);
		super.getFinderWhereByQueryBean(finder, ccParams);
		
		// 分页
		Page page = DirectiveUtils.getPage("page", params);
		
		// 排序
		finder.append(getOrderSql(QuestionnaireDetailsListDirective.getOrderBy(params)));
		
		List<QuestionnaireAnswer> qaList = super.queryForList(finder, QuestionnaireAnswer.class, page);
		if(qaList == null || qaList.size() == 0){
			return qaList;
		}

		// 设置创建人
		this.setCreatePersonName(qaList);
		
		return qaList;
	}

	/**
	 * 获取排序sql
	 * @param orderBy
	 * @return
	 */
	private String getOrderSql(int orderBy){
		String orderSql = "";
		switch (orderBy) {
		case 0: // 默认排序 序号排序 升序
			orderSql = " order by sortno asc ";
			break;
		case 1: // answerType 答案类型 升序
			orderSql = " order by answerType asc ";
			break;
		case 2: // answerType 答案类型 降序
			orderSql = " order by answerType desc ";
			break;
		case 3: // correct 是否是正确答案 升序
			orderSql = " order by correct asc ";
			break;
		case 4: // correct 是否是正确答案 降序
			orderSql = " order by correct desc ";
			break;
		case 5: // createDate 创建时间 升序
			orderSql = " order by createDate asc ";
			break;
		case 6: // createDate 创建时间 降序
			orderSql = " order by createDate desc ";
			break;
		case 7: // active 是否可用升序
			orderSql = " order by active asc ";
			break;
		case 8: // active 是否可用 降序
			orderSql = " order by active desc ";
			break;
		case 9: // 序号排序 降序
			orderSql = " order by sortno desc ";
			break;
		default:
			break;
		}
		return orderSql;
	}
	
	/**
	 * 设置创建人
	 * @param qdList
	 */
	private void setCreatePersonName(List<QuestionnaireAnswer> qaList) throws Exception {
		List<String> userIdList = new ArrayList<String>();
		for(QuestionnaireAnswer qa : qaList){
			userIdList.add(qa.getCreatePerson());
		}
		
		Finder finder = Finder.getSelectFinder(User.class);
		finder.append(" where id in (:userIds) ").setParam("userIds", userIdList);
		List<User> userList = super.queryForList(finder, User.class);
	
		for(QuestionnaireAnswer qa : qaList){
			for(User user:userList){
				if(qa.getCreatePerson().equals(user.getId())){
					qa.setCreatePersonName(user.getName());
					break;
				}
			}
		}
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		// 逻辑删除
		if(StringUtils.isEmpty(id)){
			return false;
		}
		Finder finder = Finder.getUpdateFinder(QuestionnaireAnswer.class);
		finder.append(" active = :active where id = :id ")
			.setParam("active", ContentConstant.CONTENT_ACTIVE_NO).setParam("id", id);
		super.update(finder);
		return true;
	}
	
	
	
}
