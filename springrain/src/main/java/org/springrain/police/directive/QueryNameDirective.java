package org.springrain.police.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.police.entity.Queryname;
import org.springrain.police.service.IQuerynameService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("QueryNameDirective")
public class QueryNameDirective  extends AbstractCMSDirective  {
	
	private static final String TPL_NAME = "query_name";
	
	@Resource
	private IQuerynameService querynameService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String id = DirectiveUtils.getString("id", params);
		Queryname queryname;
			try {
				queryname = querynameService.findQuerynameById(id);
				if(queryname==null)
					queryname = new Queryname();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				queryname = new Queryname();
			}
		env.setVariable("queryname", DirectiveUtils.wrap(queryname));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
