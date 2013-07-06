package org.iu9.testdb1.service;

import java.util.List;

import org.iu9.testdb1.entity.Menu;
import org.iu9.testdb1.entity.Role;
import org.iu9.testdb1.entity.User;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.iu9.testdb1.service.UserRole
 */
public interface IUserRoleMenuService extends IBaseTestdb1Service {
/**
 * 根据用户ID 获取角色
 * @param UserId
 * @return
 * @throws Exception
 */
	List<Role> findRoleByUserId(String userId) throws Exception;
	/**
	 * 根据角色 获取能够访问的菜单
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<Menu> findMenuByRoleId(String roleId) throws Exception;
	/**
	 * 获取角色下的所有人员
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<User> findUserByRoleId(String roleId) throws Exception;
	/**
	 * 根据userId 查询能够看到的菜单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Menu> findMenuByUserId(String userId) throws Exception;
	
}
