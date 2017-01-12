package org.springrain.cms.base.service;

import java.util.List;

import org.springrain.cms.base.entity.CmsChannel;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:17
 * @see org.springrain.demo.service.CmsChannel
 */
public interface ICmsChannelService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsChannel findCmsChannelById(String id) throws Exception;

	String saveChannel(CmsChannel cmsChannel) throws Exception;

	Integer updateChannel(CmsChannel cmsChannel) throws Exception;
	
	
	/**
	 * 根据id和pid生成栏目的Comcode
	 * @param id
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	String findChannelNewComcode(String id,String pid,String siteId) throws Exception ;
	
	/**
	 * 查找CmsChannel的树形结构
	 * @return
	 * @throws Exception
	 */
	List<CmsChannel> findTreeChannel(String siteId)throws Exception;
	
	/**
	 * 根据父类Id 查找CmsChannel的树形结构,根为 null
	 * @return
	 * @throws Exception
	 */
	List<CmsChannel> findTreeByPid(String pid,String siteId)throws Exception;

}
