package  org.springrain.demo.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.demo.dto.LimtDto;
import org.springrain.demo.dto.MenuTreeJSON;
import org.springrain.demo.dto.MenuZTreeJSON;
import org.springrain.demo.entity.Menu;
import org.springrain.demo.entity.Role;
import org.springrain.demo.entity.RoleMenu;
import org.springrain.demo.service.IUserRoleMenuService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.shiro.ShiroUser;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.Page;



/**
 * 
 * @Title: MenuController.java
 * @Package org.springrain.demo.web
 * @Description: 
 * @author ZhangNan
 * @date 2013-7-11 下午9:36:31
 * @version V1.0
 *
 */
@Controller
@RequestMapping(value="/limit")
public class LimtDistributeController  extends BaseController {
	

	
	@Resource
	private IUserRoleMenuService userRoleMenuService;
	
	private String listurl="/demo/limit/limitList";  //菜单列表路径
	
	private String cruurl="/demo/limit/limitCru";	  //菜单查看、修改、新增路径
	
	/**
	 * 
	 * @Title: list
	 * @Description: 返回页面
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/list")
	public String list(Model model,Menu menu) throws Exception{
		List<LimtDto> datas=new ArrayList<LimtDto>();
		Finder finder=new Finder("select * from t_role");
		List<Map<String, Object>> lists=userRoleMenuService.queryForList(finder);
		LimtDto dto=null;
		for(Map<String, Object> map:lists){
			dto=new LimtDto();
			String roleId=map.get("id").toString();
			String roleName=map.get("name").toString();
			dto.setRoleId(roleId);
			dto.setRoleName(roleName);
			datas.add(dto);
		}
		model.addAttribute("datas", datas);
		return listurl;
	}
	
	/**easyui***update***/
	
	/**
	 * 
	 * @Title: saveorupdate
	 * @Description: 新增/修改save操作
	 * @param entity
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 */
	/*
	@RequestMapping("/update")
	@ResponseBody
	public MenuTreeJSON saveorupdate(Menu entity) throws Exception{
		MenuTreeJSON json=new MenuTreeJSON();
		if(StringUtils.isBlank(entity.getId())){// 新增
			entity.setId(SecUtils.getUUID());
			try {
				menuService.saveMenu(entity);
				json.setSuccess(true);
				json.setMessage(MessageUtils.ADD_SUCCESS);
				return json;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			json.setSuccess(false);
			json.setMessage(MessageUtils.ADD_FAIL);
			return json;
		} else {// 修改
			try {
				entity.setId(entity.getId());
				menuService.updateMenu(entity);
				json.setSuccess(true);
				json.setMessage(MessageUtils.EDIT_SUCCESS);
				return json;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			json.setSuccess(false);
			json.setMessage(MessageUtils.EDIT_WARING);
			return json;
		}
		
	}
	*/
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param menu
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-29 11:36:45
	*/
	@RequestMapping("/update")
	public String saveorupdate(Model model,Menu menu,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String roleId=request.getParameter("roleId");
		String roleMenuIds=request.getParameter("roleMenuIds");
		String[] menus=null;
		if(StringUtils.isBlank(roleId)){
			model.addAttribute(message, "修改失败,没这个岗位!");
			return messageurl;
		}
		if(StringUtils.isNotBlank(roleMenuIds)){
			menus=roleMenuIds.split(",");
		}
		try {
			userRoleMenuService.updateRoleMenu(roleId, menus);
			model.addAttribute(message, "权限修改成功!");
		} catch (Exception e) {
			model.addAttribute(message, MessageUtils.EDIT_WARING);
		}
		return messageurl;
	}
	
	/**
	 * 进入修改页面
	 */
	@RequestMapping(value="/update/pre")
	public String edit(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			Finder finder=new Finder("select * from t_menu where state=:state");
			finder.setParam("state", "是");
			List<Menu> menus=userRoleMenuService.queryForList(finder, Menu.class);
			model.addAttribute("menus", menus);
			
			
		   finder=new Finder("select * from t_role_menu rm where rm.roleId=:roleId");
		   finder.setParam("roleId", id);
		   List<RoleMenu> roleMenus=userRoleMenuService.queryForList(finder, RoleMenu.class);
		   Role role = userRoleMenuService.findById(id, Role.class);
		   model.addAttribute("roleMenus", roleMenus);
		   model.addAttribute("role", role);
		}
		return "/demo/limit/limitCru";
	}
	@RequestMapping(value="/ajax/menu_json")
	public @ResponseBody List<MenuZTreeJSON> findMenuZTreeJSONs()throws Exception{
		Finder finder=new Finder("select * from t_menu where state=:state ");
		finder.setParam("state", "是");
		List<Menu> menus=userRoleMenuService.queryForList(finder, Menu.class);
		List<MenuZTreeJSON> datas=null;
		if(CollectionUtils.isEmpty(menus)){
			return null;
		}
		datas=new ArrayList<MenuZTreeJSON>();
		MenuZTreeJSON e=null;
		for(Menu menu:menus){
			e=new MenuZTreeJSON();
			e.setId(menu.getId());
			e.setText(menu.getName());
			e.setMenuType(menu.getType());
			String url=menu.getPageurl();
			e.setMenuUrl(url);
			e.setPid(menu.getPid());
			datas.add(e);
		}
		return datas;
	}
	/**
	 * 删除多条记录
	 * @Title: delMultiRecords
	 * @Description: 
	 * @param records
	 * @return
	 * @return Json
	 * @throws
	 */
	@RequestMapping("/delMulti")
	@ResponseBody
	public MenuTreeJSON delMultiRecords(@RequestParam("records") String records) {
		MenuTreeJSON info =new MenuTreeJSON();
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				userRoleMenuService.deleteById(str,Menu.class);
			} catch (Exception e) {
				e.printStackTrace();
				if (i > 0) {
					info.setSuccess(true);
					info.setMessage(MessageUtils.DELETE_ALL_WARNING);
					return info;
				}
				info.setSuccess(false);
				info.setMessage(MessageUtils.DELETE_ALL_FAIL);
				return info;
			}
			i++;
		}
		info.setSuccess(true);
		info.setMessage(MessageUtils.DELETE_ALL_SUCCESS);
		return info;
	}
	
	/**
	 * 
	 * @Title: show
	 * @Description: 添加与修改页面跳转
	 * @param model
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 */
	@RequestMapping("/show")
	public String show(Model model) throws Exception{
		return cruurl;
	}
	
	/**
	 * 
	 * @Title: menu
	 * @Description: 菜单列表
	 * @return
	 * @return Map
	 * @throws
	 */
	@RequestMapping("/leftMenu")
	@ResponseBody
	public List<Map<String,Object>> leftMenu(){
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		//获取当前登录人
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(user == null || user.getId().equals("") ){
			return null;
		}
		try {
			List<Menu> listMenu=userRoleMenuService.findMenuByUserId(user.getId());
			for (Menu menu : listMenu) {
				Map<String,Object> node = new HashMap<String, Object>();
				Map<String,Object> attributes=new HashMap<String, Object>();
				node.put("id", menu.getId());
				if(!StringUtils.isBlank(menu.getPid())){
					node.put("pid", menu.getPid());
				}else{
					node.put("pid", "");
				}
				node.put("text", menu.getName());
				if(!StringUtils.isBlank(menu.getPageurl())){
					attributes.put("url",menu.getPageurl());
				}else{
					attributes.put("url","");
				}
				node.put("attributes", attributes);
				nodes.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
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