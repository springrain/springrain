package org.springrain.system.cms.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springrain.frame.util.SpringUtils;
import org.springrain.system.cms.entity.CmsLink;
import org.springrain.system.cms.service.ICmsLinkService;
import org.springrain.system.cms.util.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("cmsLink")
public class CmsLinkDirective extends AbstractCMSDirective {

	@Resource
	private ICmsLinkService cmsLinkService;
	private static final String TPL_NAME = "cms_link";

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {

		String businessId = getBusinessId(params);
		String siteId = getSiteId(params);
		Integer modelType = DirectiveUtils.getInt("modelType", params);
		if (modelType == null) {
			body.render(env.getOut());
			return;
		}
		String cacheKey = TPL_NAME + "_cache_key_" + siteId + "_" + businessId;
		CmsLink cmsLink = (CmsLink) getDirectiveData(cacheKey);
		if (cmsLink == null) {
			try {

				if (StringUtils.isBlank(businessId)) {
					businessId = getBusinessId(params);
				}
				// 此处默认查前台的LINK
				cmsLink = cmsLinkService.findLinkBySiteBusinessIdModelType(siteId, businessId, modelType);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				cmsLink = new CmsLink();
			}
			setDirectiveData(cacheKey, cmsLink);
		}

		env.setVariable("cmsLink", DirectiveUtils.wrap(cmsLink));
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
