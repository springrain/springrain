package org.springrain.system.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.InputSafeUtils;
import org.springrain.system.util.CookieUtils;

/**
 * 系统后台管理用户的过滤器
 * 
 * @author caomei
 */

@Component("siteuser")
public class SiteUserFilter extends BaseUserFilter {
	public SiteUserFilter() {
		// 跳转到登录界面
		// super.setLoginUrl("/s/login");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		boolean access = super.isAccessAllowed(request, response, mappedValue);

		HttpServletRequest req = (HttpServletRequest) request;
		if (!access) {// 登录失败了,需要设置一下需要跳转的登录URL
			String siteId = InputSafeUtils.substringByURI(req.getRequestURI(), "/s_");

			if (StringUtils.isBlank(siteId)) {// URL中没有siteId,从cookie中取值
				siteId = CookieUtils.getCookieValue(req, GlobalStatic.springraindefaultSiteId);
			}

			if (StringUtils.isNotBlank(siteId)) {
				request.setAttribute(GlobalStatic.customLoginURLKey, "/s/" + siteId + "/login");
			}

			return access;
		}

		Object obj = req.getSession().getAttribute(GlobalStatic.tokenKey);

		if (obj == null) {
			request.setAttribute(GlobalStatic.errorTokentoURLKey, GlobalStatic.errorTokentoURL);
			return false;
		}

		boolean tokenerror = true;
		String stoken = obj.toString();

		if (stoken.startsWith("s_") || stoken.startsWith("system_")) {// tokenKey必须是s_开头或者system_
			tokenerror = false;
		}

		if (tokenerror) {
			request.setAttribute(GlobalStatic.errorTokentoURLKey, GlobalStatic.errorTokentoURL);
			return false;
		}

		String token = obj.toString();

		String userToken = req.getParameter(GlobalStatic.tokenKey);
		if (token.equals(userToken)) {
			return true;
		}

		request.setAttribute(GlobalStatic.errorTokentoURLKey, GlobalStatic.errorTokentoURL);

		return false;

	}

	/**
	 * springboot会把所有的filter列为平级,造成shiro的子拦截器和shiroFilter同级,造成访问异常,所以shiro的子Filter需要手动disable
	 * 
	 * @param filter
	 * @return
	 */

	@Bean("disableSiteUserFilter")
	public FilterRegistrationBean<SiteUserFilter> disableSiteUserFilter(SiteUserFilter filter) {
		FilterRegistrationBean<SiteUserFilter> registration = new FilterRegistrationBean<SiteUserFilter>(filter);
		registration.setEnabled(false);
		return registration;
	}

}
