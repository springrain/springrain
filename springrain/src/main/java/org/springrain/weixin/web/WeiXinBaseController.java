package org.springrain.weixin.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.cms.base.entity.CmsSite;
import org.springrain.cms.utils.SiteUtils;
import org.springrain.frame.controller.BaseController;


@Controller
@RequestMapping(value="/f/0/{siteId}")
public class WeiXinBaseController extends BaseController {
	
	    @ModelAttribute()
		public void init(@PathVariable String siteId) {
	    	SiteUtils.setSiteId(siteId);
		}
	   

	@RequestMapping("/index")
	public  String index(@PathVariable String siteId,HttpServletRequest request, Model model,CmsSite cmsSite ) 
			throws Exception {
		
		System.out.println(SiteUtils.getSiteId());
		
		System.out.println("::"+siteId);
		
		

		return "index";
	}
}
