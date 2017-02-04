package org.springrain.s.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springrain.frame.controller.BaseController;

@Controller
@RequestMapping(value="/s")
public class LoginController extends BaseController {
	
	@RequestMapping(value = "/{siteId}")
	public String index(@PathVariable String siteId){
		return super.redirect+"/s/"+siteId+"/index";
	}
	
	@RequestMapping(value="/{siteId}/index")
	public String index(Model model,@PathVariable String siteId){
		return "/s/index";
	}
	
	@RequestMapping(value="/{siteId}/login",method=RequestMethod.GET)
	public String login(Model model,HttpServletRequest request,@PathVariable String siteId){
		//判断用户是否登录
		if(SecurityUtils.getSubject().isAuthenticated()){
			return redirect+"/s/"+siteId+"/index";
		}
		String url=request.getParameter("gotourl");
		if(StringUtils.isNotBlank(url)){
			model.addAttribute("gotourl", url);
		}
		
		return "/s/login";
	}
	
	
}
