package org.springrain.activity.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.activity.entity.ActivityMain;
import org.springrain.activity.service.IActivityMainService;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 活动内容
 * @author dmin93
 * @date 2017年3月20日
 */
@Component("cmsActivityMainDirective")
public class ActivityMainDirective extends AbstractCMSDirective{

	private static final String TPL_NAME = "cms_activity_main";
	
	@Resource
	private IActivityMainService activityMainService;
	
	/**
	 * 输入参数，内容ID
	 */
	public static final String PARAM_ID = "id";
	/**
	 * 输入参数，下一篇
	 */
	public static final String PARAM_NEXT = "next";
	/**
	 * 输入参数，栏目ID
	 */
	public static final String PARAM_CHANNEL_ID = "channelId";
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			ActivityMain activityMain;
			
			Boolean next = DirectiveUtils.getBool(PARAM_NEXT, params);
			String id = DirectiveUtils.getString(PARAM_ID, params);
			String siteId = this.getSiteId(params);
			String channelId = DirectiveUtils.getString(PARAM_CHANNEL_ID, params);
			if(id == null){
				id = this.getBusinessId(params);
			}
			if(next != null ){
				activityMain = activityMainService.findActivityMainSide(id,siteId,channelId,next);
			}else{
				activityMain = activityMainService.findActivityMainById(siteId, id);
			}
			
			env.setVariable(DirectiveUtils.OUT_BEAN, DirectiveUtils.wrap(activityMain));
			if(body!=null){
				body.render(env.getOut());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
