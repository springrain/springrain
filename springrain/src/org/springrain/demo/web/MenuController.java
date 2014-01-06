package  org.springrain.demo.web;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springrain.demo.dto.MenuTreeJSON;
import org.springrain.demo.entity.Menu;
import org.springrain.demo.service.IMenuService;
import org.springrain.demo.service.IUserRoleMenuService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.shiro.ShiroUser;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;



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
@RequestMapping(value="/menu")
public class MenuController  extends BaseController {
	
	@Resource
	private IMenuService menuService;
	
	@Resource
	private IUserRoleMenuService userRoleMenuService;
	
	private String listurl="/demo/menu/menuList";  //菜单列表路径
	
	private String cruurl="/demo/menu/menuCru";	  //菜单查看、修改、新增路径
	
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
	public String list(HttpServletRequest request, Model model,Menu menu) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Menu> datas=menuService.findListDataByFinder(null,page,Menu.class,menu);
		model.addAttribute("page", page);
		model.addAttribute("datas",datas);
		model.addAttribute("fwlog",menu);
		return listurl;
	}
	/**
	 * 
	 * @Title: listpre
	 * @Description: 数据列表分页请求
	 * @param user
	 * @return
	 * @throws Exception
	 * @return Json
	 * @throws
	 */
	@RequestMapping("/list/pre")
	@ResponseBody
	public List<Map<String,Object>> listpre(@RequestParam(value="id" ,required=false) String id) throws Exception{
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		try {
			List<Menu> listMenu= menuService.findListById(id);
			if(CollectionUtils.isEmpty(listMenu)){
				return null;
			}
			for (Menu menu : listMenu) {
				Map<String,Object> node = new HashMap<String, Object>();
				node.put("id", menu.getId());
				if(!StringUtils.isBlank(menu.getPid())){
					node.put("pid", menu.getPid());
				}else{
					node.put("pid", "");
				}
				node.put("name", menu.getName());
				if(menu.getLeaf()!=null && menu.getLeaf().size()>0){
					node.put("state", "closed");
				}
				node.put("type", menu.getType());
				node.put("pageurl", menu.getPageurl());
				node.put("description", menu.getDescription());
//				node.put("systemId", menu.getSystemId());
				nodes.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return nodes;
		}
		return nodes;
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
		if(StringUtils.isBlank(menu.getId())){// 新增
				menu.setId(SecUtils.getUUID());
			try {
				menuService.save(menu);
				model.addAttribute(message, MessageUtils.ADD_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			     model.addAttribute(message, MessageUtils.ADD_FAIL);
			     return messageurl;
		} else {// 修改
			try {
				menu.setId(menu.getId());
				menuService.update(menu);
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
		   Menu menu = menuService.findMenuById(id);
		   Finder finder=new Finder("select name from t_menu where id=:id");
		   finder.setParam("id", menu.getPid());
		   String pidName=menuService.queryForObject(finder, String.class);
		   menu.setPidName(pidName);
		   model.addAttribute("menu",menu);
		   
		}
		return "/demo/menu/menuCru";
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
		try {
			List<String> ids = Arrays.asList(rs);
			menuService.deleteByIds(ids, Menu.class);
			}
			catch (Exception e) {
					info.setSuccess(false);
					info.setMessage(MessageUtils.DELETE_ALL_FAIL);
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