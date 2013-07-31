package  org.iu9.testdb1.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.iu9.frame.controller.BaseController;
import org.iu9.frame.shiro.ShiroUser;
import org.iu9.frame.util.Json;
import org.iu9.frame.util.MessageUtils;
import org.iu9.frame.util.SecUtils;
import org.iu9.testdb1.entity.Menu;
import org.iu9.testdb1.service.IMenuService;
import org.iu9.testdb1.service.IUserRoleMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 
 * @Title: MenuController.java
 * @Package org.iu9.testdb1.web
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
	
	private String listurl="/testdb1/menu/menuList";  //菜单列表路径
	
	private String cruurl="/testdb1/menu/menuCru";	  //菜单查看、修改、新增路径
	
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
	public String list(Model model) throws Exception{
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
				node.put("systemId", menu.getSystemId());
				nodes.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return nodes;
		}
		return nodes;
	}
	
	
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
	@RequestMapping("/update")
	@ResponseBody
	public Json saveorupdate(Menu entity) throws Exception{
		Json json=new Json();
		if(StringUtils.isBlank(entity.getId())){// 新增
			entity.setId(SecUtils.getUUID());
			try {
				menuService.saveMenu(entity);
				json.setSuccess(true);
				json.setMessage(MessageUtils.ADD_SUCCESS);
				return json;
			} catch (Exception e) {
				logger.error(e);
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
				logger.error(e);
			}
			json.setSuccess(false);
			json.setMessage(MessageUtils.EDIT_WARING);
			return json;
		}
		
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
	public Json delMultiRecords(@RequestParam("records") String records) {
		Json info =new Json();
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				menuService.deleteById(str,Menu.class);
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
	
	
	
}