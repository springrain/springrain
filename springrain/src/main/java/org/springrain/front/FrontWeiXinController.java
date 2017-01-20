package org.springrain.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/f/0/{siteId}")
public class FrontWeiXinController extends FrontBaseController {
	
	
	@RequestMapping("/index")
	public  String index(@PathVariable String siteId,HttpServletRequest request, Model model) 
			throws Exception {
		
	  return jump(siteId, siteId, 0, request, model);
			
	}
	
	/**
	 * 映射栏目页面
	 * @throws Exception 
	 * */
	@RequestMapping("/{businessId}")
	public String channel(@PathVariable String siteId,@PathVariable String businessId,HttpServletRequest request,Model model) throws Exception{
		return jump(siteId, businessId, 0, request, model);
	}
}
