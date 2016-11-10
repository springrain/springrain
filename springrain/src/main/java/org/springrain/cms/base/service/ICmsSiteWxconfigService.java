package org.springrain.cms.base.service;

import org.springrain.cms.base.entity.CmsSiteWxconfig;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:22
 * @see org.springrain.demo.service.CmsSiteWxconfig
 */
public interface ICmsSiteWxconfigService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsSiteWxconfig findCmsSiteWxconfigById(String id) throws Exception;
	
	
	
}
