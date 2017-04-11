package org.springrain.activity.web.s;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.activity.entity.ActivityMain;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.util.SiteUtils;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.InputSafeUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;

/**
 * 活动总结相关
 * @author dmin93
 * @date 2017年3月16日
 */
@Controller
@RequestMapping("/s/{siteId}/{businessId}/activitySummary")
public class ActivitySummaryController extends SiteBaseController{

	@Resource
	private ICmsContentService cmsContentService;
	
	/**
	 * 跳转到活动总结列表页面
	 * @param request
	 * @param model
	 * @param siteId
	 * @param businessId
	 * @param cmsContent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String siteId, @PathVariable String businessId,
			CmsContent cmsContent) throws Exception {
		ReturnDatas returnDatas =  listjson(request, model, siteId, businessId, cmsContent);
		returnDatas.setQueryBean(cmsContent);
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		return jump(siteId, businessId,Enumerations.CmsLinkModeType.站长后台列表.getType(), request, model);
	}
	
	/**
	 * 查询义工风采
	 * @param req
	 * @param model
	 * @param siteId
	 * @param businessId
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnDatas listjson(HttpServletRequest req,Model model,
			@PathVariable String siteId, @PathVariable String businessId,CmsContent cmsContent){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			Page page = newPage(req);
			List<CmsContent> cmsList = 
					cmsContentService.findCmsContentList(cmsContent,siteId,businessId,page);
			rd.setPage(page);
			rd.setData(cmsList);
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.ERROR, "系统异常!");
			logger.error(e.getMessage(),e);
		}
		return rd;
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody 
	public ReturnDatas saveorupdate(Model model,CmsContent cmsContent,HttpServletRequest request,HttpServletResponse response,@PathVariable String siteId,@PathVariable String businessId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {	
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			
			request.getParameter("cover");
			
			cmsContent.setSiteId(siteId);
			cmsContent.setChannelId(businessId);
			cmsContent.setContent(InputSafeUtils.filterRichTextContent(cmsContent.getContent(), SiteUtils.getBaseURL(request)));
		
			java.lang.String id = cmsContent.getId();
			if(StringUtils.isBlank(id)){
				cmsContent.setId(null);
				cmsContentService.saveContent(cmsContent);
			}else{
				cmsContentService.updateCmsContent(cmsContent);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	
	/**
	 * 跳转到活动新增、修改页面
	 * @param model
	 * @param request
	 * @param response
	 * @param siteId
	 * @param businessId
	 * @param activityMain
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response,
			@PathVariable String siteId,@PathVariable String businessId,ActivityMain activityMain) throws Exception{
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		CmsContent cm = cmsContentService.findCmsContentById(siteId, id);
		ReturnDatas rd = new ReturnDatas();
		rd.setData(cm);
		model.addAttribute("returnDatas", rd);
		return jump(siteId,businessId,Enumerations.CmsLinkModeType.站长后台更新.getType(),request,model);
	}
	
	/**
	 * 删除活动内容
	 * @param request
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody 
	public  ReturnDatas deleteById(HttpServletRequest request,@PathVariable String siteId){
		try {
			java.lang.String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
				cmsContentService.deleteById(id, siteId);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	 * 删除多个活动内容
	 * @param request
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/delete/more")
	@ResponseBody 
	public  ReturnDatas deleteMore(HttpServletRequest request, Model model,@PathVariable String siteId) {
		try {
			String records = request.getParameter("records");
			if(StringUtils.isBlank(records)){
				 return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
			}
			String[] rs = records.split(",");
			if (rs == null || rs.length < 1) {
				return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_FAIL);
			}
		
			List<String> ids = Arrays.asList(rs);
			cmsContentService.deleteByIds(ids, siteId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
	}
	
}
