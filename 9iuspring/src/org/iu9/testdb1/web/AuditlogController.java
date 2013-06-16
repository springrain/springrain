package  org.iu9.testdb1.web;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.iu9.frame.controller.BaseController;
import org.iu9.frame.util.CFReturnObject;
import org.iu9.frame.util.GlobalStatic;
import org.iu9.frame.util.MessageUtils;
import org.iu9.frame.util.Page;
import org.iu9.frame.util.SecUtils;
import org.iu9.testdb1.entity.AuditLog;
import org.iu9.testdb1.service.IAuditlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * TODO 日志记录,此表可以理解为系统表,必须存在,记录对每张表的操作.默认一年一张表
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-04-02 10:17:31
 * @see org.iu9.testdb1.web.AuditLog
 */
@Controller
@RequestMapping(value="/auditlog")
public class AuditlogController  extends BaseController {
	@Resource
	private IAuditlogService auditlogService;
	
	private String listurl="/testdb1/auditlog/auditlogList";
	
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
	* 进入auditlog Web页面后直接展现第一页数据,用于初始化发访问,可以设置默认的查询条件
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-06-16 10:42:49
	*/
	@RequestMapping("/list/pre")
	public String listpre(HttpServletRequest request, Model model,AuditLog auditlog) throws Exception{
		return list(request, model,auditlog);
	}
	
	/**
	* 进入auditlog Web页面后直接展现第一页数据
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-04-02 10:17:31
	*/
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,AuditLog auditLog) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<AuditLog> datas=auditlogService.findListDateByFinder(null,page,AuditLog.class,auditLog);
		model.addAttribute("page", page);
		model.addAttribute("datas",datas);
		model.addAttribute("auditlog",auditLog);
		return listurl;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,AuditLog auditLog) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = auditlogService.findDataExportExcel(null,listurl, page,AuditLog.class,auditlogService,auditLog);
		String fileName="auditlog"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	/**
	 * 查看操作
	 */
	@RequestMapping(value="/show")
	public String show(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  AuditLog auditLog = auditlogService.findAuditlogById(id);
		   model.addAttribute("auditlog",auditLog);
		}
		return "/testdb1/auditlog/auditlogCru";
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param auditLog
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version  2013-04-02 10:17:31
	*/
	@RequestMapping("/update")
	public String saveorupdate(Model model,AuditLog auditLog,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isBlank(auditLog.getId())){// 新增
				auditLog.setId(SecUtils.getUUID());
			try {
				auditlogService.saveAuditlog(auditLog);
				model.addAttribute(message, MessageUtils.ADD_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e);
			}
			     model.addAttribute(message, MessageUtils.ADD_FAIL);
			     return messageurl;
		} else {// 修改
			try {
				auditLog.setId(auditLog.getId());
				auditlogService.updateAuditlog(auditLog);
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
		   AuditLog auditLog = auditlogService.findAuditlogById(id);
		   model.addAttribute("auditlog",auditLog);
		}
		return "/testdb1/auditlog/auditlogCru";
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
	* @version  2013-04-02 10:17:31
	*/
	@RequestMapping("/delMulti")
	public @ResponseBody
	CFReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				auditlogService.deleteById(str,AuditLog.class);
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
