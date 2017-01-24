package org.springrain.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.utils.Enumerations.SiteType;

@Controller
@RequestMapping("/f/pc/{siteId}")
public class FrontPcController extends FrontBaseController {
	/**
	 * 映射首页页面
	 * @throws Exception 
	 * */
	@RequestMapping("/index")
	public String index(@PathVariable String siteId,HttpServletRequest request,Model model) throws Exception{
		return jump(siteId, siteId, SiteType.PC站.getType(), request, model);
	}
	
	/**
	 * 映射栏目页面
	 * @throws Exception 
	 * */
	@RequestMapping("/{businessId}")
	public String channel(@PathVariable String siteId,@PathVariable String businessId,HttpServletRequest request,Model model) throws Exception{
		return jump(siteId, businessId, SiteType.PC站.getType(), request, model);
	}
}
