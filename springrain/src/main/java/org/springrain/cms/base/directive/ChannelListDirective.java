package org.springrain.cms.base.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.base.directive.util.DirectiveUtils;
import org.springrain.cms.base.entity.CmsChannel;
import org.springrain.cms.base.service.ICmsChannelService;
import org.springrain.cms.base.service.ICmsLinkService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 栏目列表标签
 */
@Component("channelListDirective")
public class ChannelListDirective extends AbstractCMSDirective {
	
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private ICmsLinkService cmsLinkService;
	
	
	
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "cms_channel_list";

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<CmsChannel> list;
		try {
			list = cmsChannelService.findTreeByPid(null, getSiteId());
			for (CmsChannel cmsChannel : list) {//栏目内容较少，可以用遍历方式设置链接属性
				cmsChannel.setLink(cmsLinkService.findLinkByBusinessId(cmsChannel.getId()));
			}
		} catch (Exception e) {
			list = new ArrayList<>();
		}
		env.setVariable("channel_list", DirectiveUtils.wrap(list));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
}
