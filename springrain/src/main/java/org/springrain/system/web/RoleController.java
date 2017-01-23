package  org.springrain.system.web;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.entity.Menu;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.Role;
import org.springrain.system.service.IOrgService;
import org.springrain.system.service.IRoleService;
import org.springrain.system.service.IUserRoleMenuService;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author weicms.net<Auto generate>
 * @version  2013-07-29 11:36:46
 * @see org.springrain.springrain.web.Role
 */
@Controller
@RequestMapping(value="/system/role")
public class RoleController  extends BaseController {
	@Resource
	private IRoleService roleService;
	@Resource
	private IUserRoleMenuService userRoleMenuService;
	@Resource
	private IOrgService orgService;
	
	private String listurl="/system/role/roleList";
	

	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, Role role) throws Exception {
		ReturnDatas returnObject = listjson(request, model, role);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model, Role role) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
	
		Page page = newPage(request);
	
		List<Role> datas =roleService.findListDataByFinder(null, page, Role.class, role);
		returnObject.setQueryBean(role);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	

	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/role/roleLook";
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			Role role = userRoleMenuService.findRoleAndMenu(id);
			if(role!=null&&StringUtils.isNotBlank(role.getPid())){
				Org org=orgService.findById(role.getPid(),Org.class); 
				role.setPname(org.getName()); 
			}
			returnObject.setData(role);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}

		return returnObject;

	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody ReturnDatas saveorupdate(Role role, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String id=role.getId();
			if(StringUtils.isBlank(id)){
				role.setId(null);
			}
			
			String str_menuIds=request.getParameter("menuIds");
			if(StringUtils.isBlank(str_menuIds)){
				role.setMenus(null);
			}else{
				String[] menuIds=str_menuIds.split(",");
				if(menuIds!=null&&menuIds.length>0){
					List<Menu> menus=new ArrayList<Menu>();
					for(String s:menuIds){
						if(StringUtils.isBlank(s)){
							continue;
						}
						Menu m=new Menu();
						m.setId(s);
						menus.add(m);
					}
					
					role.setMenus(menus);
					
				}
				
				
			}
			
			roleService.saveorupdateRole(role);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}

	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String edit(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/role/roleCru";
	}

	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody
	ReturnDatas destroy(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
			java.lang.String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				roleService.deleteRoleById(id);
				return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	@RequestMapping(value = "/ajax/select2")
	public @ResponseBody List<Role> ajaxUser(HttpServletRequest request) throws Exception {
		String key=request.getParameter("q");
		Page page=new Page();
		page.setPageIndex(1);
		
		Finder finder=Finder.getSelectFinder(Role.class, "id,name").append(" WHERE roleType=1 and name like :name order by name asc ");
		finder.setParam("name", key+"%");
		return roleService.queryForList(finder,Role.class, page);
		
	}

	
	

}
