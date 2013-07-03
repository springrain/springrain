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
import org.iu9.frame.util.Page;
import org.iu9.frame.util.GlobalStatic;
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

import ${basepackage}.entity.${className};
import ${basepackage}.service.I${className}Service;
import org.iu9.frame.controller.BaseController;
import org.iu9.frame.util.MessageUtils;
import org.iu9.frame.util.CFReturnObject;
import org.iu9.frame.util.SecUtils;
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
		// ==构造分页对象
		Page page = newPage(request);
		// ==执行分页查询
		List<${className}> datas=${classNameLower}Service.findListDataByFinder(null,page,${className}.class,${classNameLower});
		// ==分页对象封装到前台
		model.addAttribute("page", page);
		// ==列表数据封装到前台
		model.addAttribute("datas",datas);
		// ==把查询条件重新封装到前台
		model.addAttribute("${classNameLower}",${classNameLower});
		return listurl;
	}
	
	
	/**
	* 进入${classNameLowerCase} Web页面后直接展现第一页数据,用于初始化发访问,可以设置默认的查询条件
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version <#if now??> ${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
	*/
	@RequestMapping("/list/pre")
	public String listpre(HttpServletRequest request, Model model,${className} ${classNameLower}) throws Exception{
		return list(request, model,${classNameLower});
	}
	
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,${className} ${classNameLower}) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = ${classNameLower}Service.findDataExportExcel(null,listurl, page,${className}.class,${classNameLower}Service,${classNameLower});
		String fileName="${classNameLower}"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	/**
	 * 查看操作
	 */
	@RequestMapping(value="/show")
	public String show(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		${pkJavaType} id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  ${className} ${classNameLower} = ${classNameLower}Service.find${className}ById(id);
		   model.addAttribute("${classNameLower}",${classNameLower});
		}
		return "/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}Cru";
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
		if(StringUtils.isBlank(${classNameLower}.getId())){// 新增
			<#list table.pkColumns as column>
				${classNameLower}.set${column.columnName}(SecUtils.getUUID());
			</#list>
			try {
				${classNameLower}Service.save${className}(${classNameLower});
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
				${classNameLower}Service.update${className}(${classNameLower});
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
		${pkJavaType} id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
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
			${pkJavaType} id=request.getParameter("id");
		    if(StringUtils.isNotBlank(id)){
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
		int i = 0;
		for (String str : rs) {
			try {
				${classNameLower}Service.deleteById(str,${className}.class);
			} catch (Exception e) {
				if (i > 0) {
					return new CFReturnObject(CFReturnObject.ERROR, MessageUtils.DELETE_ALL_WARNING);
				}
				return new CFReturnObject(CFReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
			}
			i++;
		}
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}
	
	
	private Page newPage(HttpServletRequest request){
		//==获取分页信息
		int pageIndex = NumberUtils.toInt(request.getParameter("pageIndex"), 1);
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		if(StringUtils.isBlank(order)){
		//默认页面排序 字段
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
