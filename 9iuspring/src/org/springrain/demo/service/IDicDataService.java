package org.springrain.demo.service;

import org.springrain.demo.entity.DicData;
import java.io.File;
import java.util.List;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-31 15:56:45
 * @see org.springrain.demo.service.DicData
 */
public interface IDicDataService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	DicData findDicDataById(Object id) throws Exception;
	public List<String> findAjax(String pathtypekey)throws Exception;
	
	
	
	
}
