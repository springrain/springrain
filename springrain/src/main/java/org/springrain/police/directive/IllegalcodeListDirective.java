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
import org.springrain.police.entity.Illegalcode;
import org.springrain.police.service.IIllegalcodeService;

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
@Component("illegalcodeListDirective")
public class IllegalcodeListDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "illegalcode_list";
	private static final String TAG_NAME = "datas";
	@Resource
	private IIllegalcodeService illegalcodeService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			List<Illegalcode> illegalcodeList;
			StringModel stringModel = (StringModel) params.get("page");
			Page page=null;
			if (stringModel!=null) {
				page = (Page) stringModel.getAdaptedObject(Page.class);
			}
			stringModel=(StringModel)params.get("returnDatas");
			Illegalcode illegalcode =new Illegalcode();
			String wfxw=this.getRequest().getParameter("wfxw");
			if (StringUtils.isNotBlank(wfxw)) {
				illegalcode.setWfxw(wfxw);
			}
			if (stringModel!=null) {
				illegalcode =(Illegalcode) stringModel.getAdaptedObject(Illegalcode.class);
			}
			try {
				illegalcodeList=illegalcodeService.findListDataByFinder(null, page, Illegalcode.class, illegalcode);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				illegalcodeList=new ArrayList<>();
			}
			TemplateModel wrap = DirectiveUtils.wrap(illegalcodeList);
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
