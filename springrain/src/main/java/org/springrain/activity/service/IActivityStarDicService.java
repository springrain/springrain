package org.springrain.activity.service;

import org.springrain.activity.entity.ActivityStarDic;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * 星级策略相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-14 09:34:46
 * @see org.springrain.activity.entity.base.service.ActivityStarDic
 */
public interface IActivityStarDicService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ActivityStarDic findActivityStarDicById(Object id) throws Exception;
	
	
	
}
