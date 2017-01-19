package org.springrain.cms.base.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springrain.cms.base.directive.util.DirectiveUtils;
import org.springrain.cms.base.entity.CmsContent;
import org.springrain.cms.base.service.ICmsContentService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("contentDirective")
public class ContentDirective implements TemplateDirectiveModel {
	
	@Resource
	HttpServletRequest request;
	@Resource
	private ICmsContentService cmsContentService;
	
	public static final String TPL_NAME = "cms_content";
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String contentId = request.getAttribute("businessId").toString();
		CmsContent content;
		try {
			content = cmsContentService.findById(contentId, CmsContent.class);
		} catch (Exception e) {
			content = null;
		}
		env.setVariable("content", DirectiveUtils.wrap(content));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}

}
