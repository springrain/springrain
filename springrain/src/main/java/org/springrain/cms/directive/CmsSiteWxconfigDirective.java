package org.springrain.cms.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsSiteWxconfig;
import org.springrain.cms.service.ICmsSiteWxconfigService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("siteWxconfigDirective")
public class CmsSiteWxconfigDirective extends AbstractCMSDirective  {
	
	private static final String TPL_NAME = "cms_site_wxconfig";
	@Resource
	private ICmsSiteWxconfigService cmsSiteWxconfigService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSiteWxconfig config;
		String id = DirectiveUtils.getString("id", params);
		
		try {
			config = cmsSiteWxconfigService.findCmsSiteWxconfigById(id);
		} catch (Exception e) {
			config = new CmsSiteWxconfig();
		}
				
		env.setVariable("config", DirectiveUtils.wrap(config));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}
	
}
