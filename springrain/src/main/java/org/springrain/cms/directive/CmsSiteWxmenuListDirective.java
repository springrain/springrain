package org.springrain.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsWxMenu;
import org.springrain.cms.service.ICmsWxMenuService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("siteWxmenuListDirective")
public class CmsSiteWxmenuListDirective extends AbstractCMSDirective {
	
	private static final String TPL_NAME = "cms_site_wxmenu_list";
	@Resource
	private ICmsWxMenuService cmsWxMenuService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String siteId = DirectiveUtils.getString("id", params);
		List<CmsWxMenu> menuList;
		try {
			menuList = cmsWxMenuService.findParentMenuList(siteId);
			if (menuList != null){
				for (CmsWxMenu cmsWxMenu : menuList) {
					cmsWxMenu.setChildMenuList(cmsWxMenuService.findChildMenuListByPid(cmsWxMenu.getId()));
				}
			}
		} catch (Exception e) {
			menuList = new ArrayList<>();
		}
		env.setVariable("menuList", DirectiveUtils.wrap(menuList));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}
}
