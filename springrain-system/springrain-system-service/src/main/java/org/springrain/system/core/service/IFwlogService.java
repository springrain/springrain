package org.springrain.system.core.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.core.entity.Fwlog;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link springrain}
 * @author weicms.net<Auto generate>
 * @version 2013-07-29 11:36:44
 * @see org.springrain.springrain.service.Fwlog
 */
@RpcServiceAnnotation
public interface IFwlogService extends IBaseSpringrainService {

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Fwlog findFwlogById(Object id) throws Exception;

}
