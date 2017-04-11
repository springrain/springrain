package org.springrain.questionnaire.service;

import java.util.List;
import java.util.Map;

import org.springrain.questionnaire.entity.QuestionnaireDetails;
import org.springrain.system.service.IBaseSpringrainService;

import freemarker.core.Environment;
/**
 * 问卷详情相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:16
 * @see org.springrain.cms.base.service.QuestionnaireDetails
 */
public interface IQuestionnaireDetailsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QuestionnaireDetails findQuestionnaireDetailsById(Object id) throws Exception;

	/**
	 * 根据IDs查询
	 * @param idList
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	List<QuestionnaireDetails> findListByIdsForTag(List<String> idList, Integer orderBy) throws Exception;

	/**
	 * 查询列表
	 * @param params
	 * @param env
	 * @param siteId
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	List<QuestionnaireDetails> findListForTag(Map params, Environment env,
			String siteId, String businessId) throws Exception;

	/**
	 * 删除详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean deleteById(String id) throws Exception;

	/**
	 * 根据ID查找问卷信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QuestionnaireDetails findById(String id) throws Exception;

	/**
	 * 查找问题的上一个或下一个
	 * @param id
	 * @param siteId
	 * @param channelId
	 * @param next true：下一个内容；false：下一个内容
	 * @return
	 */
	QuestionnaireDetails findQuestionnaireDetailsSide(String siteId,
			String businessId, String id, Boolean next) throws Exception;
	
}
