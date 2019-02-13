package org.springrain.system.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.system.manager.entity.Menu;
import org.springrain.system.manager.entity.PermissionsLog;
import org.springrain.system.manager.entity.Role;
import org.springrain.system.manager.entity.RoleMenu;
import org.springrain.system.manager.entity.User;
import org.springrain.system.manager.entity.UserRole;
import org.springrain.system.manager.service.IMenuService;
import org.springrain.system.manager.service.IPermissionsLogService;
import org.springrain.system.manager.service.IRoleService;
import org.springrain.system.manager.service.IUserRoleMenuService;
import org.springrain.system.manager.service.IUserService;

/**
 * 权限日志
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-12-15 12:13:15
 * @see org.springrain.system.service.impl.PermissionsLog
 */
@Service("permissionsLogService")
public class PermissionsLogServiceImpl extends BaseSpringrainServiceImpl implements IPermissionsLogService {

	@Resource
	private IRoleService roleService;

	@Resource
	private IMenuService menuService;

	@Resource
	private IUserService userService;

	@Resource
	private IUserRoleMenuService userRoleMenuService;

	@Override
	public String save(Object entity) throws Exception {
		PermissionsLog permissionsLog = (PermissionsLog) entity;
		return super.save(permissionsLog).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		PermissionsLog permissionsLog = (PermissionsLog) entity;
		return super.saveorupdate(permissionsLog).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		PermissionsLog permissionsLog = (PermissionsLog) entity;
		return super.update(permissionsLog);
	}

	@Override
	public PermissionsLog findPermissionsLogById(Object id) throws Exception {
		return super.findById(id, PermissionsLog.class);
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz, Object o) throws Exception {
		return super.findListDataByFinder(finder, page, clazz, o);
	}

	@Override
	public String savePermissionsLog(PermissionsLog log) throws Exception {
		if (log == null) {
			return null;
		}

		if (StringUtils.isBlank(log.getOperatorUserId())) {
			String operatorUserId = SessionUser.getUserId();
			User operatorUser = userService.findById(operatorUserId, User.class);
			log.setOperatorUserName(operatorUser.getName());
			log.setOperatorUserRoles(findRoleNamesByUserId(operatorUser.getId()));
		}

		if (StringUtils.isBlank(log.getCreateUserId())) {
			log.setCreateUserId(SessionUser.getUserId());
		}
		if (log.getCreateTime() == null) {
			log.setCreateTime(new Date());
		}

		if (StringUtils.isBlank(log.getId())) {
			super.save(log);
		} else {
			super.update(log, true);
		}
		return ReturnDatas.SUCCESS;
	}

	@Override
	public String saveUpdateRoleLog(Role newRole, Role oldRole, Integer actionType) throws Exception {
		if (Enumerations.PermissionsLogActionType.getActionType(actionType) == null || newRole == null) {
			return null;
		}

		String siteId = null;
		String operatorUserId = SessionUser.getUserId();
		String operatorUserName = SessionUser.getUserName();
		String operatorUserRoles = findRoleNamesByUserId(operatorUserId);
		int operatorObjectType = Enumerations.PermissionsLogOperatorObjectType.角色.getVal();
		String operatorObjectId = newRole.getId();
		String operatorObjectName = null;
		String actionContent = "";
		String createUserId = SessionUser.getUserId();
		Date createTime = new Date();

		if (actionType.intValue() == Enumerations.PermissionsLogActionType.创建.getVal()) {
			operatorObjectName = newRole.getName();

			StringBuffer sb = new StringBuffer();
//			sb.append("增加权限：" + getMenuInfosByRoleMenus(newRole.getRoleMenus()));
			sb.append(getMenuInfosByRoleMenus(newRole.getRoleMenus()));
			sb.append("<contenttsplit>");

			actionContent = sb.toString();
		} else if (actionType.intValue() == Enumerations.PermissionsLogActionType.编辑.getVal()) {
			operatorObjectName = oldRole.getName();

			StringBuffer sb = new StringBuffer();
			if (!newRole.getName().equals(oldRole.getName())) {
//				sb.append("名称变更：" + oldRole.getName() + " -> " + newRole.getName());
				sb.append(oldRole.getName() + " -> " + newRole.getName());
			}
			sb.append("<contenttsplit>");

			// 新增的
			List<RoleMenu> addRoleMenus = getAddRoleMenu(newRole.getRoleMenus(), oldRole.getRoleMenus());
			if (addRoleMenus != null && !addRoleMenus.isEmpty()) {
//				sb.append("增加权限：").append(getMenuInfosByRoleMenus(addRoleMenus));
				sb.append(getMenuInfosByRoleMenus(addRoleMenus));
			}
			sb.append("<contenttsplit>");

			// 删除的
			List<RoleMenu> delRoleMenus = getDelRoleMenu(newRole.getRoleMenus(), oldRole.getRoleMenus());
			if (delRoleMenus != null && !delRoleMenus.isEmpty()) {
//				sb.append("减少权限：").append(getMenuInfosByRoleMenus(delRoleMenus));
				sb.append(getMenuInfosByRoleMenus(delRoleMenus));
			}

			actionContent = sb.toString();
		}

		if (StringUtils.isBlank(actionContent)) {
			return null;
		}

		PermissionsLog log = new PermissionsLog(siteId, actionType, operatorUserId, operatorUserName, operatorUserRoles,
				operatorObjectType, operatorObjectId, operatorObjectName, actionContent, createUserId, createTime);

		return this.savePermissionsLog(log);
	}

