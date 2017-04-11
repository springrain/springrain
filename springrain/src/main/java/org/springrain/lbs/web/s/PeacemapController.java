package  org.springrain.lbs.web.s;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
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
@RequestMapping(value="/s/peacemap/{siteId}/{bussinissId}")
public class PeacemapController  extends SiteBaseController {
	@Resource
	private IPeacemapService peacemapService;
	
	private String listurl="/u/s_10007/s/peacemap/peacemapList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param peacemap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String siteId, @PathVariable String bussinissId,
			Peacemap peacemap) throws Exception {
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnDatas.setPage(page);
		returnDatas.setQueryBean(peacemap);
		String name=null;
		if(peacemap!=null){
			name=peacemap.getName();
			if(StringUtils.isNotBlank(name)){
				model.addAttribute("name", name);
			}
		}
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);

		model.addAttribute("siteId", siteId);
		model.addAttribute("bussinissId", bussinissId);
		return jump(siteId, bussinissId, Enumerations.CmsLinkModeType.站长后台列表.getType(), request, model);
	}
	
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
			@PathVariable String siteId, @PathVariable String bussinissId,
			Peacemap peacemap) throws Exception{

		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Peacemap> datas = peacemapService.findPeacemapBySidCid(siteId, bussinissId,Peacemap.class,page);
		returnObject.setQueryBean(peacemap);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Peacemap peacemap) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = peacemapService.findDataExportExcel(null,listurl, page,Peacemap.class,peacemap);
		String fileName="peacemap"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/base/peacemap/peacemapLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody 
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  Peacemap peacemap = peacemapService.findPeacemapById(id);
		   returnObject.setData(peacemap);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody 
	public ReturnDatas saveorupdate(Model model,Peacemap peacemap,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =peacemap.getId();
			if(StringUtils.isBlank(id)){
			  peacemap.setId(null);
			}
		
			peacemapService.saveorupdate(peacemap);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,
			@PathVariable String siteId, @PathVariable String bussinissId,
			HttpServletResponse response)  throws Exception{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("id", id);
			return jump(siteId,bussinissId,Enumerations.CmsLinkModeType.站长后台更新.getType(),request,model);
		}else{
			return jump(siteId,bussinissId,Enumerations.CmsLinkModeType.站长后台查看.getType(),request,model);
		}
		
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody 
	public  ReturnDatas delete(HttpServletRequest request) throws Exception {
			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				peacemapService.deleteById(id,Peacemap.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,
						MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping("/delete/more")
	@ResponseBody 
	public ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			peacemapService.deleteByIds(ids,Peacemap.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
