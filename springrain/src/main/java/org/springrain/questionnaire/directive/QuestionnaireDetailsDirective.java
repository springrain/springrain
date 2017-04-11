package org.springrain.questionnaire.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.questionnaire.entity.QuestionnaireDetails;
import org.springrain.questionnaire.service.IQuestionnaireDetailsService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 问卷详情列表
 * 
 * @author dmin93
 * @date 2017年4月7日
 */
@Component("questionnaireDetailsDirective")
public class QuestionnaireDetailsDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "questionnaire_details";

	@Resource
	private IQuestionnaireDetailsService questionnaireDetailsService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			QuestionnaireDetails qd;
			String id = DirectiveUtils.getString(PARAM_ID, params);
			Boolean next = DirectiveUtils.getBool(PARAM_NEXT, params);
			if(StringUtils.isEmpty(id)){
				qd = null;
			}else{
				if(next == null){
					qd = questionnaireDetailsService.findById(id);
				}else{
					// 查询与当前问题相关的上一题或下一题
					String siteId = this.getSiteId(params);
					String businessId = this.getBusinessId(params);
					qd = questionnaireDetailsService.findQuestionnaireDetailsSide(siteId,businessId,id,next);
				}
			}
			env.setVariable(DirectiveUtils.OUT_BEAN, DirectiveUtils.wrap(qd));
			if (body != null) {
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
