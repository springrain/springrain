package org.springrain.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 内容列表
 * 
 * @author dmin93
 * @date 2017年3月6日
 */
@Component("cmsContentListDirective")
public class CmsContentListDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "cms_content_list";

	@Resource
	private ICmsContentService cmsContentService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			List<CmsContent> contentList = getList(params, env, this.getSiteId(params));

			Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
			paramWrap.put(DirectiveUtils.OUT_LIST,DirectiveUtils.wrap(contentList));
			Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);

			if (body != null) {
				body.render(env.getOut());
			}
			DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取内容列表
	 * 
	 * @param params
	 * @param env
	 * @return
	 * @throws Exception
	 */
	public List<CmsContent> getList(Map params, Environment env, String siteId)
			throws Exception {
		List<CmsContent> resList = new ArrayList<CmsContent>();
		List<String> idList = Arrays.asList(DirectiveUtils.getStringArr(PARAM_IDS, params,PARAM_SPLIT));
		if (idList != null && idList.size()>0) {
			// 有ID以ID作为主参数进行查询，排斥其他筛选参数
			resList = cmsContentService.findListByIdsForTag(idList,getOrderBy(params));
		} else {
			resList = cmsContentService.findListForTag(params, env, siteId);
		}
		return resList;
	}

	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
