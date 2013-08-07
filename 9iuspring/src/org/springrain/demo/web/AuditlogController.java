package  org.springrain.demo.web;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.demo.entity.AuditLog;
import org.springrain.demo.service.IAuditlogService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.CFReturnObject;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-08-02 12:14:50
 * @see org.springrain.demo.web.Auditlog
 */
@Controller
@RequestMapping(value="/auditlog")
public class AuditlogController  extends BaseController {
	@Resource
	private IAuditlogService auditlogService;
	
	private String listurl="/demo/auditlog/auditlogList";
	
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
	* 进入auditlog Web页面后直接展现第一页数据
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-08-02 12:14:50
	*/
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,AuditLog auditlog) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<AuditLog> datas=auditlogService.findListDataByFinder(null,page,AuditLog.class,auditlog);
		model.addAttribute("page", page);
		model.addAttribute("datas",datas);
		model.addAttribute("auditlog",auditlog);
		return listurl;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,AuditLog auditlog) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = auditlogService.findDataExportExcel(null,listurl, page,AuditLog.class,auditlog);
		String fileName="auditlog"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	/**
	 * 查看操作
	 */
	@RequestMapping(value="/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		
			AuditLog auditlog = auditlogService.findAuditlogById(id);
		   model.addAttribute("auditlog",auditlog);
		}
		return "/demo/auditlog/auditlogLook";
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param auditlog
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version  2013-08-02 12:14:50
	*/
	@RequestMapping("/update")
	public String saveorupdate(Model model,AuditLog auditlog,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isBlank(auditlog.getId())){// 新增
				auditlog.setId(SecUtils.getUUID());
			try {
				auditlogService.save(auditlog);
				model.addAttribute(message, MessageUtils.ADD_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e);
			}
			     model.addAttribute(message, MessageUtils.ADD_FAIL);
			     return messageurl;
		} else {// 修改
			try {
				auditlog.setId(auditlog.getId());
				auditlogService.update(auditlog);
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
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			AuditLog auditlog = auditlogService.findAuditlogById(id);
		   model.addAttribute("auditlog",auditlog);
		}
		return "/demo/auditlog/auditlogCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody CFReturnObject destroy(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			auditlogService.deleteById(id,AuditLog.class);
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
	* @version  2013-08-02 12:14:50
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
		auditlogService.deleteByIds(ids,AuditLog.class);
		}
		catch (Exception e) {
			return new CFReturnObject(CFReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
		
		}
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
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
