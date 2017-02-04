package org.springrain.s.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.shiro.FrameAuthenticationToken;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.entity.User;

@Controller(value="FLoginController")
@RequestMapping(value="/s/{siteId}")
public class LoginController extends BaseController {
	
	/**
	 * 首页
	 * @param siteId 站点id
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(@PathVariable String siteId){
		return super.redirect+"/s/"+siteId+"/index";
	}
	
	/**
	 * 首页
	 * @param model
	 * @param siteId 站点id
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model,@PathVariable String siteId){
		return "/s/index";
	}
	
	/**
	 * 登陆页面跳转
	 * @param model
	 * @param request
	 * @param siteId
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
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
	
	/**
	 * 登陆
	 * @param currUser
	 * @param session
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login(User currUser,HttpSession session,Model model,HttpServletRequest request){
		Subject user = SecurityUtils.getSubject();
		//系统产生的验证码
		String code = (String) session.getAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
		session.removeAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
		  
		if(StringUtils.isNotBlank(code)){
			code=code.toLowerCase().toString();
		}
		//用户产生的验证码
		String submitCode = WebUtils.getCleanParam(request, GlobalStatic.DEFAULT_CAPTCHA_PARAM);
		if(StringUtils.isNotBlank(submitCode)){
			submitCode=submitCode.toLowerCase().toString();
		}
		  
		String gotourl=request.getParameter("gotourl");
		
		  
		  //如果验证码不匹配,跳转到登录
		if (StringUtils.isBlank(submitCode) ||StringUtils.isBlank(code)||!code.equals(submitCode)) {
			model.addAttribute("message", "验证码错误!");
			return "/s/login";
        }
		//通过账号和密码获取 UsernamePasswordToken token
		FrameAuthenticationToken token = new FrameAuthenticationToken(currUser.getAccount(),currUser.getPassword());
		
		String rememberme=request.getParameter("rememberme");
		if(StringUtils.isNotBlank(rememberme)){
			token.setRememberMe(true);
		}else{
			token.setRememberMe(false);
		}
		
		try {
			//会调用 shiroDbRealm 的认证方法 org.springrain.frame.shiro.ShiroDbRealm.doGetAuthenticationInfo(AuthenticationToken)
			user.login(token);
		} catch (UnknownAccountException e) {
			model.addAttribute("message", "账号不存在!");
			if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
		    }
			return "/s/login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("message", "密码错误!");
			if(StringUtils.isNotBlank(gotourl)){
		  	     model.addAttribute("gotourl", gotourl);
	        }
			return "/s/login";
		} catch (LockedAccountException e) {
			model.addAttribute("message", e.getMessage());
			if(StringUtils.isNotBlank(gotourl)){
		  	     model.addAttribute("gotourl", gotourl);
	        }
			return "/s/login";
		} catch (Exception e) {
			model.addAttribute("message", "未知错误,请联系管理员.");
			if(StringUtils.isNotBlank(gotourl)){
		  	     model.addAttribute("gotourl", gotourl);
	        }
			return "/s/login";
		}
		
		if(StringUtils.isBlank(gotourl)){
			gotourl="/index";
		}
		
		//设置tokenkey
		String springraintoken="f_"+SecUtils.getUUID();
		session.setAttribute(GlobalStatic.tokenKey, springraintoken);
		model.addAttribute(GlobalStatic.tokenKey,springraintoken);
		return redirect+gotourl;
	}
	
	/**
	 * 退出,防止csrf,必须使用POST方法
	 * @param request
	 */
	@RequestMapping(value="/logout",method=RequestMethod.POST)
    public String logout(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
        if (subject != null) {           
            subject.logout();
        }
        return super.redirect+"/s/login";
	}
}
