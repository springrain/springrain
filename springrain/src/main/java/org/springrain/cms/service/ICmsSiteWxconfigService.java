package org.springrain.cms.service;

import org.springrain.cms.entity.CmsSiteWxconfig;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-06 11:38:43
 * @see org.springrain.cms.base.service.CmsSiteWxconfig
 */
public interface ICmsSiteWxconfigService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsSiteWxconfig findCmsSiteWxconfigById(Object id) throws Exception;
	
	
	
}
