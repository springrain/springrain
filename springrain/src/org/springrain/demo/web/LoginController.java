package org.springrain.demo.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springrain.demo.entity.User;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.CaptchaUtils;
import org.springrain.frame.util.GlobalStatic;

@Controller
public class LoginController extends BaseController  {
	
	/**
	 * 首页的映射
	 * @param model
	 * @return
	 * @throws Exception
	 */
		@RequestMapping(value = "/index")
		public String index(Model model) throws Exception {
				return "/index";
			
		}
		
		@RequestMapping(value = "/login",method=RequestMethod.GET)
		public String login(Model model) throws Exception {
			if(SecurityUtils.getSubject().isAuthenticated()){
				return "redirect:/index";
			}
			//默认赋值message,避免freemarker尝试从session取值,造成异常
			model.addAttribute("message", "");
			
			return "/login";
		}
		@RequestMapping(value = "/login",method=RequestMethod.POST)
		public String loginPost(User currUser,HttpSession session,Model model,HttpServletRequest request) throws Exception {
			Subject user = SecurityUtils.getSubject();
			  String code = (String) session.getAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
			  if(StringUtils.isNotBlank(code)){
				  code=code.toLowerCase().toString();
			  }
			String submitCode = WebUtils.getCleanParam(request, GlobalStatic.DEFAULT_CAPTCHA_PARAM);
			  if(StringUtils.isNotBlank(submitCode)){
				  submitCode=submitCode.toLowerCase().toString();
			  }
			if (StringUtils.isBlank(submitCode) ||StringUtils.isBlank(code)||!code.equals(submitCode)) {
				model.addAttribute("message", "验证码错误!");
				return "/login";
	        }
			
			UsernamePasswordToken token = new UsernamePasswordToken(currUser.getAccount(),currUser.getPassword());
			
			String rememberme=request.getParameter("rememberme");
			if(StringUtils.isNotBlank(rememberme)){
			token.setRememberMe(true);
			}else{
				token.setRememberMe(false);
			}
			
			try {
				//会调用 shiroDbRealm 的认证方法 org.springrain.frame.shiro.ShiroDbRealm.doGetAuthenticationInfo(AuthenticationToken)
				user.login(token);
			} catch (UnknownAccountException uae) {
				model.addAttribute("message", "账号不存在!");
				return "/login";
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("message", "密码错误!");
				return "/login";
			} catch (LockedAccountException lae) {
				model.addAttribute("message", "账号被锁定!");
				return "/login";
			} catch (Exception e) {
				model.addAttribute("message", "未知错误,请联系管理员.");
				return "/login";
			}
		
			//String sessionId = session.getId();
			
			//Cache<Object, Object> cache = shiroCacheManager.getCache(GlobalStatic.authenticationCacheName);
			//cache.put(GlobalStatic.authenticationCacheName+"-"+currUser.getAccount(), sessionId);
			
			/*
			Cache<String, Object> cache = shiroCacheManager.getCache(GlobalStatic.shiroActiveSessionCacheName);
			Serializable oldSessionId = (Serializable) cache.get(currUser.getAccount());
			if(oldSessionId!=null){
				Subject subject=new Subject.Builder().sessionId(oldSessionId).buildSubject();
				subject.logout();
			}
			cache.put(currUser.getAccount(), session.getId());
			*/
			
			return "redirect:/index";
		}
		
		/**
		 * 没有权限
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/unauth")
		public String unauth(Model model) throws Exception {
			if(SecurityUtils.getSubject().isAuthenticated()==false){
				return "redirect:/login";
			}
				return "/unauth";
			
		}
		
		/**
		 * 退出
		 * @param request
		 */
		@RequestMapping(value="/logout")
	    public void logout(HttpServletRequest request){
	        Subject subject = SecurityUtils.getSubject();
	        if (subject != null) {           
	            subject.logout();
	        }
	        //request.getSession().invalidate();
	    }
		
		@RequestMapping(value = "/mobilelogin")
		public String mobilelogin(Model model) throws Exception {
			return "/mobilelogin";
		}
		
		
		/**
		 * 生成验证码
		 * 
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping("/getCaptcha")
		public void getCaptcha(HttpSession session,HttpServletResponse response) throws IOException {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			CaptchaUtils tool = new CaptchaUtils();
			StringBuffer code = new StringBuffer();
			BufferedImage image = tool.genRandomCodeImage(code);
			session.removeAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
			session.setAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM, code.toString());

			// 将内存中的图片通过流动形式输出到客户端
			ImageIO.write(image, "JPEG", response.getOutputStream());
			return;
		}
		
}
