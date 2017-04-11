package org.springrain.lbs.directive;

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
import org.springrain.lbs.entity.Peacemap;
import org.springrain.lbs.service.IPeacemapService;

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
@Component("peaceMapListDirective")
public class PeaceMapListDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "cms_peace_map_list";

	@Resource
	private IPeacemapService peacemapService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			List<Peacemap> peacemapList=null;
			StringModel stringModel = (StringModel) params.get("page");
			Page page = (Page) stringModel.getAdaptedObject(Page.class);
			String sid=DirectiveUtils.getString("siteId", params);
			String cid=DirectiveUtils.getString("bussinissId", params);
			String name=DirectiveUtils.getString("name", params);
			try {
				if(StringUtils.isNotBlank(name)){
					peacemapList = peacemapService.findPeacemapByName(sid, cid, name, Peacemap.class, page);
				}else{
					peacemapList = peacemapService.findPeacemapBySidCid(sid, cid,Peacemap.class,page);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				peacemapList=new ArrayList<>();
			}
			env.setVariable("peacemaplist", DirectiveUtils.wrap(peacemapList));
			if (body != null) {
				body.render(env.getOut());
			}
		
	}

	

	@PostConstruct
	public void registerFreeMarkerVariable() {
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}

}
