package  org.springrain.questionnaire.web.s;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.questionnaire.entity.QuestionnaireAnswerRecord;
import org.springrain.questionnaire.service.IQuestionnaireAnswerRecordService;
import org.springrain.weixin.sdk.common.api.IWxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfigService;
import org.springrain.weixin.sdk.mp.api.IWxMpUserService;
import org.springrain.weixin.sdk.mp.bean.result.WxMpUser;


/**
 * 答题记录相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:46
 * @see org.springrain.cms.base.web.QuestionnaireAnswerRecord
 */
@Controller
@RequestMapping(value="/s/{siteId}/{businessId}/questionnaireanswerrecord")
public class QuestionnaireAnswerRecordController extends SiteBaseController {
	
	@Resource
	IWxMpUserService wxMpUserService;
	
	@Resource
	IWxMpConfigService wxMpConfigService;
	
	@Resource
	private IQuestionnaireAnswerRecordService questionnaireAnswerRecordService;
	
	/**
	 * 提交答题记录
	 * @param req
	 * @param res
	 * @param siteId
	 * @param businessId
	 * @return
	 */
	@RequestMapping("/ajax/submitAnswerRecord")
	@ResponseBody
	public ReturnDatas submitAnswerRecord(HttpServletRequest req,HttpServletResponse res,
			@PathVariable String siteId,@PathVariable String businessId,
			QuestionnaireAnswerRecord questionnaireAnswerRecord){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String openId = String.valueOf(req.getSession().getAttribute("openId"));
			if(StringUtils.isEmpty(openId)){
				return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.ADD_FAIL);
			}
			IWxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
			WxMpUser wxMpUser = wxMpUserService.userInfo(wxmpconfig, openId);
			if(wxMpUser == null){
				return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.ADD_FAIL);
			}
			String id = questionnaireAnswerRecordService.saveAnswerRecordFromWeixin(wxMpUser,questionnaireAnswerRecord);
			if(StringUtils.isEmpty(id)){
				return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.ADD_FAIL);
			}
			rd.setMessage(MessageUtils.ADD_SUCCESS);
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.ERROR, MessageUtils.ADD_FAIL);
			logger.error(e.getMessage(),e);
		}
		return rd;
	}
	
	/**
	 * 导出答题统计
	 * @param req
	 * @param res
	 * @param siteId
	 * @param businessId
	 */
	@RequestMapping("/exportAnswerStatistics")
	public void exportAnswerStatistics(HttpServletRequest req,HttpServletResponse res,@PathVariable String siteId,
			@PathVariable String businessId){
		try {
			String questionnaireId = req.getParameter("questionnaireId");
			String listurl = "/questionnaire/s/questionnaire/answerStatisticsExport";
			File file = questionnaireAnswerRecordService.findDataExportExcel(listurl, siteId, questionnaireId);
			
			CmsContent questionnaire = questionnaireAnswerRecordService.findById(questionnaireId, CmsContent.class);
			String title = questionnaire.getTitle();
			String fileName = title + GlobalStatic.excelext;
			downFile(res, file, fileName,true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
