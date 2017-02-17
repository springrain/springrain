package org.springrain.cms.directive;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;

/**
 * 标签基类
 */
public abstract class AbstractCMSDirective implements
		TemplateDirectiveModel {
	private   Logger logger = LoggerFactory.getLogger(getClass());
	public String getReuestSiteId(){
		return getRequestAttributeString("siteId");
	}
	
	public String getReuestBusinessId(){
		return getRequestAttributeString("businessId");
	}
	/**
	 * 优先从 标签参数取值
	 * @param params
	 * @return
	 */
	public String getSiteId(Map params){
		return getParameter("siteId", params);
		}
	/**
	 * 优先从 标签参数取值
	 * @param params
	 * @return
	 */
	public String getBusinessId(Map params){
		return getParameter("businessId", params);
	}
	
	
	private String getParameter(String key,Map params){
		try {
			if(params==null){
				return getRequestAttributeString(key);
			}
			String value = DirectiveUtils.getString(key, params);
			
			if(value==null){
				return getRequestAttributeString(key);
			}
			return value;
		} catch (TemplateException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public String getRequestAttributeString(String key){
		 Object attribute = getRequest().getAttribute(key);
		 if(attribute==null){
			 return null;
		 }
		 return attribute.toString();
		 
	}
	
	
	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	
}
