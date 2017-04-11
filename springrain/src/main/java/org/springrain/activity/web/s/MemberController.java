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
import org.springrain.activity.entity.Member;
import org.springrain.activity.service.IMemberService;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;

/**
 * 会员管理
 * @author dmin93
 * @date 2017年3月21日
 */
@Controller
@RequestMapping("/s/{siteId}/{businessId}/member")
public class MemberController extends SiteBaseController{

	@Resource
	private IMemberService memberService;
	
	/**
	 * 跳转到会员列表页面
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
			Member member) throws Exception {
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnDatas.setPage(page);
		returnDatas.setQueryBean(member);
		returnDatas.setData(listjson(request, model, member,siteId).getData());
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		return jump(siteId, businessId, Enumerations.CmsLinkModeType.站长后台列表.getType(), request, model);
	}
	
	/**
	 * 获取会员信息
	 * @param request
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/ajax/getMemeberInfo")
	@ResponseBody 
	public  ReturnDatas isAliveMember(HttpServletRequest request, Model model,
			@PathVariable String siteId) {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String openId = request.getParameter("openId");
			if(StringUtils.isEmpty(openId)){
				rd = ReturnDatas.getErrorReturnDatas();
				rd.setMessage("openId是必需的。");
				return rd;
			}
			Member member = memberService.findMemberByOpenId(openId, siteId);
			rd.setData(member);
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.ERROR, "未知错误！");
			logger.error(e.getMessage(),e);
		}
		return rd;
	}
	
	/**
	 * 完善会员信息（member中不存在时）
	 * @param request
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/ajax/saveorupdate")
	@ResponseBody 
	public  ReturnDatas saveorupdate(HttpServletRequest request, Model model,
			@PathVariable String siteId,Member member) {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			if(member == null){
				rd = ReturnDatas.getErrorReturnDatas();
				rd.setMessage("提交的信息不能为空！");
				return rd;
			}
			if(request.getSession().getAttribute("openId") != null){
				member.setOpenId(String.valueOf(request.getSession().getAttribute("openId")));
			}
			if(StringUtils.isEmpty(member.getOpenId())){
				rd = ReturnDatas.getErrorReturnDatas();
				rd.setMessage("openId不能为空！");
				return rd;
			}
			member.setSiteId(siteId);
			memberService.saveMemberAndUser(member);
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.ERROR,"未知错误！");
			logger.error(e.getMessage(),e);
		}
		return rd;
	}
	
	/**
	 * 查询会员
	 * @param request
	 * @param model
	 * @param member
	 * @return
	 */
	@RequestMapping("/list/json")
	@ResponseBody 
	public  ReturnDatas listjson(HttpServletRequest request, Model model,Member member,
			@PathVariable String siteId){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			Page page = newPage(request);
			List<Member> listMemeber = memberService.findMemberList(siteId, page, member);
			rd.setPage(page);
			rd.setData(listMemeber);
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.ERROR,"未知错误！");
			logger.error(e.getMessage(),e);
		}
		return rd;
	}
	
	/**
	 * 修改会员积分
	 * @param req
	 * @param res
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/updatePoint")
	@ResponseBody 
	public  ReturnDatas updatePoint(HttpServletRequest req,HttpServletResponse res,Model model,
			@PathVariable String siteId ){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			String id = req.getParameter("id");
			String point = req.getParameter("point");
			if(StringUtils.isEmpty(id) || StringUtils.isEmpty(point)){
				rd = new ReturnDatas(ReturnDatas.ERROR,"积分不能为空！");
			}else{
				memberService.saveMemberPoint(id,Integer.valueOf(point),siteId);
				rd.setMessage("保存成功！");
			}
		} catch (Exception e) {
			rd = new ReturnDatas(ReturnDatas.ERROR, "系统异常！");
			logger.error(e.getMessage(),e);
		}
		return rd;
	}
	
	/**
	 * 刷新积分排行
	 * @param req
	 * @param res
	 * @param model
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/updateRanking")
	@ResponseBody 
	public  ReturnDatas updateRanking(HttpServletRequest req,HttpServletResponse res,Model model,
			@PathVariable String siteId){
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		try {
			// 刷新积分排行,清除缓存,现在因为是实时刷新积分排行，所以该方法无用处
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rd = new ReturnDatas(ReturnDatas.ERROR, "系统异常！");
		}
		return rd;
	}
	
}
