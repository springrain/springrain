package org.springrain.questionnaire.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.util.ContentConstant;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.questionnaire.directive.QuestionnaireDetailsListDirective;
import org.springrain.questionnaire.entity.QuestionnaireAnswer;
import org.springrain.questionnaire.entity.QuestionnaireDetails;
import org.springrain.questionnaire.service.IQuestionnaireAnswerService;
import org.springrain.questionnaire.service.IQuestionnaireDetailsService;
import org.springrain.system.entity.User;
import org.springrain.system.service.BaseSpringrainServiceImpl;

import freemarker.core.Environment;

/**
 * 问卷详情相关
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-04-07 11:27:16
 * @see org.springrain.cms.base.service.impl.QuestionnaireDetails
 */
@Service("questionnaireDetailsService")
public class QuestionnaireDetailsServiceImpl extends BaseSpringrainServiceImpl
		implements IQuestionnaireDetailsService {

	@Resource
	private IQuestionnaireAnswerService questionnaireAnswerService;
	
	@Override
	public String save(Object entity) throws Exception {
		QuestionnaireDetails questionnaireDetails = (QuestionnaireDetails) entity;
		return super.save(questionnaireDetails).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		QuestionnaireDetails questionnaireDetails = (QuestionnaireDetails) entity;
		return super.saveorupdate(questionnaireDetails).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		QuestionnaireDetails questionnaireDetails = (QuestionnaireDetails) entity;
		return super.update(questionnaireDetails);
	}

	@Override
	public QuestionnaireDetails findQuestionnaireDetailsById(Object id)
			throws Exception {
		return super.findById(id, QuestionnaireDetails.class);
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
	public List<QuestionnaireDetails> findListByIdsForTag(List<String> idList,
			Integer orderBy) throws Exception {
		if(idList == null || idList.size() == 0){
			return null;
		}
		
		List<QuestionnaireDetails> qdList;
		Finder finder = Finder.getSelectFinder(QuestionnaireDetails.class);
		finder.append(" where id in (:ids) ").setParam("ids", idList);
		finder.append(getOrderSql(orderBy));
		
		qdList = super.queryForList(finder,QuestionnaireDetails.class);
		
		if(qdList == null || qdList.size() == 0){
			return qdList;
		}

		// 设置答案
		this.setQuestionnaireAnswerList(qdList);
		
		return qdList;
	}

	@Override
	public List<QuestionnaireDetails> findListForTag(Map params,
			Environment env, String siteId, String businessId) throws Exception {
		if(StringUtils.isEmpty(siteId) || StringUtils.isEmpty(businessId)){
			return null;
		}
		
		Finder finder = Finder.getSelectFinder(QuestionnaireDetails.class);
		finder.append(" where siteId = :siteId and businessId = :businessId ")
			.setParam("siteId", siteId).setParam("businessId", businessId);

		// 常用参数（表与实体类直接对应的字段）
		QuestionnaireDetails ccParams = new QuestionnaireDetails();
		BeanUtils.populate(ccParams, params);
		super.getFinderWhereByQueryBean(finder, ccParams);
		
		// 分页
		Page page = DirectiveUtils.getPage("page", params);
		
		// 排序
		finder.append(getOrderSql(QuestionnaireDetailsListDirective.getOrderBy(params)));
		
		List<QuestionnaireDetails> qdList = super.queryForList(finder, QuestionnaireDetails.class, page);
		if(qdList == null || qdList.size() == 0){
			return qdList;
		}
		
		// 设置答案
		this.setQuestionnaireAnswerList(qdList);

		// 设置创建人
		this.setCreatePersonName(qdList);
		
		return qdList;
	}

	/**
	 * 设置答案
	 * @param qdList
	 * @return
	 */
	private void setQuestionnaireAnswerList(List<QuestionnaireDetails> qdList) throws Exception{
		List<String> qdIdList = new ArrayList<String>();
		for(QuestionnaireDetails qd : qdList){
			qdIdList.add(qd.getId());
		}
		List<QuestionnaireAnswer> qaList = questionnaireAnswerService.findListByQuestionIds(qdIdList);
		if(qaList!=null && qaList.size()>0){
			for(QuestionnaireDetails qd:qdList){
				List<QuestionnaireAnswer> resList = new ArrayList<QuestionnaireAnswer>();
				for(QuestionnaireAnswer qa:qaList){
					if(qd.getId().equals(qa.getQuestionId())){
						resList.add(qa);
					}
				}
				qd.setQuestionnaireAnswerList(resList);
			}
		}
	}
	
	/**
	 * 设置答案
	 * @param qdList
	 * @return
	 */
	private void setQuestionnaireAnswerList(QuestionnaireDetails qd) throws Exception{
		Finder finder_qa = Finder.getSelectFinder(QuestionnaireDetails.class);
		finder_qa.append(" where questionId = :questionId ")
			.setParam("questionId", qd.getId());
		List<QuestionnaireAnswer> qaList = 
				questionnaireAnswerService.queryForList(finder_qa, QuestionnaireAnswer.class);
		if(qaList!=null && qaList.size()>0){
			List<QuestionnaireAnswer> resList = new ArrayList<QuestionnaireAnswer>();
			for(QuestionnaireAnswer qa:qaList){
				if(qd.getId().equals(qa.getQuestionId())){
					resList.add(qa);
				}
			}
			qd.setQuestionnaireAnswerList(resList);
		}
	}

	/**
	 * 设置创建人
	 * @param qdList
	 */
	private void setCreatePersonName(List<QuestionnaireDetails> qdList) throws Exception {
		List<String> userIdList = new ArrayList<String>();
		for(QuestionnaireDetails qd : qdList){
			userIdList.add(qd.getCreatePerson());
		}
		
		Finder finder = Finder.getSelectFinder(User.class);
		finder.append(" where id in (:userIds) ").setParam("userIds", userIdList);
		List<User> userList = super.queryForList(finder, User.class);
	
		for(QuestionnaireDetails qd : qdList){
			for(User user:userList){
				if(qd.getCreatePerson().equals(user.getId())){
					qd.setCreatePersonName(user.getName());
					break;
				}
			}
		}
	}
	
	/**
	 * 获取排序sql
	 * @param orderBy
	 * @return
	 */
	private String getOrderSql(int orderBy){
		String orderSql = "";
		switch (orderBy) {
		case 0: // 默认排序
			orderSql = " order by sortno asc ";
			break;
		case 1: // title 标题 升序
			orderSql = " order by title asc ";
			break;
		case 2: // title 标题 降序
			orderSql = " order by title desc ";
			break;
		case 3: // questionType 问题类型 升序
			orderSql = " order by questionType asc ";
			break;
		case 4: // questionType 问题类型 降序
			orderSql = " order by questionType desc ";
			break;
		case 5: // createDate 创建时间 升序
			orderSql = " order by createDate asc ";
			break;
		case 6: // createDate 创建时间 降序
			orderSql = " order by createDate desc ";
			break;
		case 7: // active 是否可用 升序
			orderSql = " order by active asc ";
			break;
		case 8: // active 是否可用 降序
			orderSql = " order by active desc ";
			break;
		default:
			break;
		}
		return orderSql;
	}

	@Override
	public boolean deleteById(String id) throws Exception{
		if(StringUtils.isEmpty(id)){
			return false;
		}
		
		// 逻辑删除
		Finder finder = Finder.getUpdateFinder(QuestionnaireDetails.class);
		finder.append(" active=0 where id=:id").setParam("id", id);
		super.update(finder);
		return true;
	}

	@Override
	public QuestionnaireDetails findById(String id) throws Exception{
		QuestionnaireDetails qd = super.findById(id, QuestionnaireDetails.class);
		if(qd!=null){
			this.setQuestionnaireAnswerList(qd);
		}
		return qd;
	}

	@Override
	public QuestionnaireDetails findQuestionnaireDetailsSide(String siteId,
			String businessId, String id, Boolean next) throws Exception {
		if(StringUtils.isEmpty(siteId) || StringUtils.isEmpty(businessId) || 
				StringUtils.isEmpty(id) ){
			return null;
		}
		
		Finder finder = Finder.getSelectFinder(QuestionnaireDetails.class);
		finder.append(" where siteId = :siteId and businessId = :businessId	")
			.setParam("siteId", siteId).setParam("businessId", businessId);
		if(next){
			// 下一题
			finder.append(" and sortno >= ( select sortno from ").append(finder.getTableName(QuestionnaireDetails.class))
				.append(" where id=:id ) and id != :id ").setParam("id", id).setParam("id", id);
			finder.append(" and active = :active ").setParam("active", ContentConstant.CONTENT_ACTIVE_YES);
			finder.append(" order by sortno asc limit 0,1 ");
		}else{
			// 上一题
			finder.append(" and sortno <= ( select sortno from ").append(finder.getTableName(QuestionnaireDetails.class))
			.append(" where id=:id ) and id != :id ").setParam("id", id).setParam("id", id);
			finder.append(" and active = :active ").setParam("active", ContentConstant.CONTENT_ACTIVE_YES);
			finder.append(" order by sortno desc limit 0,1 ");
		}
		List<QuestionnaireDetails> questionnaireDetailsList = super.queryForList(finder, QuestionnaireDetails.class);
		if(questionnaireDetailsList!=null && questionnaireDetailsList.size()>0){
			return questionnaireDetailsList.get(0);
		}
		return null;
	}
	
}
