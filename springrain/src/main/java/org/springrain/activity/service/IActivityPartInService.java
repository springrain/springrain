package org.springrain.activity.service;

import java.util.List;

import org.springrain.activity.entity.ActivityPartIn;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * 活动参与记录相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-13 16:48:04
 * @see org.springrain.activity.service.ActivityPartIn
 */
public interface IActivityPartInService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ActivityPartIn findActivityPartInById(Object id) throws Exception;

	/**
	 * 根据openId和活动ID获取报名记录
	 * @param openId
	 * @param activityMainId
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	ActivityPartIn findActivityPartInByOpenIdAndActivityMainId(String openId,
			String activityMainId, String siteId) throws Exception;
	
	/**
	 * 根据用户ID与站点ID获取用户参与的活动记录
	 * @param userId
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	List<ActivityPartIn> findListDataByUserId(String userId, String siteId) throws Exception;

	/**
	 * 判断是否已报名
	 * @param name
	 * @param telPhone
	 * @param activityId
	 * @return
	 */
	boolean hasSignupForJunPolAca(String name, String telPhone,
			String activityId)  throws Exception;
	
}
