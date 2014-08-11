package org.springrain.system.service;

import java.io.File;
import java.util.List;

import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.Fwlog;
/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:44
 * @see org.springrain.springrain.service.Fwlog
 */
public interface IFwlogService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Fwlog findFwlogById(Object id) throws Exception;
	
	
	
}
