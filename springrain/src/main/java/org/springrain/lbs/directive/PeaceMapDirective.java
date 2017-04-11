package org.springrain.lbs.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.lbs.entity.Peacemap;
import org.springrain.lbs.service.IPeacemapService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("PeaceMapDirective")
public class PeaceMapDirective  extends AbstractCMSDirective  {
	
	private static final String TPL_NAME = "s_peacemap";
	
	@Resource
	private IPeacemapService peacemapService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String id = DirectiveUtils.getString("id", params);
		String siteId=getSiteId(params);
		
		String cacheKey=TPL_NAME+"_cache_key_"+siteId+"_"+id;
		
		Peacemap peacemap=(Peacemap) getDirectiveData(cacheKey);
		if(peacemap==null){
			try {
				peacemap = peacemapService.findPeacemapById(id);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				peacemap = new Peacemap();
			}
			setDirectiveData(cacheKey,peacemap);
		}
		env.setVariable("peacemap", DirectiveUtils.wrap(peacemap));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
