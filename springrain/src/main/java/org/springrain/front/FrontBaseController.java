package org.springrain.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Enumerations.LinkFtlType;


public class FrontBaseController extends BaseController {
	@Resource
	private ICmsLinkService cmsLinkService;
	
	
	public  String jump( String siteId,String businessId,Integer siteType,HttpServletRequest request, Model model) 
			throws Exception {
		
		    CmsLink  cmsLink = cmsLinkService.findLinkBySiteBusinessId(siteId,businessId,LinkFtlType.前台页面连接.getType());
		    
		    String link=cmsLink.getLink();
		    String ftlFile=cmsLink.getFtlfile();
		    Integer jumpType=cmsLink.getJumpType();
			
			addModelParameter(request, model);
			
			model.addAttribute("siteId", siteId);
			model.addAttribute("businessId", businessId);
			model.addAttribute("siteType", siteType);
			
			
			if(link==null||jumpType==null||jumpType==0){
				return ftlFile;
			}else if(jumpType==1){
				return redirect+link;
			}else if(jumpType==2){
				return forward+link;
			}
			
			return ftlFile;
	}
	

}
