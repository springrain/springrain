package org.iu9.testdb1.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.iu9.frame.util.Finder;
import org.iu9.testdb1.entity.Menu;
import org.iu9.testdb1.entity.Role;
import org.iu9.testdb1.entity.User;
import org.iu9.testdb1.service.BaseTestdb1ServiceImpl;
import org.iu9.testdb1.service.IUserRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.iu9.testdb1.service.impl.UserRole
 */
@Service("userRoleMenuService")
public class UserRoleMenuServiceImpl extends BaseTestdb1ServiceImpl implements IUserRoleMenuService {

	@Override
	public List<Role> findRoleByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		
		Finder finder=new Finder("SELECT r.* from t_role r,t_user_role  re where re.userId=:userId and re.roleId=r.id");
		finder.setParam("userId", userId);
		return super.queryForList(finder, Role.class);
	}

	@Override
	public List<Menu> findMenuByRoleId(String roleId) throws Exception {
		if(StringUtils.isBlank(roleId)){
			return null;
		}
		Finder finder=new Finder("SELECT m.* from t_menu m,t_role_menu  re where re.roleId=:roleId and re.menuId=m.id  ");
		finder.setParam("roleId", roleId);
		return super.queryForList(finder, Menu.class);
	}

	@Override
	public List<User> findUserByRoleId(String roleId) throws Exception {
		if(StringUtils.isBlank(roleId)){
			return null;
		}
		
		Finder finder=new Finder("SELECT u.* from t_user u,t_user_role  re where re.roleId=:roleId and re.userId=u.id");
		finder.setParam("roleId", roleId);
		return super.queryForList(finder, User.class);
	}

	@Override
	public List<Menu> findMenuByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		
		Finder finder=new Finder("SELECT m.* from t_menu m,t_role_menu  rm,re_user_role  ur where ur.userId=:userId and ur.roleId=rm.roleId and m.id=rm.menuId ");
		finder.setParam("userId", userId);
		return super.queryForList(finder, Menu.class);
		
	}
	@Override
	public Role findRoleAndMenu(String roleId) throws Exception {
		if(StringUtils.isBlank(roleId)){
			return null;
		}
		Role role =super.findById(roleId, Role.class);
		if(role==null){
			return null;
		}
		List<Menu> menus = findMenuByRoleId(roleId);
		role.setMenus(menus);
		return role;
	}

	@Override
	public User findUserAndMenu(String userId) throws Exception {
		User user=super.findById(userId, User.class);
		List<Role> roles = findRoleByUserId(userId);
		if(CollectionUtils.isEmpty(roles)){
			return user;
		}
		Set<Menu> menus=new HashSet<Menu>();
		for(Role r:roles){
		  r=findRoleAndMenu(r.getId());
		  menus.addAll(r.getMenus());
		}
		user.setRoles(roles);
		user.setMenus(menus);
		return user;
	}

	@Override
	public User findLoginUser(String account, String password) throws Exception {
		if(StringUtils.isBlank(account)){
			return null;
		}
       Finder finder=new Finder("SELECT * FROM T_user WHERE state=1 and account=:account ");
       finder.setParam("account", account);
       if(StringUtils.isNotBlank(password)){
    	 finder.append(" and password=:password ").setParam("password", password);
       }
		return super.queryForObject(finder, User.class);
	}

	@Override
	public List<Role> findAllRoleAndMenu() throws Exception {
		Finder f_role=new Finder("SELECT * FROM t_role where state=1 ");
		List<Role> listRole = super.queryForList(f_role,Role.class);
		if(CollectionUtils.isEmpty(listRole)){
			return null;
		}
		for(Role r:listRole){
			List<Menu> menus = findMenuByRoleId(r.getId());
			r.setMenus(menus);
		}
		return listRole;
	}
		
	
}
