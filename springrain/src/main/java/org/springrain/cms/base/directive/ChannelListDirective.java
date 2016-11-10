package org.springrain.cms.base.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springrain.cms.base.directive.abs.AbstractChannelDirective;
import org.springrain.cms.base.entity.CmsChannel;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 栏目列表标签
 */
@Component("channelListDirective")
public class ChannelListDirective extends AbstractChannelDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "cms_channel_list";

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<CmsChannel> list=new ArrayList<CmsChannel>();
		Object object = params.get("p");
		System.out.println("-----------------------:"+object);
		for(int i=0;i<5;i++){
			CmsChannel c=new CmsChannel();
			c.setId("c"+i);
			c.setName("name"+i);
			list.add(c);
		}
		env.setVariable("channel_list", new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_22).build().wrap(list));
		if (body != null) { 
			body.render(env.getOut());  
		}
		
	
		
	}
}
