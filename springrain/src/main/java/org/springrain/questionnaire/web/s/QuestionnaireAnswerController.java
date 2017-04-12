package  org.springrain.questionnaire.web.s;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.questionnaire.entity.QuestionnaireAnswer;
import org.springrain.questionnaire.entity.QuestionnaireDetails;
import org.springrain.questionnaire.service.IQuestionnaireAnswerService;


/**
 * 问题相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:32
 * @see org.springrain.cms.base.web.QuestionnaireAnswer
 */
@Controller
@RequestMapping(value="/s/{siteId}/{businessId}/questionnaireanswer")
public class QuestionnaireAnswerController extends SiteBaseController {
	
	@Resource
	private IQuestionnaireAnswerService questionnaireAnswerService;
	
	/**
	 * 问卷相关页面路径
	 */
	private static final String QUESTIONNAIRE_PAGE_PATH = "/questionnaire/s/questionnaire/";
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param questionnaireAnswer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String siteId, @PathVariable String businessId,
			QuestionnaireAnswer questionnaireAnswer) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnObject.setPage(page);
		returnObject.setQueryBean(questionnaireAnswer);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		model.addAttribute("questionId", request.getParameter("questionId"));
		model.addAttribute("questionnaireId", request.getParameter("questionnaireId"));
		return QUESTIONNAIRE_PAGE_PATH + "questionnaireAnswerList";
	}
	

	/**
	 * 新增、修改问题
	 * @param request
	 * @param response
	 * @param model
	 * @param siteId
	 * @param businessId
	 * @param questionnaireDetails
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnDatas update(HttpServletRequest request,HttpServletResponse response,Model model,
			@PathVariable String siteId,@PathVariable String businessId,QuestionnaireAnswer questionnaireAnswer){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String busId = String.valueOf(request.getParameter("businessId"));
			if(StringUtils.isNotEmpty(busId)){
				businessId = busId;
			}
			String questionId = request.getParameter("questionId");
			// 问卷ID为空的不能保存
			if(questionnaireAnswer == null || 
					(StringUtils.isEmpty(questionnaireAnswer.getQuestionId()) 
							&& StringUtils.isEmpty(questionId))){
				return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.UPDATE_ERROR);
			}
			questionnaireAnswer.setSiteId(siteId); // 站点ID
			questionnaireAnswer.setBusinessId(businessId); // 问卷ID
			if(StringUtils.isEmpty(questionnaireAnswer.getQuestionId())){
				questionnaireAnswer.setQuestionId(questionId);
			}
			if(StringUtils.isEmpty(questionnaireAnswer.getId())){
				questionnaireAnswer.setId(null);
				// 新增
				questionnaireAnswer.setCreateDate(new Date());
				questionnaireAnswer.setCreatePerson(SessionUser.getUserId());
				this.questionnaireAnswerService.save(questionnaireAnswer);
			}else{
				// 修改
				this.questionnaireAnswerService.update(questionnaireAnswer, true);
			}
			rd.setMessage(MessageUtils.UPDATE_SUCCESS);
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.ERROR, MessageUtils.UPDATE_ERROR);
			logger.error(e.getMessage(),e);
		}
		return rd;
	}
	
	/**
	 * 跳转到修改页面
	 * 
	 * @param request
	 * @param model
	 * @param questionnaireDetails
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update/pre")
	public String updatePre(HttpServletRequest request, Model model,
			@PathVariable String siteId, @PathVariable String businessId,
			QuestionnaireDetails questionnaireDetails) 
			throws Exception {
		String id = request.getParameter("id");
		String questionId = request.getParameter("questionId");
		String questionnaireId = request.getParameter("questionnaireId");
		model.addAttribute("id", id);
		model.addAttribute("questionId", questionId);
		model.addAttribute("questionnaireId", questionnaireId);
		return QUESTIONNAIRE_PAGE_PATH + "questionnaireAnswerCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody 
	public  ReturnDatas delete(HttpServletRequest request,@PathVariable String siteId) throws Exception {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		// 执行删除
		try {
			String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
				boolean isSuccess = questionnaireAnswerService.deleteById(id);
				if(isSuccess){
					return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
				}else{
					return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
				}
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
			logger.error(e.getMessage(), e);
		}
		return rd;
	}

}
