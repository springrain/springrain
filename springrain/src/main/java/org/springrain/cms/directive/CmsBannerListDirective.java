package org.springrain.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

@Component("bannerListDirective")
public class CmsBannerListDirective extends AbstractCMSDirective {
	
	/**
	 * 模板名称
	 */
	private static final String TPL_NAME = "cms_banner_list";
	@Resource
	private ICmsBannerService cmsBannerService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<CmsBanner> bannerList;
		try {
			bannerList = cmsBannerService.findBannerListByBusinessId(getSiteId(params),getBusinessId(params));
		} catch (Exception e) {
			bannerList = new ArrayList<>();
		}
		
		env.setVariable("bannerList", DirectiveUtils.wrap(bannerList));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
		
	}

}
