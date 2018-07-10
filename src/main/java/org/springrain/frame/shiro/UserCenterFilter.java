package org.springrain.frame.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.CookieUtils;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.InputSafeUtils;

/**
 * 用户中心
 * 
 * @author caomei
 */

@Component("usercenter")
public class UserCenterFilter extends BaseUserFilter {
	public UserCenterFilter() {
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
				request.setAttribute(GlobalStatic.customLoginURLKey, "/uc/" + siteId + "/login");
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

		if (stoken.startsWith("uc_")) {// tokenKey必须是uc_
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

	@Bean("disableUserCenterFilter")
	public FilterRegistrationBean<UserCenterFilter> disableUserCenterFilter(UserCenterFilter filter) {
		FilterRegistrationBean<UserCenterFilter> registration = new FilterRegistrationBean<UserCenterFilter>(filter);
		registration.setEnabled(false);
		return registration;
	}

}
