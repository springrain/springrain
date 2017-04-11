package  org.springrain.police.web.f;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.web.f.FrontBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.Enumerations.OrgType;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.police.dto.QueryNameResult;
import org.springrain.police.entity.Queryname;
import org.springrain.police.service.IQuerynameService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-23 09:35:23
 * @see org.springrain.police.entity.base.web.Queryname
 */
@Controller
@RequestMapping(value="/f/mp/queryname/{siteId}/{bussinissId}")
public class QnameController  extends FrontBaseController {
	@Resource
	private IQuerynameService querynameService;
	
	/**
	 * 前台找到页面
	 * 
	 * @param request
	 * @param model
	 * @param queryname
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String siteid, @PathVariable String bussinissId,
			Queryname queryname) 
			throws Exception {
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		returnDatas.setQueryBean(queryname);
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		Integer modelType=Enumerations.CmsLinkModeType.前台栏目.getType();
		if(bussinissId.startsWith("c_")){
			modelType=Enumerations.CmsLinkModeType.前台内容.getType();
		}
		String name=request.getParameter("name");
		if(StringUtils.isNotBlank(name)){
			List<Map<String, Object>> querynameresults=querynameService.findQueryNameResultList(name, QueryNameResult.class);
			int s=0;
			if(CollectionUtils.isNotEmpty(querynameresults)){
				for (Map<String, Object> map : querynameresults) {
					s+=Integer.parseInt(map.get("size").toString());
				}
			}
			model.addAttribute("size", s);
			model.addAttribute("results", querynameresults);
		}
		return jump(siteid, bussinissId, OrgType.mp.getType(), request, model,modelType);
	}
}
	
