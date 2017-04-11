package org.springrain.activity.service;

import java.util.List;
import java.util.Map;

import org.springrain.activity.entity.ActivityMain;
import org.springrain.activity.entity.ActivityPartIn;
import org.springrain.activity.entity.Member;
import org.springrain.frame.util.Page;
import org.springrain.system.service.IBaseSpringrainService;

import freemarker.core.Environment;
/**
 * 活动内容信息相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-13 17:20:47
 * @see org.springrain.activity.service.ActivityMain
 */
public interface IActivityMainService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ActivityMain findActivityMainById(Object id) throws Exception;

	/**
	 * 根据IDs获取活动内容列表
	 * @param asList
	 * @param orderBy
	 * @return
	 */
	List<ActivityMain> findListByIdsForTag(List<String> asList, int orderBy) throws Exception;

	/**
	 * 根据参数获取活动内容列表
	 * @param params
	 * @param env
	 * @param siteId
	 * @return
	 */
	List<ActivityMain> findListForTag(Map params, Environment env, String siteId, String chnannelId) throws Exception;

	/**
	 * 保存活动内容（新增）
	 * @param activityMain
	 * @return ID
	 * @throws Exception
	 */
	String saveActivityMain(ActivityMain activityMain) throws Exception;

	/**
	 * 更新活动内容
	 * @param activityMain
	 * @return 更新记录数
	 * @throws Exception
	 */
	Integer updateActivityMain(ActivityMain activityMain) throws Exception;

	/**
	 * 删除活动内容
	 * @param id
	 * @param siteId
	 * @throws Exception
	 */
	void deleteById(String id, String siteId) throws Exception;

	/**
	 * 删除多条活动内容
	 * @param ids
	 * @param siteId
	 * @throws Exception
	 */
	void deleteByIds(List<String> ids, String siteId) throws Exception;

	/**
	 * 查找与当前活动内容相近的活动
	 * @param id
	 * @param siteId
	 * @param channelId
	 * @param next
	 * @return
	 * @throws Exception
	 */
	ActivityMain findActivityMainSide(String id, String siteId, String channelId,
			Boolean next) throws Exception;

	/**
	 * 根据siteId、id查找活动
	 * @param siteId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ActivityMain findActivityMainById(String siteId, String id) throws Exception;

	/**
	 * 保存报名记录
	 * @param member
	 * @param activityMainId
	 * @throws Exception
	 */
	void saveActivityPartIn(Member member,String activityMainId) throws Exception;

	/**
	 * 保存报名记录
	 * @param openId
	 * @param activityMainId
	 * @param siteId
	 */
	void saveActivityPartIn(String openId, String activityMainId, String siteId) throws Exception;

	/**
	 * 获取活动列表
	 * @param activityMain
	 * @param siteId
	 * @param channelId 
	 * @param page
	 * @return
	 */
	List<ActivityMain> findActivityMainList(ActivityMain activityMain,
			String siteId, String channelId ,Page newPage) throws Exception;
	
	/**
	 * 保存警校参与记录
	 * @param api
	 * @param siteId
	 * @throws Exception
	 */
	void saveActivityPartInForJunPolAca(ActivityPartIn api, String siteId)  throws Exception;
	
}
