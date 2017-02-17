package org.springrain.cms.service;

import java.util.List;

import org.springrain.cms.entity.CmsComment;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:18
 * @see org.springrain.cms.entity.demo.service.CmsComment
 */
public interface ICmsCommentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsComment findCmsCommentById(String id) throws Exception;

	/**
	 * 根据业务id查询评论数量
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	Integer findCommentsNumByBusinessId(String siteId,String businessId) throws Exception;
	
	List<CmsComment> findCommentListByBusinessId(String siteId,String businessId) throws Exception;
	
}
