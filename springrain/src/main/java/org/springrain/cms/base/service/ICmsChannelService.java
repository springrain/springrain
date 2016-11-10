package org.springrain.cms.base.service;

import org.springrain.cms.base.entity.CmsChannel;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
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
	
	
	
}
