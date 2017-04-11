package org.springrain.activity.service;

import org.springrain.activity.entity.ActivityMember;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * 活动成员相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-13 16:47:49
 * @see org.springrain.activity.entity.ActivityMember
 */
public interface IActivityMemberService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ActivityMember findActivityMemberById(Object id) throws Exception;
	
	
	
}
