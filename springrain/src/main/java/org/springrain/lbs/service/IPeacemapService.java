package org.springrain.lbs.service;

import java.util.List;

import org.springrain.frame.util.Page;
import org.springrain.lbs.entity.Peacemap;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-14 10:44:41
 * @see org.springrain.lbs.entity.base.service.Peacemap
 */
public interface IPeacemapService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Peacemap findPeacemapById(Object id) throws Exception;
	
	/**
	 * 根据siteid(站点id)和categoryId(类型id)查找数据
	 * @param sid
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	List<Peacemap> findPeacemapBySidCid(String sid, String cid, Class<Peacemap> clazz, Page page) throws Exception; 
	
	/**
	 * 根据name查找数据
	 * @param sid
	 * @param cid
	 * @param orderBy
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<Peacemap> findPeacemapByName(String sid, String cid,String name, Class<Peacemap> clazz, Page page) throws Exception;
	
	
	
}
