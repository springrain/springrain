package org.springrain.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.utils.DirectiveUtils;
import org.springrain.frame.util.Page;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("contentListDirective")
public class ContentListDirective  extends AbstractCMSDirective  {
	
	@Resource
	private ICmsContentService cmsContentService;
	
	
	
	/**
	 * 模板名称
	 */
	private static final String TPL_NAME = "cms_content_list";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<CmsContent> contentList = (List<CmsContent>) getDirectiveData();
		if(contentList == null){
			String type = DirectiveUtils.getString("type", params);
			int pageIndex = DirectiveUtils.getInt("p", params);
			Page page = new Page(pageIndex);
			try {
				if(type.equals("0")){//查询站点首页下的内容
					contentList = cmsContentService.findListBySiteId(getSiteId(params), page);
				}else if (type.equals("1")){//查询栏目下的内容
					contentList = cmsContentService.findContentByChannelId(getSiteId(params),getBusinessId(params), page);
				}else{
					//目前暂时没有其它类型，先占位
					contentList = new ArrayList<>();
				}
			} catch (Exception e) {
				contentList = new ArrayList<>();
			}
			setDirectiveData(contentList);
		}
		
		env.setVariable("content_list", DirectiveUtils.wrap(contentList));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}
	
}
