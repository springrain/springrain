package org.springrain.activity.web.s;

import java.io.File;
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
import org.springrain.activity.entity.ActivityPartIn;
import org.springrain.activity.entity.Member;
import org.springrain.activity.service.IActivityMainService;
import org.springrain.activity.service.IActivityPartInService;
import org.springrain.activity.service.IMemberService;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.util.SiteUtils;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.InputSafeUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;


/**
 * 活动相关
 * @author dmin93
 * @date 2017年3月14日
 */
@Controller
@RequestMapping(value = "/s/{siteId}/{businessId}/activity")
public class ActivityController extends SiteBaseController {

	@Resource
	private IActivityMainService activityMainService;
	
	@Resource
	private IMemberService memberService;
	
	@Resource
	private IActivityPartInService activityPartInService;
	
	/**
	 * 跳转到活动列表页面
	 * @param request
	 * @param model
	 * @param siteId
	 * @param businessId
	 * @param activityMain
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String siteId, @PathVariable String businessId,
			ActivityMain activityMain) throws Exception {
		ReturnDatas returnDatas = listjson(request,model,siteId,businessId,activityMain);
		returnDatas.setQueryBean(activityMain);
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		return jump(siteId, businessId, Enumerations.CmsLinkModeType.站长后台列表.getType(), request, model);
	}

	/**
	 * 查询活动列表
	 * @param req
	 * @param model
	 * @param siteId
	 * @param businessId
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody 
	public  ReturnDatas listjson(HttpServletRequest req,Model model,
			@PathVariable String siteId, @PathVariable String businessId,ActivityMain activityMain){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			Page page = newPage(req);
			List<ActivityMain> amList = 
					activityMainService.findActivityMainList(activityMain,siteId,businessId,page);
			rd.setPage(page);
			rd.setData(amList);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rd = new ReturnDatas(ReturnDatas.ERROR, "系统异常!");
		}
		return rd;
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody 
	public ReturnDatas saveorupdate(Model model,ActivityMain activityMain,HttpServletRequest request,HttpServletResponse response,@PathVariable String siteId,@PathVariable String businessId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			
			activityMain.getCmsContent().setSiteId(siteId);
			activityMain.getCmsContent().setChannelId(businessId);
			String content= activityMain.getCmsContent().getContent();
			activityMain.getCmsContent().setContent(InputSafeUtils.filterRichTextContent(content, SiteUtils.getBaseURL(request)));
		
			java.lang.String id = activityMain.getId();
			if(StringUtils.isBlank(id)){
				activityMain.setId(null);
				activityMainService.saveActivityMain(activityMain);
			}else{
				activityMainService.updateActivityMain(activityMain);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject  = new ReturnDatas(ReturnDatas.ERROR, MessageUtils.UPDATE_ERROR);
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
		ActivityMain am = activityMainService.findById(id, ActivityMain.class);
		if(am!=null && StringUtils.isNoneBlank(am.getId())){
			CmsContent cm = activityMainService.findById(id, CmsContent.class);
			am.setCmsContent(cm);
		}
		ReturnDatas rd = new ReturnDatas();
		rd.setData(am);
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
				activityMainService.deleteById(id,siteId);
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
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			activityMainService.deleteByIds(ids,siteId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
	}
	
	/**
	 * 判断是否已完善信息
	 * @param request
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/ajax/hasSignup")
	@ResponseBody
	public ReturnDatas hasSignup(HttpServletRequest request, Model model,@PathVariable String siteId) {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String openId = String.valueOf(request.getSession().getAttribute("openId"));
			Member member = memberService.findMemberByOpenId(openId, siteId);
			if(member == null){
				rd.setMessage("false");
			}else{
				rd.setMessage("true");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rd = new ReturnDatas(ReturnDatas.ERROR,"未知错误！");
		}
		return rd;
	}
	
	/**
	 * 报名参加活动
	 * @param request
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/ajax/signup")
	@ResponseBody 
	public  ReturnDatas signup(HttpServletRequest request, Model model,@PathVariable String siteId,
			Member member) {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String activityMainId = request.getParameter("activityMainId");
			String openId = String.valueOf(request.getSession().getAttribute("openId"));
			ActivityPartIn api = 
				activityPartInService.findActivityPartInByOpenIdAndActivityMainId(openId,activityMainId,siteId);
			if(api == null){
				activityMainService.saveActivityPartIn(openId, activityMainId,siteId);
				rd.setMessage("报名成功！");
			}else{
				rd = new ReturnDatas(ReturnDatas.ERROR, "您已报名该次活动，请不要重复报名！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rd = new ReturnDatas(ReturnDatas.ERROR, "未知错误！");
		}
		return rd;
	}
	
	/**
	 * 判断是否已报名过警校活动
	 * @param request
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/ajax/hasSignupForJunPolAca")
	@ResponseBody
	public ReturnDatas hasSignupForJunPolAca(HttpServletRequest request, Model model,@PathVariable String siteId) {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String name = request.getParameter("name");
			String telPhone = request.getParameter("telPhone");
			String activityId = request.getParameter("activityId");
			boolean hasSignup = activityPartInService.hasSignupForJunPolAca(name,telPhone,activityId);
			if(hasSignup){
				rd.setMessage("true");
			}else{
				rd.setMessage("false");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rd = new ReturnDatas(ReturnDatas.ERROR,"未知错误！");
		}
		return rd;
	}
	
	/**
	 * 报名参加警校活动
	 * @param request
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/ajax/signupJuniorPoliceAcademy")
	@ResponseBody 
	public  ReturnDatas signupJuniorPoliceAcademy(HttpServletRequest request, Model model,@PathVariable String siteId,
			ActivityPartIn api) {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			activityMainService.saveActivityPartInForJunPolAca(api,siteId);
			rd.setMessage("报名成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rd = new ReturnDatas(ReturnDatas.ERROR,"未知错误！");
		}
		return rd;
	}
	
	@RequestMapping("/exportPartIn")
	public void exportPartIn(HttpServletRequest req,HttpServletResponse res, 
			Model model,@PathVariable String siteId) throws Exception{
		// ==构造分页请求
		Page page = newPage(req);
	
		String activityId = req.getParameter("activityId");
		String listurl = "/activity/s/activitymain/activityMainExport";
		Finder finder = Finder.getSelectFinder(ActivityPartIn.class);
		finder.append(" where activityId = :activityId ").setParam("activityId", activityId);
		File file = activityPartInService.findDataExportExcel(finder,listurl, page,ActivityPartIn.class,null);
		
		ActivityMain am = activityMainService.findActivityMainById(siteId, activityId);
		String fileName = am.getCmsContent().getTitle() + GlobalStatic.excelext;
		downFile(res, file, fileName,true);
		return;
	}
	
}
