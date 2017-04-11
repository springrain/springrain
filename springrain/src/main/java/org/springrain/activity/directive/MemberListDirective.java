package org.springrain.activity.directive;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.activity.entity.Member;
import org.springrain.activity.service.IMemberService;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 会员列表
 * @author dmin93
 * @date 2017年3月27日
 */
@Component("cmsMemberListDirective")
public class MemberListDirective extends AbstractCMSDirective{

	private static final String TPL_NAME = "cms_member_list";
	
	@Resource
	private IMemberService memberService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			List<Member> memberList = getList(params, env, this.getSiteId(params));
			env.setVariable(DirectiveUtils.OUT_LIST, DirectiveUtils.wrap(memberList));
			
			if (body != null) {
				body.render(env.getOut());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}		
	}
	
	/**
	 * 获取会员列表
	 * @param params
	 * @param env
	 * @param siteId
	 * @param channelId
	 * @return
	 */
	private List<Member> getList(Map params, Environment env, String siteId) throws Exception{
		List<Member> resList;
		String[] idArr = DirectiveUtils.getStringArr(PARAM_IDS, params,PARAM_SPLIT);
		if(idArr != null && idArr.length > 0 ){
			// 有ID以ID作为主参数进行查询，排斥其他筛选参数
			resList = memberService.findListByIdsForTag(Arrays.asList(idArr),getOrderBy(params));
		} else {
			resList = memberService.findListForTag(params, env, siteId);
		}
		return resList;
	}
	
	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
