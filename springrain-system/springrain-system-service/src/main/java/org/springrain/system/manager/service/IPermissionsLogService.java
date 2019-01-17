package org.springrain.system.manager.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.manager.entity.PermissionsLog;
import org.springrain.system.manager.entity.Role;

/**
 * 权限日志
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-12-15 12:13:15
 * @see org.springrain.system.service.PermissionsLog
 */
@RpcServiceAnnotation
public interface IPermissionsLogService extends IBaseSpringrainService {

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PermissionsLog findPermissionsLogById(Object id) throws Exception;

	/**
	 * 保存权限日志
	 * 
	 * @param log
	 * @return
	 * @throws Exception
	 */
	String savePermissionsLog(PermissionsLog log) throws Exception;

	/**
	 * 保存角色变更日志
	 * 
	 * @param newRole
	 * @param oldRole
	 * @param actionType
	 * @return
	 * @throws Exception
	 */
	String saveUpdateRoleLog(Role newRole, Role oldRole, Integer actionType) throws Exception;

	/**
	 * 保存用户变更日志
	 * 
	 * @param userId
	 * @param actionType
	 * @return
	 * @throws Exception
	 */
	String saveUserLog(String userId, int actionType) throws Exception;

}
