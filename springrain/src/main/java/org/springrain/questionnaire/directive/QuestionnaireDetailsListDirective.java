package org.springrain.questionnaire.directive;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.ContentConstant;
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
@Component("questionnaireDetailsListDirective")
public class QuestionnaireDetailsListDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "questionnaire_details_list";

	@Resource
	private IQuestionnaireDetailsService questionnaireDetailsService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			List<QuestionnaireDetails> questionnaireDetailsList = getList(params, env, this.getSiteId(params),this.getBusinessId(params));
			env.setVariable(DirectiveUtils.OUT_LIST, DirectiveUtils.wrap(questionnaireDetailsList));
			if (body != null) {
				body.render(env.getOut());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取列表
	 * @param params
	 * @param env
	 * @param siteId
	 * @return
	 */
	private List<QuestionnaireDetails> getList(Map params, Environment env, String siteId, String channelId) throws Exception{
		List<QuestionnaireDetails> resList;
		String[] idArr = DirectiveUtils.getStringArr(PARAM_IDS, params,PARAM_SPLIT);
		if(idArr != null && idArr.length > 0 ){
			// 有ID以ID作为主参数进行查询，排斥其他筛选参数
			resList = questionnaireDetailsService.findListByIdsForTag(Arrays.asList(idArr),getOrderBy(params));
		} else {
			// 前台使用还是后台使用
			Boolean isFront = DirectiveUtils.getBool("isFront", params);
			if(isFront == null){
				isFront = true;
			}
			
			if(isFront){
				// 默认查询可用的内容
				Integer active = DirectiveUtils.getInt("active", params);
				if(active == null){
					params.put("active", DirectiveUtils.wrap(ContentConstant.CONTENT_ACTIVE_YES));
				}
			}else{
				Integer active = DirectiveUtils.getInt("active", params);
				if(active == null){
					params.remove("active");
				}
			}
			
			resList = questionnaireDetailsService.findListForTag(params, env, siteId, channelId);
		}
		return resList;
	}
	
	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
