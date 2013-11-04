package org.springrain.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springrain.demo.entity.Menu;
import org.springrain.demo.entity.Role;
import org.springrain.demo.entity.User;
import org.springrain.demo.service.BaseDemoServiceImpl;
import org.springrain.demo.service.IUserRoleMenuService;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 * @see org.springrain.demo.service.impl.UserRole
 */
@Service("userRoleMenuService")
public class UserRoleMenuServiceImpl extends BaseDemoServiceImpl implements
		IUserRoleMenuService {
	@Resource
	private CacheManager shiroCacheManager;

	@Override
	@Cacheable(value = GlobalStatic.qxCacheKey, key = "'findRoleByUserId_'+#userId")
	public List<Role> findRoleByUserId(String userId) throws Exception {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		Finder finder = new Finder(
				"SELECT r.* from t_role r,t_user_role  re where re.userId=:userId and re.roleId=r.id");
		finder.setParam("userId", userId);
		return super.queryForList(finder, Role.class);
	}
	@Override
	@Cacheable(value = GlobalStatic.qxCacheKey, key = "'getRolesAsString_'+#userId")
	public Set<String> getRolesAsString(String userId)throws Exception {
		List<Role> list = findRoleByUserId(userId);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		Set<String> set=new HashSet<String>();
		for(Role r:list){
			set.add(r.getCode());
		}
		return set;
	}
	@Override
	@Cacheable(value = GlobalStatic.qxCacheKey, key = "'getPermissionsAsString_'+#userId")
	public  Set<String> getPermissionsAsString(String userId) throws Exception {
		List<Menu> setMenu = findAllMenuByUserId(userId);
		if(CollectionUtils.isEmpty(setMenu)){
			return null;
		}
		Set<String> set=new HashSet<String>();
		for(Menu m:setMenu){
			if(StringUtils.isBlank(m.getPageurl())){
				continue;
			}
			set.add(m.getPageurl());
		}
		return set;
	}

	@Override
	@Cacheable(value = GlobalStatic.qxCacheKey, key = "'findMenuByRoleId_'+#roleId")
	public List<Menu> findMenuByRoleId(String roleId) throws Exception {
		if (StringUtils.isBlank(roleId)) {
			return null;
		}
		Finder finder = new Finder(
				"SELECT m.* from t_menu m,t_role_menu  re where re.roleId=:roleId and re.menuId=m.id  ");
		finder.setParam("roleId", roleId);
		return super.queryForList(finder, Menu.class);
	}

	@Override
	@Cacheable(value = GlobalStatic.qxCacheKey, key = "'findUserByRoleId_'+#roleId")
	public List<User> findUserByRoleId(String roleId) throws Exception {
		if (StringUtils.isBlank(roleId)) {
			return null;
		}

		Finder finder = new Finder(
				"SELECT u.* from t_user u,t_user_role  re where re.roleId=:roleId and re.userId=u.id");
		finder.setParam("roleId", roleId);
		return super.queryForList(finder, User.class);
	}

	@Override

	public List<Menu> findMenuByUserId(String userId) throws Exception {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		
		Finder finder = new Finder(
				"SELECT m.* from t_menu m,t_role_menu  rm,t_user_role  ur where ur.userId=:userId and ur.roleId=rm.roleId and m.id=rm.menuId  and m.type=1 and m.state=:state");
		finder.setParam("userId", userId).setParam("state", "是");
		return super.queryForList(finder, Menu.class);
	}
	private List<Menu> findAllMenuByUserId(String userId) throws Exception {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		
		List<Role> roles = findRoleByUserId(userId);
		if(CollectionUtils.isEmpty(roles)){
			return null;
		}
		List<Menu> list=new ArrayList<Menu>();
		for(Role role:roles){
			List<Menu> menus = findMenuByRoleId(role.getId());
			if(CollectionUtils.isEmpty(menus)){
				continue;
			}
			list.addAll(menus);
		}
		
		return list;

	}
	@Override
	@Cacheable(value = GlobalStatic.qxCacheKey, key = "'findRoleAndMenu_'+#roleId")
	public Role findRoleAndMenu(String roleId) throws Exception {
		if (StringUtils.isBlank(roleId)) {
			return null;
		}
		Role role = super.findById(roleId, Role.class);
		if (role == null) {
			return null;
		}
		List<Menu> menus = findMenuByRoleId(roleId);
		role.setMenus(menus);
		return role;
	}



	@Override
	public User findLoginUser(String account, String password) throws Exception {
		if (StringUtils.isBlank(account)) {
			return null;
		}
		Finder finder = new Finder(
				"SELECT * FROM t_user WHERE state=:state and account=:account ");
		finder.setParam("account", account).setParam("state", "是");
		if (StringUtils.isNotBlank(password)) {
			finder.append(" and password=:password ").setParam("password",
					password);
		}
		return super.queryForObject(finder, User.class);
	}

	@Override
	@Cacheable(value = GlobalStatic.qxCacheKey, key = "'findAllRoleAndMenu'")
	public List<Role> findAllRoleAndMenu() throws Exception {
		Finder f_role = new Finder("SELECT * FROM t_role where state=:state ");
		f_role.setParam("state", "是");
		List<Role> listRole = super.queryForList(f_role, Role.class);
		if (CollectionUtils.isEmpty(listRole)) {
			return null;
		}
		for (Role r : listRole) {
			List<Menu> menus = findMenuByRoleId(r.getId());
			r.setMenus(menus);
		}
		return listRole;
	}

	@Override
	public Menu findMenuAndLeaf(String menuId) throws Exception {
		if (StringUtils.isBlank(menuId)) {
			return null;
		}
		Menu menu = super.findById(menuId, Menu.class);
		addLeafMenu(menu);
		return menu;
	}
	
	
	private Menu addLeafMenu(Menu menu) throws Exception{
		if(menu==null){
			return null;
		}
		String id=menu.getId();
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		Finder finder=new Finder("SELECT * FROM t_menu where pid=:pid ");
		finder.setParam("pid", id);
		  List<Menu> list = super.queryForList(finder,Menu.class);
		if(CollectionUtils.isEmpty(list)){
			return menu;
		}
		menu.setLeaf(list);
		
		for(Menu m:list){
			addLeafMenu(m);
		}
		
		return menu;
	}
	
	@Override
	//@Caching(evict={@CacheEvict(value = GlobalStatic.qxCacheKey,key = "'findMenuByRoleId_'+#roleId"),@CacheEvict(value = GlobalStatic.qxCacheKey,key = "'findRoleAndMenu_'+#roleId"),@CacheEvict(value = GlobalStatic.qxCacheKey,key = "'findAllRoleAndMenu'")})
	@CacheEvict(value=GlobalStatic.qxCacheKey,allEntries=true)  
	public void updateRoleMenu(String roleId,String[] menus) throws Exception {
		//清理 shiro 权限缓存
		shiroCacheManager.getCache(GlobalStatic.authorizationCacheName).clear();
		
		//删除现在的中间权限表
				Finder finder=new Finder("delete from t_role_menu  where roleId=:roleId ");
				finder.setParam("roleId", roleId);
				this.update(finder);
				//新加权限
				finder=new Finder("insert into t_role_menu(id,roleId,menuId) values(:id,:roleId,:menuId)");
				for(String menuId:menus){
					finder.setParam("id", UUID.randomUUID().toString());
					finder.setParam("roleId", roleId);
					finder.setParam("menuId", menuId);
					this.update(finder);
				}
	}

}
