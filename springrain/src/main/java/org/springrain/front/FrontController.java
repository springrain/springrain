package org.springrain.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.base.entity.CmsChannel;
import org.springrain.cms.base.entity.CmsContent;
import org.springrain.cms.base.entity.CmsSite;
import org.springrain.cms.base.service.ICmsChannelService;
import org.springrain.cms.base.service.ICmsContentService;
import org.springrain.cms.base.service.ICmsSiteService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;

@Controller
@RequestMapping("/f/{siteType}/{siteId}")
public class FrontController extends BaseController {
	
	
	@Resource
	private ICmsSiteService cmsSiteService;
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private ICmsContentService cmsContentService;

	/**
	 * 映射首页页面
	 * */
	@RequestMapping("/index")
	public String index(@PathVariable String siteType,@PathVariable String siteId,HttpServletRequest request, Model model){
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			CmsSite site = cmsSiteService.findCmsSiteById(siteId);
			List<CmsChannel> channelList = cmsChannelService.findTreeByPid(null, siteId);
			List<CmsContent> contentList = cmsContentService.findListBySiteId(siteId,page);
			dataMap.put("site", site);
			dataMap.put("channelList", channelList);
			dataMap.put("contentList", contentList);
			returnDatas.setData(dataMap);
		} catch (Exception e) {
			returnDatas.setMessage("系统异常:"+e.getLocalizedMessage());
			returnDatas.setStatus(ReturnDatas.ERROR);
			returnDatas.setStatusCode("500");
		}
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		return "/front/index";
	}
	
	/**
	 * 映射栏目页面
	 * */
	@RequestMapping("/channel/{channelId}")
	public String channel(@PathVariable String siteType,@PathVariable String siteId,@PathVariable String channelId,HttpServletRequest request, Model model){
		return "/front/channel";
	}
	
	/**
	 * 映射内容页面
	 * */
	@RequestMapping("/content/{contentId}")
	public String content(@PathVariable String contentId,HttpServletRequest request, Model model){
		return "/front/content";
	}
}
