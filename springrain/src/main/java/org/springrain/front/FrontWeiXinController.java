package org.springrain.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.entity.CmsSite;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.frame.controller.BaseController;


@Controller
@RequestMapping(value="/f/0/{siteId}")
public class FrontWeiXinController extends BaseController {
	@Resource
	private ICmsLinkService cmsLinkService;
	
	
	@RequestMapping("/index")
	public  String index(@PathVariable String siteId,HttpServletRequest request, Model model,CmsSite cmsSite ) 
			throws Exception {String url = cmsLinkService.findFtlFileByBussinessId(siteId);
			
			addModelParameter(request, model);
			
			model.addAttribute("siteId", siteId);
			model.addAttribute("businessId", siteId);
			model.addAttribute("siteType", 0);
			
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
		model.addAttribute("siteType", 0);
		
		return url;
	}
}
