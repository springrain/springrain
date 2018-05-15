package org.springrain.system.service;

import org.springrain.system.entity.H5drag;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-05-14 15:58:54
 * @see org.springrain.system.service.H5drag
 */
public interface IH5dragService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	H5drag findH5dragById(Object id) throws Exception;
	
	
	
}
