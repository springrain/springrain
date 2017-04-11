package org.springrain.police.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.police.entity.Illegalcode;
import org.springrain.police.service.IIllegalcodeService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 违法代码详情
 * @author 跨时代
 *
 */
@Component("illegalcodeDirective")
public class IllegalcodeDirective  extends AbstractCMSDirective  {
	
	private static final String TPL_NAME = "s_illegalcode";
	@Resource
	private IIllegalcodeService illegalcodeService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		
		String id = DirectiveUtils.getString("id", params);
		String siteId=getSiteId(params);
		
		String cacheKey=TPL_NAME+"_cache_key_"+siteId+"_"+id;
		
		Illegalcode illegalcode=(Illegalcode) getDirectiveData(cacheKey);
		if(illegalcode==null){
			try {
				illegalcode = illegalcodeService.findById(id, Illegalcode.class);
			} catch (Exception e) {
				illegalcode = null;
				logger.error(e.getMessage(), e);
			}
			setDirectiveData(cacheKey,illegalcode);
		}
		
		env.setVariable("illegalcode", DirectiveUtils.wrap(illegalcode));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
