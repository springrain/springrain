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

import org.springrain.demo.entity.Role;
import org.springrain.demo.service.IRoleService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.CFReturnObject;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:46
 * @see org.springrain.demo.web.Role
 */
@Controller
@RequestMapping(value="/role")
public class RoleController  extends BaseController {
	@Resource
	private IRoleService roleService;
	
	private String listurl="/demo/role/roleList";
	
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
	* 进入role Web页面后直接展现第一页数据
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-29 11:36:46
	*/
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Role role) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Role> datas=roleService.findListDataByFinder(null,page,Role.class,role);
		model.addAttribute("page", page);
		model.addAttribute("datas",datas);
		model.addAttribute("role",role);
		return listurl;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Role role) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = roleService.findDataExportExcel(null,listurl, page,Role.class,role);
		String fileName="role"+GlobalStatic.excelext;
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
		
		  Role role = roleService.findRoleById(id);
		   model.addAttribute("role",role);
		}
		return "/demo/role/roleLook";
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param role
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-29 11:36:46
	*/
	@RequestMapping("/update")
	public String saveorupdate(Model model,Role role,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isBlank(role.getId())){// 新增
				role.setId(SecUtils.getUUID());
			try {
				roleService.save(role);
				model.addAttribute(message, MessageUtils.ADD_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			     model.addAttribute(message, MessageUtils.ADD_FAIL);
			     return messageurl;
		} else {// 修改
			try {
				role.setId(role.getId());
				roleService.update(role);
				model.addAttribute(message, MessageUtils.EDIT_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
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
		   Role role = roleService.findRoleById(id);
		   model.addAttribute("role",role);
		}
		return "/demo/role/roleCru";
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
			roleService.deleteById(id,Role.class);
			return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
			}else{
			    return new CFReturnObject(CFReturnObject.WARNING, MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
				logger.error(e.getMessage(),e);
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
	* @version  2013-07-29 11:36:46
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
			roleService.deleteByIds(ids, Role.class);
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
	/**
	 * 返回级别的json数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajax/find_role_json")
	public @ResponseBody List<Role> findAjaxJSON()throws Exception{
		Finder finder=new Finder("select * from t_role where state=:state ");
		finder.setParam("state", "是");
		List<Role> grades=roleService.queryForList(finder, Role.class);
		return grades;
	}
}
