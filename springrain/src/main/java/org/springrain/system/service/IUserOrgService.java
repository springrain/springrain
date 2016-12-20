package org.springrain.system.service;

import java.util.List;

import org.springrain.system.entity.Org;
import org.springrain.system.entity.User;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 15:28:18
 * @see org.springrain.springrain.service.TuserOrg
 */
public interface IUserOrgService extends IBaseSpringrainService {

	
	/**
	 * 根据部门Id 查找部门下的所有人员
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<User> findUserByOrgId(String orgId) throws Exception;
	
	
	
	/**
	 * 根据部门Id 查找部门下的所有人员的Id
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<String> findUserIdsByOrgId(String orgId) throws Exception;
	
	

	
	/**
	 * 根据部门ID,查找部门下(包括所有子部门)的人员
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<User> findAllUserByOrgId(String orgId) throws Exception;
	
	
	

	/**
	 * 根据部门ID,查找部门下(包括所有子部门)的人员Id
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<String> findAllUserIdsByOrgId(String orgId) throws Exception;
	
	/**
	 * 根据用户ID 查找所在的部门
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Org> findOrgByUserId(String userId) throws Exception;
	
	/**
	 * 根据用户ID 查找所在的部门的Id
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<String> findOrgIdsByUserId(String userId) throws Exception;
	
	
	/**
	 * 根据部门ID 查找主管
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<User> findManagerUserByOrgId(String orgId) throws Exception;
	
	
	/**
	 * 根据部门ID 查找主管Id
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<String> findManagerUserIdsByOrgId(String orgId) throws Exception;
	
	
	
	
	
	/**
	 * 根据主管ID 查找主管部门Ids
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<String> findOrgIdsIdsByManagerUserId(String managerUserId) throws Exception;
	
	/**
	 * 根据主管ID 查找主管部门
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<Org> findOrgIdsByManagerUserId(String managerUserId) throws Exception;
	
	
	
	/**
	 * 返回根据主管子查询的orgId的sql语句
	 * @param managerUserId
	 * @return
	 * @throws Exception
	 */
	String findOrgIdsSQLByManagerUserId(String managerUserId) throws Exception;
	
	
	
	
	/**
	 * 根据部门ID,查找部门下(包括所有子部门)的人员数量
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	Integer findAllUserCountByOrgId(String orgId) throws Exception;
	
	
	
}