	/**
	 * 根据用户ID获取用户拥有的角色名称的拼接，已英文逗号分割
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private String findRoleNamesByUserId(String userId) throws Exception {
		if (StringUtils.isBlank(userId)) {
			return null;
		}

		List<Role> roles = userRoleMenuService.findRoleByUserId(userId);
		if (roles == null || roles.isEmpty()) {
			return null;
		}

		String roleNames = "";
		for (Role role : roles) {
			roleNames += role.getName() + ",";
		}
		if (StringUtils.isBlank(roleNames)) {
			return null;
		}

		roleNames = roleNames.substring(0, roleNames.length() - 1);
		return roleNames;
	}

	/**
	 * 根据角色菜单关联关系获取菜单描述信息
	 * 
	 * @param roleMenus
	 * @return
	 * @throws Exception
	 */
	private String getMenuInfosByRoleMenus(List<RoleMenu> roleMenus) throws Exception {
		if (roleMenus == null || roleMenus.isEmpty()) {
			return "";
		}

		List<String> menuIds = new ArrayList<String>();
		for (RoleMenu roleMenu : roleMenus) {
			menuIds.add(roleMenu.getMenuId());
		}

		Finder finder_menu = Finder.getSelectFinder(Menu.class).append(" where id in (:ids) ").setParam("ids", menuIds);
		List<Menu> menuList = super.queryForList(finder_menu, Menu.class);

		// 补全数据
		Finder finder_allmenu = new Finder("select m1.* from ").append(Finder.getTableName(Menu.class)).append(" m1 ")
				.append(",").append(Finder.getTableName(RoleMenu.class)).append(" rm ").append(",")
				.append(Finder.getTableName(UserRole.class)).append(" ur ")
				.append(" where m1.id = rm.menuId and rm.roleId = ur.roleId and ur.userId = :userId ")
				.setParam("userId", SessionUser.getUserId());
		List<Menu> allMenus = super.queryForList(finder_allmenu, Menu.class);
		List<Menu> pmenuListAll = new ArrayList<Menu>();
		for (Menu menu : menuList) {
			List<Menu> pmenuList = menuService.findAllParentByChildIdFromAll(menu.getPid(), allMenus);
			if (pmenuList == null || pmenuList.isEmpty()) {
				continue;
			}
			for (Menu pmenu : pmenuList) {
				boolean has = false;
				for (Menu pmenu2 : pmenuListAll) {
					if (pmenu2.getId().equals(pmenu.getId())) {
						has = true;
						break;
					}
				}
				if (!has) {
					pmenuListAll.add(pmenu);
				}
			}
		}

		menuList.addAll(pmenuListAll);
		menuList = setLeaf(menuList);

		String res = getMenuInfos(menuList, null);

		// 去重
		if (StringUtils.isNotBlank(res)) {
			String[] resArr = res.split("<br>");
			if (resArr != null && resArr.length > 1) {
				List<String> resList = new ArrayList<String>();
				for (String resone : resArr) {
					if (!resList.contains(resone)) {
						resList.add(resone);
					}
				}
				if (resList.size() > 0) {
					res = "";
					for (String resone : resList) {
						res += resone + "<br>";
					}
					if (StringUtils.isNotBlank(res)) {
						res = res.substring(0, res.length() - 4);
					}
				}
			}
		}

		return res;
	}

