package org.springrain.system.cms.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.system.cms.entity.CmsComment;
import org.springrain.system.cms.service.ICmsCommentService;
import org.springrain.system.cms.util.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("cmsCommentDirective")
public class CmsCommentDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "cms_comment";
	@Resource
	private ICmsCommentService cmsCommentService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String id = DirectiveUtils.getString("id", params);
		CmsComment comment;

		try {
			comment = cmsCommentService.findCmsCommentById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			comment = new CmsComment();
		}
		env.setVariable("cmsComment", DirectiveUtils.wrap(comment));

		if (body != null) {
			body.render(env.getOut());
		}
	}

	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}
}
