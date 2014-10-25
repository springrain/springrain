package org.springrain.system.service;

import org.springrain.system.entity.RoleMenu;
/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:47
 * @see org.springrain.springrain.service.RoleMenu
 */
public interface IRoleMenuService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	RoleMenu findRoleMenuById(Object id) throws Exception;
	
	
	
}
