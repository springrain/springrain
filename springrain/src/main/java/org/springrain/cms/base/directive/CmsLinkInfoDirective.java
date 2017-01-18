package org.springrain.cms.base.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springrain.cms.base.directive.util.DirectiveUtils;
import org.springrain.cms.base.entity.CmsLink;
import org.springrain.cms.base.service.ICmsLinkService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class CmsLinkInfoDirective implements TemplateDirectiveModel {
	
	public static final String TPL_NAME = "cms_link";
	@Resource
	private ICmsLinkService cmsLinkService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String bussinessId = DirectiveUtils.getString("businessId", params);
		String link;
		try {
			link = cmsLinkService.findLinkByBusinessId(bussinessId);
		} catch (Exception e) {
			link = "";
		}
		env.setVariable("channel_list", DirectiveUtils.wrap(link));
	}
	
	
}
