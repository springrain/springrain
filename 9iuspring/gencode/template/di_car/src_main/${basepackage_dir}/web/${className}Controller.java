<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign pkJavaType = table.idColumn.javaType>  
package  ${basepackage}.web;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileCopyUtils;

import ${basepackage}.entity.${className};
import ${basepackage}.service.I${className}Service;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.CFReturnObject;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
<#assign myParentDir="web">


<#include "/copyright_class.include" >
@Controller
@RequestMapping(value="/${classNameLowerCase}")
public class ${className}Controller  extends BaseController {
	@Resource
	private I${className}Service ${classNameLower}Service;
	
	private String listurl="/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}List";
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	          super.initBinder(binder);
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute("")
	public void init(Model model) {
		model.addAttribute("now", new Date());
	}
	
	/**
	* 进入${classNameLowerCase} Web页面后直接展现第一页数据
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version <#if now??> ${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
	*/
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,${className} ${classNameLower}) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<${className}> datas=${classNameLower}Service.findListDataByFinder(null,page,${className}.class,${classNameLower});
		model.addAttribute("page", page);
		model.addAttribute("datas",datas);
		model.addAttribute("${classNameLower}",${classNameLower});
		return listurl;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,${className} ${classNameLower}) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = ${classNameLower}Service.findDataExportExcel(null,listurl, page,${className}.class,${classNameLower});
		String fileName="${classNameLower}"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	/**
	 * 查看操作
	 */
	@RequestMapping(value="/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	     <#if pkJavaType=="java.lang.String">
		${pkJavaType} id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		<#else>
		  String  strId=request.getParameter("id");
		  ${pkJavaType} id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= ${pkJavaType}.valueOf(strId.trim());
			}
		
		</#if>
		
		  ${className} ${classNameLower} = ${classNameLower}Service.find${className}ById(id);
		   model.addAttribute("${classNameLower}",${classNameLower});
		}
		return "/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}Look";
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param ${classNameLower}
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version <#if now??> ${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
	*/
	@RequestMapping("/update")
	public String saveorupdate(Model model,${className} ${classNameLower},HttpServletRequest request,HttpServletResponse response) throws Exception{
		  <#if pkJavaType=="java.lang.String">
		if(StringUtils.isBlank(${classNameLower}.getId())){// 新增
		<#else>
		if(${classNameLower}.getId()!=null){// 新增
		</#if>
			<#list table.pkColumns as column>
				${classNameLower}.set${column.columnName}(SecUtils.getUUID());
			</#list>
			try {
				${classNameLower}Service.save(${classNameLower});
				model.addAttribute(message, MessageUtils.ADD_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e);
			}
			     model.addAttribute(message, MessageUtils.ADD_FAIL);
			     return messageurl;
		} else {// 修改
			try {
			<#list table.pkColumns as column>
				${classNameLower}.set${column.columnName}(${classNameLower}.getId());
			</#list>
				${classNameLower}Service.update(${classNameLower});
				model.addAttribute(message, MessageUtils.EDIT_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e);
			}
			model.addAttribute(message, MessageUtils.EDIT_WARING);
			return messageurl;
		}
		
	}
	
	/**
	 * 进入修改页面
	 */
	@RequestMapping(value="/update/pre")
	public String edit(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		  <#if pkJavaType=="java.lang.String">
		${pkJavaType} id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		<#else>
		  String  strId=request.getParameter("id");
		  ${pkJavaType} id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= ${pkJavaType}.valueOf(strId.trim());
			}
		
		</#if>
		   ${className} ${classNameLower} = ${classNameLower}Service.find${className}ById(id);
		   model.addAttribute("${classNameLower}",${classNameLower});
		}
		return "/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}Cru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody CFReturnObject destroy(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
	   <#if pkJavaType=="java.lang.String">
		${pkJavaType} id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		<#else>
		  String  strId=request.getParameter("id");
		  ${pkJavaType} id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= ${pkJavaType}.valueOf(strId.trim());
			}
		</#if>
			${classNameLower}Service.deleteById(id,${className}.class);
			return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
			}else{
			    return new CFReturnObject(CFReturnObject.WARNING, MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
				logger.error(e);
		}
		return new CFReturnObject(CFReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	* 删除多条记录
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version <#if now??> ${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
	*/
	@RequestMapping("/delMulti")
	public @ResponseBody
	CFReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		String[] rs = records.split(",");
		if(rs==null||rs.length<1){
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
		}
		try {
		List<String> ids = Arrays.asList(rs);
		${classNameLower}Service.deleteByIds(ids,${className}.class);
		}
		catch (Exception e) {
			return new CFReturnObject(CFReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
		
		}
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}
		/**
	 *  上传文件
	 * @param uploadFile
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	public @ResponseBody Map<String,String> upload(@RequestParam MultipartFile uploadFile,
			HttpServletRequest request, Model model,
			HttpServletResponse response) throws Exception {
		String info = "";
		File destFile = null;

		try {
			String uploadDirPath = request.getSession().getServletContext()
					.getRealPath("/upload");
			File dir = new File(uploadDirPath);
			if (dir.exists() == false) {
				dir.mkdirs();
			}
			destFile = new File(uploadDirPath + "/"
					+ uploadFile.getOriginalFilename());
			FileCopyUtils.copy(uploadFile.getBytes(), destFile);
		} catch (IOException e) {
			logger.error(e);
			info = "上传失败,文件处理异常.";
		}

		try {
			info=${classNameLower}Service.saveImportExcelFile(destFile,${className}.class);
		} catch (Exception e) {
		    logger.error(e);
			info = e.getMessage();
		}
		if (StringUtils.isBlank(info)) {
			info = "数据导入成功....";
		}
	  Map<String,String> map=new HashMap<String,String>();
	  map.put("msg", info);
	  return map;

	}
	
	private Page newPage(HttpServletRequest request){
		//==获取分页信息
		int pageIndex = NumberUtils.toInt(request.getParameter("pageIndex"), 1);
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		if(StringUtils.isBlank(order)){
		  order="id";
		}
		if(StringUtils.isBlank(sort)){
		  sort="desc";
		}
		
		Page page=new Page(pageIndex);
		page.setOrder(order);
		page.setSort(sort);
		return page;
	}
}
