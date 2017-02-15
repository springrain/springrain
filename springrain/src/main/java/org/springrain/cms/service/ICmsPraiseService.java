package org.springrain.cms.service;

import org.springrain.cms.entity.CmsPraise;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-15 15:02:34
 * @see org.springrain.cms.base.service.CmsPraise
 */
public interface ICmsPraiseService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsPraise findCmsPraiseById(Object id) throws Exception;
	
	
	
}
