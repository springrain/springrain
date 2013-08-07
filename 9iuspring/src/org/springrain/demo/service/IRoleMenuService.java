package org.springrain.demo.service;

import org.springrain.demo.entity.RoleMenu;
import java.io.File;
import java.util.List;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:47
 * @see org.springrain.demo.service.RoleMenu
 */
public interface IRoleMenuService extends IBaseDemoService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	RoleMenu findRoleMenuById(Object id) throws Exception;
	
	
	
}
