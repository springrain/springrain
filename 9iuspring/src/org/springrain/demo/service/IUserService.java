package org.springrain.demo.service;

import java.util.List;
import java.util.Map;

import org.springrain.demo.entity.Role;
import org.springrain.demo.entity.User;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.springrain.demo.service.User
 */
public interface IUserService extends IBaseSpringrainService {
/**
	 * 保存 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	String saveUser(User entity) throws Exception;
	/**
	 * 修改或者保存,根据id是否为空判断
	 * @param entity
	 * @return
	 * @throws Exception
	 */
    String saveorupdateUser(User entity) throws Exception;
	 /**
     * 更新
     * @param entity
     * @return
     * @throws Exception
     */
	Integer updateUser(User entity) throws Exception;
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User findUserById(Object id) throws Exception;
    

	
	void updateRoleUser(String userId,String roleIds)throws Exception;

	
}
