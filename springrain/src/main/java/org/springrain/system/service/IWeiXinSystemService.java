package org.springrain.system.service;

import org.springrain.system.entity.User;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2014-08-20 22:48:34
 * @see com.bibizao.system.entity.Picture
 */
public interface IWeiXinSystemService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User findMemberByweixinId(String weixinId) throws Exception;
	/**
	 * 根据微信Id更新用户信息,若用户不存在则保存用户
	 * @param weixinId
	 * @param state
	 * @throws Exception
	 */
	void updateMemberinfoByweixinId(String weixinId,Integer state) throws Exception;
	
	
	
}
