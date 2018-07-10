package org.springrain.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.Menu;
import org.springrain.system.entity.PermissionsLog;
import org.springrain.system.entity.Role;
import org.springrain.system.entity.RoleMenu;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserRole;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IMenuService;
import org.springrain.system.service.IPermissionsLogService;
import org.springrain.system.service.IRoleService;
import org.springrain.system.service.IUserOrgService;
import org.springrain.system.service.IUserRoleMenuService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:02:59
 * @see org.springrain.springrain.service.impl.Role
 */
@Service("roleService")
public class RoleServiceImpl extends BaseSpringrainServiceImpl implements IRoleService {
	@Resource
	private IUserRoleMenuService userRoleMenuService;
	// @Resource
	// private CacheManager shiroCacheManager;
	@Resource
	private IUserOrgService userOrgService;

	@Resource
	private IMenuService menuService;

	@Resource
	private IPermissionsLogService permissionsLogService;

	@Override
	public String saveRole(Role entity) throws Exception {
		return super.save(entity).toString();
	}

	@Override
	// @CacheEvict(value=GlobalStatic.qxCacheKey,allEntries=true)
	public String saveorupdateRole(Role role) throws Exception {
		super.cleanCache(GlobalStatic.qxCacheKey);
		// 更新 shiro 的权限缓存
		// shiroCacheManager.getCache(GlobalStatic.authorizationCacheName).clear();

		List<Menu> menus = role.getMenus();

		// ========== 权限日志相关START ==========
		Integer logActionType = null;
		Role newRole = role;
		Role oldRole = null;
		if (StringUtils.isBlank(role.getId())) {
			logActionType = Enumerations.PermissionsLogActionType.创建.getVal();
			newRole = role;
		} else {
			logActionType = Enumerations.PermissionsLogActionType.编辑.getVal();
			oldRole = super.findById(role.getId(), Role.class);
			oldRole.setRoleMenus(findRoleMenuByRoleId(role.getId()));
		}
		// ========== 权限日志相关END ==========

		String id = super.saveorupdate(role).toString();
		String _id = role.getId();
		if (StringUtils.isBlank(_id)) {
			_id = id;
		}

		Finder f_del = Finder.getDeleteFinder(RoleMenu.class).append(" WHERE roleId=:roleId ");
		f_del.setParam("roleId", _id);
		super.update(f_del);

		if (!CollectionUtils.isEmpty(menus)) {
			List<RoleMenu> rms = new ArrayList<>();
			for (Menu m : menus) {
				RoleMenu rm = new RoleMenu();
				rm.setRoleId(_id);
				rm.setMenuId(m.getId());
				rms.add(rm);
			}
			super.save(rms);

			newRole = role;
			newRole.setRoleMenus(rms);
		}

		// 保存角色变更日志
		permissionsLogService.saveUpdateRoleLog(newRole, oldRole, logActionType);

		// 修改角色清除权限缓存 2018年1月6日 14:58:39
		super.cleanCache(GlobalStatic.qxCacheKey);

		return id;
	}

	@Override
	public Integer updateRole(Role entity) throws Exception {
		return super.update(entity);
	}

	@Override
	public Role findRoleById(Object id) throws Exception {
		return super.findById(id, Role.class);
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
//			 return super.findListDataByFinder(finder,page,clazz,o);
		// 角色查询加权限
		Finder f = null;
		if (finder != null) {
			f = finder;
		} else {
			f = Finder.getSelectFinder(Role.class);
			f.append(" where 1=1 ");
		}
		Finder qxfinder = userOrgService.findOrgIdsSQLByManagerUserId(SessionUser.getUserId());
		if (StringUtils.isNotBlank(qxfinder.getSql())) {
			f.append(" and orgId in ( ").appendFinder(qxfinder).append(")");
		}
		return super.findListDataByFinder(f, page, clazz, o);
	}

	/**
	 * 根据查询列表的宏,导出Excel
	 * 
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page   分页对象
	 * @param clazz  要查询的对象
	 * @param o      querybean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl, Page page, Class<T> clazz, Object o)
			throws Exception {
		return super.findDataExportExcel(finder, ftlurl, page, clazz, o);
	}

	@Override
	public String findNameById(String roleId) throws Exception {
		Finder finder = Finder.getSelectFinder(Role.class, "name").append(" WHERE id=:id ");
		finder.setParam("id", roleId);
		String name = super.queryForObject(finder, String.class);
		return name;
	}

	@Override
	// @CacheEvict(value=GlobalStatic.qxCacheKey,allEntries=true)
	public String deleteRoleById(String roleId) throws Exception {
		super.cleanCache(GlobalStatic.qxCacheKey);

		if (StringUtils.isEmpty(roleId)) {
			return null;
		}

		// 被删除前的角色信息
		Role delRole = super.findById(roleId, Role.class);

		Finder finder_del_user = Finder.getDeleteFinder(UserRole.class).append(" where roleId=:roleId ")
				.setParam("roleId", roleId);
		super.update(finder_del_user);

		Finder finder_del_menu = Finder.getDeleteFinder(RoleMenu.class).append(" where roleId=:roleId ")
				.setParam("roleId", roleId);
		super.update(finder_del_menu);

		super.deleteById(roleId, Role.class);

		// 保存角色变更日志
		PermissionsLog dellog = new PermissionsLog(null, Enumerations.PermissionsLogActionType.删除.getVal(),
				Enumerations.PermissionsLogOperatorObjectType.角色.getVal(), delRole.getId(), delRole.getName(), null);
		permissionsLogService.savePermissionsLog(dellog);

		return null;
	}

	@Override
	public Integer findCountUserNumById(String roleId) throws Exception {
		Finder finder_userNum = new Finder(" select count(r.roleId) as countNum,r.roleId from ")
				.append(Finder.getTableName(UserRole.class)).append(" r ").append(",")
				.append(Finder.getTableName(User.class)).append(" u ")
				.append(" where r.userId = u.id and u.active =:active and r.roleId = :roleId group by r.roleId ")
				.setParam("active", Enumerations.ActiveState.可用.getState());
		finder_userNum.setParam("roleId", roleId);
		Map<String, Object> countUserNumMap = super.queryForObject(finder_userNum);
		if (countUserNumMap == null) {
			return 0;
		}
		Integer countNum = new Long((long) countUserNumMap.get("countNum")).intValue();
		return countNum;
	}

	/**
	 * 根据角色ID获取角色具有的与菜单的关联关系
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	private List<RoleMenu> findRoleMenuByRoleId(String roleId) throws Exception {
		Finder finder = Finder.getSelectFinder(RoleMenu.class).append(" where roleId = :roleId ").setParam("roleId",
				roleId);
		return super.queryForList(finder, RoleMenu.class);
	}
}
