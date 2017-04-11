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
import org.springrain.questionnaire.entity.QuestionnaireDetails;
import org.springrain.questionnaire.service.IQuestionnaireDetailsService;


/**
 * 问卷详情相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-07 11:27:16
 * @see org.springrain.cms.base.web.QuestionnaireDetails
 */
@Controller
@RequestMapping(value="/s/{siteId}/{businessId}/questionnairedetails")
public class QuestionnaireDetailsController extends SiteBaseController {
	
	@Resource
	private IQuestionnaireDetailsService questionnaireDetailsService;

	/**
	 * 问卷相关页面路径
	 */
	private static final String QUESTIONNAIRE_PAGE_PATH = "/questionnaire/s/questionnaire/";
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param questionnaireDetails
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String siteId, @PathVariable String businessId,
			QuestionnaireDetails questionnaireDetails) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnObject.setPage(page);
		returnObject.setQueryBean(questionnaireDetails);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		model.addAttribute("questionnaireId", request.getParameter("questionnaireId"));
		return QUESTIONNAIRE_PAGE_PATH + "questionnaireDetailsList";
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
			@PathVariable String siteId,@PathVariable String businessId,QuestionnaireDetails questionnaireDetails){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String questionnaireId = request.getParameter("questionnaireId");
			// 问卷ID为空的不能保存
			if(questionnaireDetails == null || 
					(StringUtils.isEmpty(questionnaireDetails.getBusinessId()) 
							&& StringUtils.isEmpty(questionnaireId))){
				return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.UPDATE_ERROR);
			}
			questionnaireDetails.setSiteId(siteId); // 站点ID
			if(StringUtils.isEmpty(questionnaireDetails.getBusinessId())){
				questionnaireDetails.setBusinessId(questionnaireId);
			}
			if(StringUtils.isEmpty(questionnaireDetails.getId())){
				questionnaireDetails.setId(null);
				// 新增
				questionnaireDetails.setCreateDate(new Date());
				questionnaireDetails.setCreatePerson(SessionUser.getUserId());
				this.questionnaireDetailsService.save(questionnaireDetails);
			}else{
				// 修改
				this.questionnaireDetailsService.update(questionnaireDetails, true);
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
		String questionnaireId = request.getParameter("questionnaireId");
		model.addAttribute("id", id);
		model.addAttribute("questionnaireId", questionnaireId);
		return QUESTIONNAIRE_PAGE_PATH + "questionnaireDetailsCru";
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
				boolean isSuccess = questionnaireDetailsService.deleteById(id);
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
