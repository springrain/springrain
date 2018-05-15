package org.springrain.system.service;

import java.util.List;

import org.springrain.system.entity.UserPlatformInfos;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-31 16:41:34
 * @see org.springrain.cms.base.service.UserPlatformInfos
 */
public interface IUserPlatformInfosService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserPlatformInfos findUserPlatformInfosById(Object id) throws Exception;
	
	/**
	 * 通过t_user表小的id找此用户相关的平台Ids
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<UserPlatformInfos> findUserPlatformsByUserId(String userId) throws Exception;
	
	/**
	 * 通过用户ID和用户类型找台对像
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	UserPlatformInfos findUserPlateformByUserIdAndType(String userId,Integer type)throws Exception;
	
	
	/**
	 *通过用户ID和用户类型找TOKENID
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	String findTokenIdByUserIdAndType(String userId,Integer type)throws Exception;
}
