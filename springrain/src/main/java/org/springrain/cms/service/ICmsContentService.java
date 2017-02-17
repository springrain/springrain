package org.springrain.cms.service;

import java.util.List;

import org.springrain.cms.entity.CmsContent;
import org.springrain.frame.util.Page;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:19
 * @see org.springrain.cms.entity.demo.service.CmsContent
 */
public interface ICmsContentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsContent findCmsContentById(String findCmsContentById,String id) throws Exception;

	String saveContent(CmsContent cmsContent) throws Exception;

	Integer updateCmsContent(CmsContent cmsContent) throws Exception;

	/**
	 * 根据站点id分页查找
	 * @param siteId
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	List<CmsContent> findListBySiteId(String siteId, Page page) throws Exception;

	/**
	 * 根据栏目id分页查找内容列表
	 * @param siteId
	 * @param channelId 
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	List<CmsContent> findContentByChannelId(String siteId, String channelId, Page page) throws Exception;
}
