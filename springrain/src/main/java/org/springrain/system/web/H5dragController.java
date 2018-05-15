package  org.springrain.system.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.entity.H5drag;
import org.springrain.system.service.IH5dragService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-05-14 15:58:54
 * @see org.springrain.system.web.H5drag
 */
@Controller
@RequestMapping(value="/system/h5drag")
public class H5dragController  extends BaseController {
	@Resource
	private IH5dragService h5dragService;
	
	
	
	   

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param h5drag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,H5drag h5drag) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<H5drag> datas=h5dragService.findListDataByFinder(null,page,H5drag.class,h5drag);
			//returnObject.setQueryBean(h5drag);
		//returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody      
	public ReturnDatas saveorupdate(@RequestBody  List<H5drag> data) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		    
		    Finder finder=Finder.getDeleteFinder(H5drag.class);
		    h5dragService.update(finder);
			h5dragService.save(data);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	
}
