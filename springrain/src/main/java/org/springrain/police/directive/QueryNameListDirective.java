package org.springrain.police.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springrain.cms.directive.AbstractCMSDirective;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.frame.util.Page;
import org.springrain.police.entity.Queryname;
import org.springrain.police.service.IQuerynameService;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 内容列表
 * 
 * @author dmin93
 * @date 2017年3月6日
 */
@Component("queryNameListDirective")
public class QueryNameListDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "query_name_list";
	private static final String TAG_NAME = "datas";
	@Resource
	private IQuerynameService querynameService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			List<Queryname> querynamesList;
			StringModel stringModel = (StringModel) params.get("page");
			Page page = (Page) stringModel.getAdaptedObject(Page.class);
			stringModel=(StringModel)params.get("queryname");
			/*String sid=DirectiveUtils.getString("siteId", params);
			String cid=DirectiveUtils.getString("bussinissId", params);
			String name=DirectiveUtils.getString("name", params);
			String city=DirectiveUtils.getString("city", params);
			String distrit=DirectiveUtils.getString("district", params);*/
			Queryname queryname =new Queryname();
			if (stringModel!=null) {
				queryname =(Queryname) stringModel.getAdaptedObject(Queryname.class);
			}
			try {
				/*if(StringUtils.isNotBlank(sid)){
					queryname.setSiteId(sid);
				}
				if(StringUtils.isNotBlank(cid)){
					queryname.setBussinissId(cid);
				}
				if(StringUtils.isNotBlank(name)){
					queryname.setName(name);
				}
				if(StringUtils.isNotBlank(city)){
					queryname.setCity(city);
				}
				if(StringUtils.isNotBlank(distrit)){
					queryname.setDistrict(distrit);
				}*/
				querynamesList=querynameService.findListDataByFinder(null, page, Queryname.class, queryname);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				querynamesList=new ArrayList<>();
			}
			TemplateModel wrap = DirectiveUtils.wrap(querynamesList);
			env.setVariable(TAG_NAME, wrap);
			if (body != null) {
				body.render(env.getOut());
			}
		
	}

	

	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
