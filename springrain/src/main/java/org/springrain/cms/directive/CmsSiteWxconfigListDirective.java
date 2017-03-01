package org.springrain.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsSiteWxconfig;
import org.springrain.cms.service.ICmsSiteWxconfigService;
import org.springrain.cms.utils.DirectiveUtils;
import org.springrain.frame.common.SessionUser;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("siteWxconfigListDirective")
public class CmsSiteWxconfigListDirective extends AbstractCMSDirective {
	private static final String TPL_NAME = "cms_site_wxconfig_list";
	@Resource
	private ICmsSiteWxconfigService cmsSiteWxconfigService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<CmsSiteWxconfig> configList;
		try {
			configList = cmsSiteWxconfigService.findWxconfigByUserId(SessionUser.getUserId());
		} catch (Exception e) {
			configList = new ArrayList<>();
		}
		
		
		env.setVariable("configList", DirectiveUtils.wrap(configList));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}
}
