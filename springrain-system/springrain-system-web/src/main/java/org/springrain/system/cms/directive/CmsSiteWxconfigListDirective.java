package org.springrain.system.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springrain.frame.util.SpringUtils;
import org.springrain.system.base.SessionUser;
import org.springrain.system.cms.util.DirectiveUtils;
import org.springrain.system.weixin.entity.WxMpConfig;
import org.springrain.system.weixin.service.IWxMenuService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("siteWxconfigListDirective")
public class CmsSiteWxconfigListDirective extends AbstractCMSDirective {
	private static final String TPL_NAME = "cms_site_wxconfig_list";
	@Resource
	private IWxMenuService wxMenuService;

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {

		List<WxMpConfig> configList;
		try {
			configList = wxMenuService.findWxconfigByUserId(SessionUser.getUserId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			configList = new ArrayList<>();
		}

		env.setVariable("configList", DirectiveUtils.wrap(configList));
		if (body != null) {
			body.render(env.getOut());
		}
	}

	@Resource
	private SpringUtils springUtils;
	@PostConstruct
	public void registerFreeMarkerVariable() {
		// setFreeMarkerSharedVariable(TPL_NAME, this);
		FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) springUtils.getBean("freeMarkerConfigurer");
		freeMarkerConfigurer.getConfiguration().setSharedVariable(TPL_NAME, this);
	}
}
