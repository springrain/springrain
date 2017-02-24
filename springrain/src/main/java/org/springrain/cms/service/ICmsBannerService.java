package org.springrain.cms.service;

import org.springrain.cms.entity.CmsBanner;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-24 10:06:38
 * @see org.springrain.cms.base.service.CmsBanner
 */
public interface ICmsBannerService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsBanner findCmsBannerById(Object id) throws Exception;
	
	
	
}
