package org.springrain.activity.directive;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
 * 活动内容列表
 * @author dmin93
 * @date 2017年3月14日
 */
@Component("cmsActivityMainListDirective")
public class ActivityMainListDirective extends AbstractCMSDirective{

	private static final String TPL_NAME = "cms_activity_main_list";
	
	@Resource
	private IActivityMainService activityMainService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			// 查询当前登录人是否参与过的活动
			String hasPartIn = DirectiveUtils.getString("hasPartIn", params);
			if(StringUtils.isNotEmpty(hasPartIn)){
				params.put("openId", DirectiveUtils.wrap(this.getRequest().getSession().getAttribute("openId")));
			}
			
			String channelId = DirectiveUtils.getString("channelId", params);
			if(StringUtils.isEmpty(channelId)){
				channelId = this.getBusinessId(params);
			}
			List<ActivityMain> activityMainList = getList(params, env, this.getSiteId(params),channelId);
			env.setVariable(DirectiveUtils.OUT_LIST, DirectiveUtils.wrap(activityMainList));
			
			if (body != null) {
				body.render(env.getOut());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 获取活动内容列表
	 * @param params
	 * @param env
	 * @param siteId
	 * @return
	 */
	private List<ActivityMain> getList(Map params, Environment env, String siteId, String channelId) throws Exception{
		List<ActivityMain> resList;
		String[] idArr = DirectiveUtils.getStringArr(PARAM_IDS, params,PARAM_SPLIT);
		if(idArr != null && idArr.length > 0 ){
			// 有ID以ID作为主参数进行查询，排斥其他筛选参数
			resList = activityMainService.findListByIdsForTag(Arrays.asList(idArr),getOrderBy(params));
		} else {
			// 默认查询可用的内容
			Integer active = DirectiveUtils.getInt("active", params);
			if(active == null){
				params.put("active", DirectiveUtils.wrap(1));
			}
			
			resList = activityMainService.findListForTag(params, env, siteId, channelId);
		}
		return resList;
	}
	
	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
