package  org.springrain.lbs.web.f;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.cms.web.f.FrontBaseController;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.lbs.entity.Peacemap;
import org.springrain.lbs.service.IPeacemapService;



/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-14 10:44:41
 * @see org.springrain.lbs.entity.base.web.Peacemap
 */
@Controller
@RequestMapping(value="/f/mp/peacemap/{siteid}/{bussinissId}")
public class MapController  extends FrontBaseController {
	@Resource
	private IPeacemapService peacemapService;
	
	
	
	   
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param peacemap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnDatas listjson(HttpServletRequest request, Model model,
			@PathVariable String siteid, @PathVariable String bussinissId,
			Peacemap peacemap) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Peacemap> datas = peacemapService.findPeacemapBySidCid(siteid, bussinissId,Peacemap.class,page);
		returnObject.setQueryBean(peacemap);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
}
