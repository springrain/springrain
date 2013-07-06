package org.iu9.testdb1.service;

import java.util.List;

import org.iu9.testdb1.entity.Torg;
import org.iu9.testdb1.entity.Tuser;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 15:28:18
 * @see org.iu9.testdb1.service.TuserOrg
 */
public interface ITuserOrgService extends IBaseTestdb1Service {

	
	/**
	 * 根据部门Id 查找部门下的所有人员
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<Tuser> findUsersByOrgId(String orgId) throws Exception;

	
	/**
	 * 根据部门ID,查找部门下(包括所有子部门)的人员
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<Tuser> findAllUsersByOrgId(String orgId) throws Exception;
	
	/**
	 * 根据用户ID 查找所在的部门
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Torg> findOrgByUserId(String userId) throws Exception;
	/**
	 * 根据部门ID 查找主管
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	Tuser findManagerByOrgId(String orgId) throws Exception;
	
	
	
}
