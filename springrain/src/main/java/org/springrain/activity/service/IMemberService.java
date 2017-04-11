package org.springrain.activity.service;

import java.util.List;
import java.util.Map;

import org.springrain.activity.entity.Member;
import org.springrain.frame.util.Page;
import org.springrain.system.service.IBaseSpringrainService;

import freemarker.core.Environment;
/**
 * 会员相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-21 14:34:32
 * @see org.springrain.activity.entity.base.service.Member
 */
public interface IMemberService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Member findMemberById(Object id) throws Exception;
	
	/**
	 * 获取某个站点下的会员列表
	 * @param siteId
	 * @param page
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */
	List<Member> findMemberList(String siteId,Page page,Member queryBean) throws Exception;

	/**
	 * 根据openId获取会员信息
	 * @param siteId
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	//Member findMemeberByOpenId(String siteId,String openId) throws Exception;

	/**
	 * 完善会员信息
	 * @param member
	 */
	void saveMemberAndUser(Member member) throws Exception;
	
	/**
	 * 保存会员积分信息
	 * @param id
	 * @param point
	 * @param siteId
	 * @throws Exception
	 */
	void saveMemberPoint(String id, int point,String siteId) throws Exception;

	/**
	 * 根据openId和siteId获取会员信息
	 * @param openId
	 * @param siteId
	 * @return
	 */
	Member findMemberByOpenId(String openId, String siteId) throws Exception;

	/**
	 * 根据ids获取会员列表
	 * @param idList
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	List<Member> findListByIdsForTag(List<String> idList, int orderBy) throws Exception;

	/**
	 * 获取会员列表
	 * @param params
	 * @param env
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	List<Member> findListForTag(Map params, Environment env, String siteId) throws Exception;
	
}
