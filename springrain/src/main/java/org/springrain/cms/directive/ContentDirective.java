package org.springrain.cms.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.service.ICmsCommentService;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.service.ICmsPraiseService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("contentDirective")
public class ContentDirective  extends AbstractCMSDirective  {
	
	@Resource
	private ICmsContentService cmsContentService;
	
	@Resource
	private ICmsCommentService cmsCommentService;
	@Resource
	private ICmsPraiseService cmsPraiseService;
	


	private static final String TPL_NAME = "cms_content";
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsContent content;
		try {
			content = cmsContentService.findCmsContentById(getSiteId(params),getBusinessId(params));
			Integer commentsNum = cmsCommentService.findCommentsNumByBusinessId(getSiteId(params),content.getId());
			Integer praiseNum = cmsPraiseService.findPraiseNumByBusinessId(getSiteId(params),content.getId());
			content.setCommentsNum(commentsNum);
			content.setPraiseNum(praiseNum);
		} catch (Exception e) {
			content = null;
			logger.error(e.getMessage(), e);
		}
		env.setVariable("content", DirectiveUtils.wrap(content));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
