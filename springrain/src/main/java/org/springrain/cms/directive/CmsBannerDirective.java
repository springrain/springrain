package org.springrain.cms.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsBanner;
import org.springrain.cms.service.ICmsBannerService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("bannerDirective")
public class CmsBannerDirective extends AbstractCMSDirective {
	
	/**
	 * 模板名称
	 */
	private static final String TPL_NAME = "cms_banner";
	@Resource
	private ICmsBannerService cmsBannerService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = DirectiveUtils.getString("id", params);
		CmsBanner banner;
		try {
			banner = cmsBannerService.findCmsBannerById(id);
			if(banner==null)
				banner = new CmsBanner();
		} catch (Exception e) {
			banner = new CmsBanner();
		}
		env.setVariable("banner", DirectiveUtils.wrap(banner));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
		
	}
}
