package org.springrain.questionnaire.service;

import org.springrain.questionnaire.entity.QuestionnaireAnswerRecord;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * 答题记录相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:46
 * @see org.springrain.cms.base.service.QuestionnaireAnswerRecord
 */
public interface IQuestionnaireAnswerRecordService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QuestionnaireAnswerRecord findQuestionnaireAnswerRecordById(Object id) throws Exception;
	
}
