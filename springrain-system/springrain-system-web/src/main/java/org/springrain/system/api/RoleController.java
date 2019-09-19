package org.springrain.system.api;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.Menu;
import org.springrain.system.entity.Role;
import org.springrain.system.entity.RoleMenu;
import org.springrain.system.entity.RoleOrg;
import org.springrain.system.service.IRoleService;
import org.springrain.system.service.IUserRoleMenuService;
import org.springrain.system.service.IUserRoleOrgService;
import org.springrain.rpc.sessionuser.SessionUser;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2019-07-27 16:10:16
 */
@RestController
@RequestMapping(value="/api/system/role", method = RequestMethod.POST)
public class RoleController  extends BaseController {
	@Resource
	private IRoleService roleService;

	@Resource
	private IUserRoleMenuService userRoleMenuService;

	@Resource
	private IUserRoleOrgService userRoleOrgService;



	/**
	 * 列表数据
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lists", method = RequestMethod.POST)
	public ReturnDatas<Role> lists(@RequestBody Page<Role> page)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		// Page page = newPage(request);
		// ==执行分页查询
		List<Role> datas=roleService.findListDataByFinder(null,page,Role.class,page.getData());
			returnObject.setQueryBean(page.getData());
		returnObject.setPage(page);
		returnObject.setResult(datas);
		return returnObject;
	}


	/**
	 * 列表数据
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ReturnDatas<Role> list()
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		// Page page = newPage(request);
 		List<Role> roleList = roleService.findListDataByFinder(null,null,Role.class,null);
		returnObject.setResult(roleList);
		return returnObject;
	}



	/**
	 * 查看的Json格式数据
	 */
	@RequestMapping(value = "/look", method = RequestMethod.POST)   
	public ReturnDatas<Role> look(String id) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		if(StringUtils.isNotBlank(id)){
		  Role role = roleService.findRoleById(id);
		   returnObject.setResult(role);
		}else{
		   returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	/**
	 * 保存 操作,返回json格式数据
	 * 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)    
	public ReturnDatas<Role> save(@RequestBody Role role) {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
		try {
		
			String id =role.getId();
			if(StringUtils.isBlank(id)){
			  role.setId(null);
			}

			role.setCreateUserId(SessionUser.getUserId());
			role.setCreateTime(new Date());

			role.setPrivateOrg(0);
			role.setRoleOrgType(0);
			role.setShareRole(0);
			role.setOrgId("o_10001");
			role.setUpdateTime(new Date());
			role.setSortno(0);
			roleService.save(role);

			returnObject.setResult(role);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.SAVE_ERROR);
		}
		return returnObject;
	
	}

	@RequestMapping(value = "/getMenusByRoleId", method = RequestMethod.POST)
	public ReturnDatas  findMenuByRoleId(@RequestBody Map map) throws Exception {
		String roleid = map.get("roleid").toString();
		ReturnDatas returnDatas=ReturnDatas.getSuccessReturnDatas();
		  List<Menu> menus = userRoleMenuService.findMenuByRoleId(roleid);
		ConcurrentMap resutltMap = Maps.newConcurrentMap();
		List<Map<String,Object>> listMap=new ArrayList<>();
		userRoleMenuService.wrapVueMenu(menus,listMap);
		resutltMap.put("menus",listMap);
		returnDatas.setResult(resutltMap);
		return returnDatas;
	}
		
	
	/**
	 * 修改 操作,返回json格式数据
	 * 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)    
	public ReturnDatas<Role> update(@RequestBody Role role) {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			String id =role.getId();
			if(StringUtils.isBlank(id)){
			   return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
			}
			roleService.update(role);

			returnObject.setResult(role);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)   
	public  ReturnDatas<Role> delete(@RequestBody String id) throws Exception {
			// 执行删除
		try {
			
		    if(StringUtils.isNotBlank(id)){
			    roleService.deleteById(id,Role.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_ERROR);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
	}

	/**
	 * 更新 角色 菜单
	 */
	@RequestMapping(value = "/updaterolemenu", method = RequestMethod.POST)
	public  ReturnDatas<String> updaterolemenu(@RequestBody RoleMenu roleMenu) throws Exception {
		String str= userRoleMenuService.updateRoleMenu(roleMenu);
		if(StringUtils.isBlank(str)){
			return ReturnDatas.getSuccessReturnDatas();
		}else{
			return ReturnDatas.getErrorReturnDatas(str);
		}
	}

	/**
	 * 更新 角色 部门
	 */
	@RequestMapping(value = "/updateRoleOrg", method = RequestMethod.POST)
	public  ReturnDatas<String> updateRoleOrg(@RequestBody RoleOrg roleOrg) throws Exception {
		String str= userRoleOrgService.updateRoleOrg(roleOrg);
		if(StringUtils.isBlank(str)){
			return ReturnDatas.getSuccessReturnDatas();
		}else{
			return ReturnDatas.getErrorReturnDatas(str);
		}
	}


	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping(value = "/delete/more", method = RequestMethod.POST)
	public ReturnDatas deleteMore(@RequestBody String[] ids) {

		if (ids == null || ids.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_ERROR);
		}
		try {
			List<String> listIds = Arrays.asList(ids);
			roleService.deleteByIds(listIds,Role.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_ERROR);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
			
	}

}
