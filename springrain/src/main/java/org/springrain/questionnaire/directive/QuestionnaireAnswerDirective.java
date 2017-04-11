package org.springrain.questionnaire.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.questionnaire.entity.QuestionnaireAnswer;
import org.springrain.questionnaire.service.IQuestionnaireAnswerService;

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
@Component("questionnaireAnswerDirective")
public class QuestionnaireAnswerDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "questionnaire_answer";

	@Resource
	private IQuestionnaireAnswerService questionnaireAnswerService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			QuestionnaireAnswer qa;
			String id = DirectiveUtils.getString(PARAM_ID, params);
			if(StringUtils.isEmpty(id)){
				qa = null;
			}else{
				qa = questionnaireAnswerService.findById(id, QuestionnaireAnswer.class);
			}
			env.setVariable(DirectiveUtils.OUT_BEAN, DirectiveUtils.wrap(qa));
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
