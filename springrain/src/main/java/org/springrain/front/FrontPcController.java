package org.springrain.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.base.service.ICmsChannelService;
import org.springrain.cms.base.service.ICmsContentService;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.cms.base.service.ICmsSiteService;
import org.springrain.frame.controller.BaseController;

@Controller
@RequestMapping("/f/2/{siteId}")
public class FrontPcController extends BaseController {
	
	
	@Resource
	private ICmsSiteService cmsSiteService;
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private ICmsContentService cmsContentService;
	@Resource
	private ICmsLinkService cmsLinkService;
	
	@ModelAttribute
	public void init(Model model,@PathVariable String siteId) {
		model.addAttribute("siteId", siteId);
		model.addAttribute("siteType", "2");
		super.init(model);
	}
	
	/**
	 * 映射首页页面
	 * @throws Exception 
	 * */
	@RequestMapping("/index")
	public String index(@PathVariable String siteId,HttpServletRequest request) throws Exception{
		String url = cmsLinkService.findFtlFileByBussinessId(siteId);
		return url;
	}
	
	/**
	 * 映射栏目页面
	 * @throws Exception 
	 * */
	@RequestMapping("/{businessId}")
	public String channel(@PathVariable String businessId,HttpServletRequest request,Model model) throws Exception{
		model.addAttribute("businessId", businessId);
		String url = cmsLinkService.findFtlFileByBussinessId(businessId);
		return url;
	}
	
}
