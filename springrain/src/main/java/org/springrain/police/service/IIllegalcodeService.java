package org.springrain.police.service;

import org.springrain.police.entity.Illegalcode;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-06 10:57:53
 * @see org.springrain.cms.base.service.Illegalcode
 */
public interface IIllegalcodeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Illegalcode findIllegalcodeById(Object id) throws Exception;
	
	
}
