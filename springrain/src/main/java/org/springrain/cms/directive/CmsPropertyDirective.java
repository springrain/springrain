package org.springrain.cms.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsProperty;
import org.springrain.cms.service.ICmsPropertyService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("cmsPropertyDirective")
public class CmsPropertyDirective extends AbstractCMSDirective {

	@Resource
	private ICmsPropertyService cmsPropertyService;

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<CmsProperty> list = null;
		try {
			list = cmsPropertyService.findByBusinessId(getBusinessId(params),
					null);
		} catch (Exception e) {
		}
		env.setVariable("content", DirectiveUtils.wrap(list));
		if (body != null) {
			body.render(env.getOut());
		}
	}

}
