package org.springrain.system.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SpringUtils;
import org.springrain.system.cms.entity.CmsComment;
import org.springrain.system.cms.service.ICmsCommentService;
import org.springrain.system.cms.util.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("cmsCommentListDirective")
public class CmsCommentListDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "cms_comment_list";

	@Resource
	private ICmsCommentService cmsCommentService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		StringModel stringModel = (StringModel) params.get("page");
		Page page = (Page) stringModel.getAdaptedObject(Page.class);

		// 修改分页大小
		Integer pageSize = DirectiveUtils.getInt("pageSize", params);
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}

		String pageSort = DirectiveUtils.getString("pageSort", params);
		if (pageSort != null) {
			page.setSort(pageSort);
		}

		String pageOrder = DirectiveUtils.getString("pageOrder", params);
		if (pageOrder != null) {
			page.setOrder(pageOrder);
		}

		List<CmsComment> commentList;
		try {
			commentList = cmsCommentService.findCommentListByBusinessId(this.getSiteId(params),
					this.getBusinessId(params), page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			commentList = new ArrayList<>();
		}

		env.setVariable(DirectiveUtils.OUT_LIST, DirectiveUtils.wrap(commentList));

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
