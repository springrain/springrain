package org.springrain.frame.shiro;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;
import org.springrain.demo.entity.Fwlog;
import org.springrain.demo.service.IMenuService;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
@Component("frameperms")
public class FramePermissionsAuthorizationFilter extends
		PermissionsAuthorizationFilter {
	public Logger logger=Logger.getLogger(getClass());
	@Resource
	private IMenuService menuService;
	@Resource
	private CacheManager shiroCacheManager;
	
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		Subject user = SecurityUtils.getSubject();
		 ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
		Session session = user.getSession(false);
		Cache<Object, Object> cache = shiroCacheManager.getCache(GlobalStatic.authenticationCacheName);
		String cachedSessionId = cache.get(GlobalStatic.authenticationCacheName+"-"+shiroUser.getAccount()).toString();
		String sessionId=(String) session.getId();
		if(!sessionId.equals(cachedSessionId)){
			user.logout();
			
		}
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
		String requestURL = req.getRequestURL().toString();
		String contextPath = req.getContextPath();
		if(uri.endsWith("/pre")){// 去掉pre
			uri=uri.substring(0,uri.length()-4);
		}
		int i=uri.indexOf(contextPath);
		if(i>-1){
			uri=uri.substring(i+contextPath.length());
		}
		if(StringUtils.isBlank(uri)){
			uri="/";
		}
		 boolean permitted = subject.isPermitted(uri);
		 String isqx="否";
		 if(permitted){
			 isqx="是";
		 }
		 String ip = IPUtils.getClientAddress(req);
		
		 Fwlog fwlog=new Fwlog();
		 fwlog.setFwUrl(requestURL);
		 fwlog.setIsqx(isqx);
		 fwlog.setIp(ip);
	
		fwlog.setUserCode(shiroUser.getAccount());
		fwlog.setUserName(shiroUser.getName());
		Date startDate=new Date();
		fwlog.setStartDate(startDate);
		fwlog.setStrDate(DateUtils.convertDate2String("yyyy-MM-dd HH:mm:ss.SSSS", startDate));
		HttpSession httpSession = req.getSession(false);
		if(httpSession!=null){
			fwlog.setSessionId(httpSession.getId());
		}
		try {
			String menuName = menuService.getNameByPageurl(uri);
			fwlog.setMenuName(menuName);
			menuService.save(fwlog);
		} catch (Exception e) {
			logger.error(e);
		}
		return permitted;

	}
}
