package org.springrain.cms.base.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.base.directive.util.DirectiveUtils;
import org.springrain.cms.base.entity.CmsSite;
import org.springrain.cms.base.service.ICmsSiteService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("siteDirective")
public class CmsSiteDirective extends AbstractCMSDirective {
	
	@Resource
	private ICmsSiteService cmsSiteService;
	
	
	
	public static final String TPL_NAME = "cms_site";
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site;
		try {
			site = cmsSiteService.findCmsSiteById(getSiteId());
		} catch (Exception e) {
			site = new CmsSite();
		}
		env.setVariable("site", DirectiveUtils.wrap(site));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}

}
