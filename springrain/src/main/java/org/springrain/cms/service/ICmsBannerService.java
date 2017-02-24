package org.springrain.cms.service;

import java.util.List;

import org.springrain.cms.entity.CmsBanner;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-24 10:06:38
 * @see org.springrain.cms.base.service.CmsBanner
 */
public interface ICmsBannerService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsBanner findCmsBannerById(Object id) throws Exception;

	/**
	 * 根据站点id和businessId查找banner列表
	 * @param siteId
	 * @param businessId
	 * @return
	 * @throws Exception 
	 */
	List<CmsBanner> findBannerListByBusinessId(String siteId, String businessId) throws Exception;
	
	
	
}
