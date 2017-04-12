package org.springrain.questionnaire.service;

import java.io.File;

import org.springrain.questionnaire.entity.QuestionnaireAnswerRecord;
import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.weixin.sdk.mp.bean.result.WxMpUser;
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

	/**
	 * 保存答题记录（从微信端提交的答题记录）
	 * @param wxMpUser 微信用户信息
	 * @param questionnaireAnswerRecord 答题记录
	 * @return 主键
	 * @throws Exception
	 */
	String saveAnswerRecordFromWeixin(WxMpUser wxMpUser,QuestionnaireAnswerRecord questionnaireAnswerRecord)
		throws Exception;
	
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param ftlurl
	 * @param siteId
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	public <T> File findDataExportExcel(String ftlurl,String siteId,String businessId) throws Exception;
	
}
