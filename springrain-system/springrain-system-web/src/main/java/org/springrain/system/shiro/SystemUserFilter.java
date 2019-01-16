package org.springrain.system.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.GlobalStatic;

/**
 * 系统后台管理用户的过滤器
 * 
 * @author caomei
 */

@Component("systemuser")
public class SystemUserFilter extends BaseUserFilter {
	public SystemUserFilter() {
		// 跳转到登录界面
		super.setLoginUrl("/system/login");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		boolean access = super.isAccessAllowed(request, response, mappedValue);

		HttpServletRequest req = (HttpServletRequest) request;
		if (!access) {// 登录失败了,需要设置一下需要跳转的登录URL
			return access;
		}

		Object obj = req.getSession().getAttribute(GlobalStatic.tokenKey);
		if (obj == null || (!obj.toString().startsWith("system_"))) {// tokenKey必须是system_开头
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

	@Bean("disableSystemUserFilter")
	public FilterRegistrationBean<SystemUserFilter> disableSystemUserFilter(SystemUserFilter filter) {
		FilterRegistrationBean<SystemUserFilter> registration = new FilterRegistrationBean<SystemUserFilter>(filter);
		registration.setEnabled(false);
		return registration;
	}

}
