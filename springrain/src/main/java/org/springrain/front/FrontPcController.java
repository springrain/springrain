package org.springrain.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.frame.controller.BaseController;

@Controller
@RequestMapping("/f/2/{siteId}")
public class FrontPcController extends BaseController {
	
	@Resource
	private ICmsLinkService cmsLinkService;
	
	
	/**
	 * 映射首页页面
	 * @throws Exception 
	 * */
	@RequestMapping("/index")
	public String index(@PathVariable String siteId,HttpServletRequest request,Model model) throws Exception{
		String url = cmsLinkService.findFtlFileByBussinessId(siteId);
		
		addModelParameter(request, model);
		
		model.addAttribute("siteId", siteId);
		model.addAttribute("siteType", "2");
		
		return url;
	}
	
	/**
	 * 映射栏目页面
	 * @throws Exception 
	 * */
	@RequestMapping("/{businessId}")
	public String channel(@PathVariable String siteId,@PathVariable String businessId,HttpServletRequest request,Model model) throws Exception{
		String url = cmsLinkService.findFtlFileByBussinessId(businessId);
		
	    addModelParameter(request, model);
	    
	    model.addAttribute("siteId", siteId);
		model.addAttribute("businessId", businessId);
		model.addAttribute("siteType", "2");
		
		return url;
	}
	
	
	
	
	
	
	
}