	/**
	 * 给一个总集合中的元素设置成树结构
	 * 
	 * @param menuList
	 * @return
	 * @throws Exception
	 */
	private List<Menu> setLeaf(List<Menu> menuList) throws Exception {
		List<Menu> res = new ArrayList<Menu>();
		for (Menu menu1 : menuList) {
			List<Menu> leafs = new ArrayList<Menu>();
			for (Menu menu2 : menuList) {
				if (menu1.getId().equals(menu2.getPid())) {
					leafs.add(menu2);
				}
			}
			menu1.setLeaf(leafs);
		}

		for (Menu menu1 : menuList) {
			boolean isParent = true;
			for (Menu menu2 : menuList) {
				if (menu2.getId().equals(menu1.getPid())) {
					isParent = false;
					break;
				}
			}
			if (isParent) {
				res.add(menu1);
			}
		}

		return res;
	}

	/**
	 * 获取拼接信息
	 * 
	 * @param menuList
	 * @return
	 * @throws Exception
	 */
	private String getMenuInfos(List<Menu> menuList, String pName) throws Exception {
		if (menuList == null || menuList.isEmpty()) {
			return "";
		}
		String res = "";
		String leafPname = "";
		for (Menu menu : menuList) {
			if (StringUtils.isBlank(pName)) {
				leafPname = menu.getName();
			} else {
				leafPname = pName + " - " + menu.getName();
			}
			if (menu.getLeaf() == null || menu.getLeaf().isEmpty()) {
				res += leafPname + "<br>";
				continue;
			}
			res += getMenuInfos(menu.getLeaf(), leafPname);
		}
		return res;
	}

	/**
	 * 比较获得新增的
	 * 
	 * @param newRoleMenuList
	 * @param oldRoleMenuList
	 * @return
	 */
	private List<RoleMenu> getAddRoleMenu(List<RoleMenu> newRoleMenuList, List<RoleMenu> oldRoleMenuList) {
		List<RoleMenu> addRoleMenuList = new ArrayList<RoleMenu>();
		if (newRoleMenuList == null || newRoleMenuList.isEmpty()) {
			return null;
		}
		// 新增的
		for (RoleMenu newRoleMenu : newRoleMenuList) {
			boolean isNew = true;
			for (RoleMenu oldRoleMenu : oldRoleMenuList) {
				if (newRoleMenu.getMenuId().equals(oldRoleMenu.getMenuId())) {
					isNew = false;
					break;
				}
			}
			if (isNew) {
				addRoleMenuList.add(newRoleMenu);
			}
		}

		return addRoleMenuList;
	}

	/**
	 * 比较获得被删除的
	 * 
	 * @param newRoleMenuList
	 * @param oldRoleMenuList
	 * @return
	 */
	private List<RoleMenu> getDelRoleMenu(List<RoleMenu> newRoleMenuList, List<RoleMenu> oldRoleMenuList) {
		List<RoleMenu> delRoleMenuList = new ArrayList<RoleMenu>();
		if (oldRoleMenuList == null || oldRoleMenuList.isEmpty()) {
			return null;
		}
		// 被删除的
		for (RoleMenu oldRoleMenu : oldRoleMenuList) {
			boolean isDel = true;
			if (newRoleMenuList != null && !newRoleMenuList.isEmpty()) {
				for (RoleMenu newRoleMenu : newRoleMenuList) {
					if (newRoleMenu.getMenuId().equals(oldRoleMenu.getMenuId())) {
						isDel = false;
						break;
					}
				}
			}
			if (isDel) {
				delRoleMenuList.add(oldRoleMenu);
			}
		}

		return delRoleMenuList;
	}

	@Override
	public String saveUserLog(String userId, int actionType) throws Exception {
		if (StringUtils.isBlank(userId) || Enumerations.PermissionsLogActionType.getActionType(actionType) == null) {
			return null;
		}

		User user = super.findById(userId, User.class);
		String siteId = "default";
		int operatorObjectType = Enumerations.PermissionsLogOperatorObjectType.用户.getVal();
		String operatorObjectId = user.getId();
		String operatorObjectName = user.getName();
		String actionContent = "";

		// 从属角色
		List<Role> roleList = userRoleMenuService.findRoleByUserId(user.getId());
		if (roleList != null && !roleList.isEmpty()) {
			String split = ",";
			for (Role role : roleList) {
				actionContent += role.getName() + split;
			}
			if (StringUtils.isNotBlank(actionContent)) {
				actionContent = actionContent.substring(0, actionContent.length() - split.length());
			}
		}

		PermissionsLog log = new PermissionsLog(siteId, actionType, operatorObjectType, operatorObjectId,
				operatorObjectName, actionContent);

		return this.savePermissionsLog(log);
	}

}
