package org.springrain.cms.directive;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springrain.frame.util.Page;

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
	
	public Page newPage(){
		HttpServletRequest request = getRequest();
		String str_pageIndex = request.getParameter("pageIndex");
		int pageIndex = NumberUtils.toInt(str_pageIndex, 1);
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		
		if (StringUtils.isBlank(order)) {
			order = "id";
		}
		if (StringUtils.isBlank(sort)) {
			sort = "desc";
		}

		Page page = new Page(pageIndex);
		page.setOrder(order);
		page.setSort(sort);
		return page;
	}
}
