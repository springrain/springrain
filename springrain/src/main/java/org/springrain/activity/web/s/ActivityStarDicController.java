package org.springrain.activity.web.s;

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
import org.springrain.activity.entity.ActivityStarDic;
import org.springrain.activity.service.IActivityStarDicService;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;

/**
 * 星级策略
 * @author dmin93
 * @date 2017年3月22日
 */
@Controller
@RequestMapping("/s/{siteId}/{businessId}/stardic")
public class ActivityStarDicController extends SiteBaseController{

	@Resource
	private IActivityStarDicService activityStarDicService;
	
	/**
	 * 跳转到星级策略列表页面
	 * @param request
	 * @param model
	 * @param siteId
	 * @param businessId
	 * @param activityMain
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,HttpServletResponse response,
			@PathVariable String siteId, @PathVariable String businessId,
			ActivityStarDic activityStarDic) throws Exception {
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnDatas.setPage(page);
		returnDatas.setQueryBean(activityStarDic);
		returnDatas.setData(listjson(model, activityStarDic, request, response, siteId, businessId).getData());
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		return jump(siteId, businessId, Enumerations.CmsLinkModeType.站长后台列表.getType(), request, model);
	}
	
	/**
	 * 获取列表数据
	 */
	@RequestMapping("/list/json")
	@ResponseBody 
	public ReturnDatas listjson(Model model,ActivityStarDic activityStarDic,HttpServletRequest request,
			HttpServletResponse response,@PathVariable String siteId,@PathVariable String businessId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			Page page = newPage(request);
			Finder finder = Finder.getSelectFinder(ActivityStarDic.class);
			finder.append(" where siteId = :siteId ").setParam("siteId", siteId);
			activityStarDicService.getFinderWhereByQueryBean(finder, activityStarDic);
			finder.append(" order by minVal desc ");
			List<ActivityStarDic> resList = activityStarDicService.queryForList(finder, ActivityStarDic.class, page);
			returnObject.setData(resList);
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
			@PathVariable String siteId,@PathVariable String businessId,ActivityStarDic activityStarDic) throws Exception{
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		ActivityStarDic am = activityStarDicService.findById(id, ActivityStarDic.class);
		ReturnDatas rd = new ReturnDatas();
		rd.setData(am);
		model.addAttribute("returnDatas", rd);
		return jump(siteId,businessId,Enumerations.CmsLinkModeType.站长后台更新.getType(),request,model);
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 */
	@RequestMapping("/update")
	@ResponseBody 
	public ReturnDatas saveorupdate(Model model,ActivityStarDic activityStarDic,HttpServletRequest request,
			HttpServletResponse response,@PathVariable String siteId,@PathVariable String businessId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String id = activityStarDic.getId();
			if(StringUtils.isBlank(id)){
				activityStarDic.setId(null);
				activityStarDic.setSiteId(siteId);
				activityStarDic.setBuinessId(businessId);
				activityStarDicService.save(activityStarDic);
			}else{
				activityStarDicService.update(activityStarDic,true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	
}
