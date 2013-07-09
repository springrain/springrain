package org.iu9.frame.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;
@Component("frameperms")
public class FramePermissionsAuthorizationFilter extends
		PermissionsAuthorizationFilter {
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
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
		return subject.isPermitted(uri);

	}
}
