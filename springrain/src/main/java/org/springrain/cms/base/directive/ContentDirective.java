package org.springrain.cms.base.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.base.directive.util.DirectiveUtils;
import org.springrain.cms.base.entity.CmsContent;
import org.springrain.cms.base.service.ICmsContentService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("contentDirective")
public class ContentDirective  extends AbstractCMSDirective  {
	
	@Resource
	private ICmsContentService cmsContentService;
	
	public static final String TPL_NAME = "cms_content";
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsContent content;
		try {
			content = cmsContentService.findById(getBusinessId(), CmsContent.class);
		} catch (Exception e) {
			content = null;
		}
		env.setVariable("content", DirectiveUtils.wrap(content));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}

}
