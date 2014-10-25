package org.springrain.system.service;

import org.springrain.system.entity.Kefu;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2014-10-23 16:06:12
 * @see com.bibizao.bms.goods.service.Kefu
 */
public interface IKefuService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Kefu findKefuById(Object id) throws Exception;
	
	
	
}
