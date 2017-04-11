package org.springrain.questionnaire.service;

import java.util.List;
import java.util.Map;

import org.springrain.questionnaire.entity.QuestionnaireAnswer;
import org.springrain.questionnaire.entity.QuestionnaireDetails;
import org.springrain.system.service.IBaseSpringrainService;

import freemarker.core.Environment;
/**
 * 答案相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:32
 * @see org.springrain.cms.base.service.QuestionnaireAnswer
 */
public interface IQuestionnaireAnswerService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QuestionnaireAnswer findQuestionnaireAnswerById(Object id) throws Exception;

	/**
	 * 根据问题ID集合查询答案
	 * @param qdIdList
	 * @return
	 */
	List<QuestionnaireAnswer> findListByQuestionIds(List<String> qdIdList) throws Exception;

	/**
	 * 根据ID集合查询答案集合
	 * @param asList
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	List<QuestionnaireAnswer> findListByIdsForTag(List<String> asList, int orderBy) throws Exception;

	/**
	 * 查询答案集合
	 * @param params
	 * @param env
	 * @param siteId
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	List<QuestionnaireAnswer> findListForTag(Map params, Environment env,
			String siteId, String channelId) throws Exception;

	/**
	 * 删除答案
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean deleteById(String id) throws Exception;
	
}
