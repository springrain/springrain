package org.springrain.system.cms.web.s;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springrain.frame.controller.BaseController;
import org.springrain.system.cms.entity.CmsLink;
import org.springrain.system.cms.service.ICmsLinkService;

public class SiteBaseController extends BaseController {
	@Resource
	private ICmsLinkService cmsLinkService;

	public String jump(String siteId, String businessId, Integer modelType, HttpServletRequest request, Model model)
			throws Exception {

		CmsLink cmsLink = cmsLinkService.findLinkBySiteBusinessIdModelType(siteId, businessId, modelType);

		String ftlFile = cmsLink.getFtlfile();

		addModelParameter(request, model);

		model.addAttribute("siteId", siteId);
		model.addAttribute("businessId", businessId);

		return ftlFile;
	}

}
