package org.springrain.cms.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springrain.cms.directive.abs.AbstractChannelDirective;
import org.springrain.cms.directive.util.DirectiveUtils;
import org.springrain.cms.directive.util.DirectiveUtils.InvokeType;
import org.springrain.cms.entity.CmsChannel;

import freemarker.core.Environment;
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
	public static final String TPL_NAME = "channel_list";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Integer count = DirectiveUtils.getInt("count", params);
		
		System.out.println(count);
		
		
		List<CmsChannel> list=new ArrayList<CmsChannel>();
	 
		for(int i=0;i<5;i++){
			CmsChannel c=new CmsChannel();
			c.setId("c"+i);
			c.setName("name"+i);
			list.add(c);
		}
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put("channel_list", DEFAULT_WRAPPER.wrap(list));
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		//根据type 可以辨别类型 例如 手机 app 引入不同的css js 等
		InvokeType type = DirectiveUtils.getInvokeType(params);
		
	    body.render(env.getOut());
		
		
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
	}
}
