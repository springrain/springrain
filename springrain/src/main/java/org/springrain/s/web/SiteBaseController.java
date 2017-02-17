package org.springrain.s.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.frame.controller.BaseController;


public class SiteBaseController extends BaseController {
	@Resource
	private ICmsLinkService cmsLinkService;
	
	
	public  String jump(String siteId, String businessId,String defaultLink,HttpServletRequest request, Model model) 
			throws Exception {
		
		    CmsLink  cmsLink = cmsLinkService.findLinkBySiteBusinessId(siteId,businessId,defaultLink);
		    
		    String ftlFile=cmsLink.getFtlfile();
			
			addModelParameter(request, model);
			
			model.addAttribute("siteId", siteId);
			model.addAttribute("businessId", businessId);
			
			
			return ftlFile;
	}
	

}
