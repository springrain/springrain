package org.springrain.weixin.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.entity.CmsSite;
import org.springrain.frame.controller.BaseController;


@Controller
@RequestMapping(value="/f/0/{siteId}")
public class WeiXinSiteController extends BaseController {
	
	    @ModelAttribute()
		public void init(@PathVariable String siteId) {
		}
	   

	@RequestMapping("/index")
	public  String index(@PathVariable String siteId,HttpServletRequest request, Model model,CmsSite cmsSite ) 
			throws Exception {
		
		model.addAttribute("siteId",siteId);
		//request.setAttribute("siteId",siteId);

		return "/testDirective";
	}
}
