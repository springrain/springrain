package org.springrain.activity.directive;

import java.io.IOException;
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
 * 会员信息
 * @author dmin93
 * @date 2017年3月23日
 */
@Component("cmsMemberDirective")
public class MemberDirective extends AbstractCMSDirective{

	private static final String TPL_NAME = "cms_member";
	
	@Resource
	private IMemberService memberService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			String openId = String.valueOf(this.getRequest().getSession().getAttribute("openId"));
			Member member = memberService.findMemberByOpenId(openId,this.getSiteId(params));
			env.setVariable(DirectiveUtils.OUT_BEAN, DirectiveUtils.wrap(member));
			if(body!=null){
				body.render(env.getOut());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
