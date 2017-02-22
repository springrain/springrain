package org.springrain.cms.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("cmsLink")
public class CmsLinkDirective extends AbstractCMSDirective {
	
	@Resource
	private ICmsLinkService cmsLinkService;
	private static final String TPL_NAME = "cms_link";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		
		String businessId = getBusinessId(params);
		String siteId=getSiteId(params);
		
		String cacheKey=TPL_NAME+"_cache_key_"+siteId+"_"+businessId;
		
		
		CmsLink cmsLink = (CmsLink) getDirectiveData(cacheKey);
		if(cmsLink == null){
			try {
				
				if(StringUtils.isBlank(businessId)){
					businessId = getBusinessId(params);
				}
				cmsLink = cmsLinkService.findLinkBySiteBusinessId(siteId, businessId);
			} catch (Exception e) {
				cmsLink = new CmsLink();
			}
			setDirectiveData(cacheKey,cmsLink);
		}
		
		env.setVariable("cmsLink", DirectiveUtils.wrap(cmsLink));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}
}
