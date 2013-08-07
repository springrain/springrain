package org.springrain.demo.service;

import org.springrain.demo.entity.UserRole;
import java.io.File;
import java.util.List;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:48
 * @see org.springrain.demo.service.UserRole
 */
public interface IUserRoleService extends IBaseDemoService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserRole findUserRoleById(Object id) throws Exception;
	
	
	
}
