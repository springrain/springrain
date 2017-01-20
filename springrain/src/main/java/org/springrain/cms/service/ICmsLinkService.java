package org.springrain.cms.service;

import org.springrain.cms.entity.CmsLink;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:20
 * @see org.springrain.cms.entity.demo.service.CmsLink
 */
public interface ICmsLinkService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsLink findCmsLinkById(String id) throws Exception;
	
	
	String  saveCmsLink(CmsLink cmsLink ) throws Exception;

	    

    Integer updateCmsLink(CmsLink cmsLink ) throws Exception;

	/**
	 * 查询资源链接
	 * @param bussinessId
	 * @return
	 * @throws Exception 
	 */
    CmsLink findLinkBySiteBusinessId(String siteId,String bussinessId) throws Exception;
	
	
}
