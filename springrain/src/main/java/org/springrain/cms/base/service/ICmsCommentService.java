package org.springrain.cms.base.service;

import org.springrain.cms.base.entity.CmsComment;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:18
 * @see org.springrain.demo.service.CmsComment
 */
public interface ICmsCommentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsComment findCmsCommentById(String id) throws Exception;
	
	
	
}
