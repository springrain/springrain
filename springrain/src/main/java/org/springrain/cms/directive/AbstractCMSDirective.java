package org.springrain.cms.directive;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.template.TemplateDirectiveModel;

/**
 * 标签基类
 */
public abstract class AbstractCMSDirective implements
		TemplateDirectiveModel {
	
	public String getSiteId(){
		return getRequest().getAttribute("siteId").toString();
	}
	
	public String getBusinessId(){
		return getRequest().getAttribute("businessId").toString();
	}
	
	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	
}
