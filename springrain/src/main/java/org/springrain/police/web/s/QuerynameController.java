package  org.springrain.police.web.s;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
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
@RequestMapping(value="/s/queryname/{siteId}/{bussinissId}")
public class QuerynameController  extends SiteBaseController {
	@Resource
	private IQuerynameService querynameService;
	
	private String listurl="/base/queryname/querynameList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param queryname
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String siteId, @PathVariable String bussinissId,
			Queryname queryname) 
			throws Exception {
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnDatas.setPage(page);
		returnDatas.setQueryBean(queryname);
		returnDatas.setData(queryname);
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
	 * @param queryname
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody
	public ReturnDatas listjson(HttpServletRequest request, Model model,Queryname queryname) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Queryname> datas=querynameService.findListDataByFinder(null,page,Queryname.class,queryname);
		returnObject.setQueryBean(queryname);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Queryname queryname) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = querynameService.findDataExportExcel(null,listurl, page,Queryname.class,queryname);
		String fileName="queryname"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	@RequestMapping(value = "/import")
	public void upload(@RequestParam MultipartFile uploadExcelfile,
			HttpServletRequest request, Model model,
			HttpServletResponse response,@PathVariable String siteId,@PathVariable String bussinissId) throws Exception {
		String uploadDirPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		MultipartFile image = uploadExcelfile;
		File destFile = new File(uploadDirPath + "/"
				+ image.getOriginalFilename());
		try {
			FileCopyUtils.copy(image.getBytes(), destFile);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
		}
		String info = "";
		try {
			info = querynameService.saveImportExcelFile(destFile,
					Queryname.class,siteId,bussinissId, false);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
			info = e.getMessage();
		}
		if (StringUtils.isBlank(info)) {
			info = "数据导入成功....";
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		response.getWriter().println("{'msg':'" + info + "'}");

	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/base/queryname/querynameLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  Queryname queryname = querynameService.findQuerynameById(id);
		   returnObject.setData(queryname);
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
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,Queryname queryname,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =queryname.getId();
			if(StringUtils.isBlank(id)){
			  queryname.setId(null);
			  queryname.setLeadTime(new java.util.Date());
			}
			querynameService.saveorupdate(queryname);
			
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
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				querynameService.deleteById(id,Queryname.class);
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
	public @ResponseBody
	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
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
			querynameService.deleteByIds(ids,Queryname.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
