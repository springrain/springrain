package org.springrain.cms.base.service;

import org.springrain.cms.base.entity.CmsSite;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:21
 * @see org.springrain.demo.service.CmsSite
 */
public interface ICmsSiteService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsSite findCmsSiteById(String id) throws Exception;
	/**
	 * 保存站点
	 * @param entity
	 * @return
	 * @throws Exception
	 */
   String  saveCmsSite(CmsSite cmsSite ) throws Exception;
   
   /**
    * 更新站点
    * @param cmsSite
    * @return
    * @throws Exception
    */
   String  updateCmsSite(CmsSite cmsSite) throws Exception;
   
   /**
    * 根据 站点ID 查找 站点的类型
    * @param siteId
    * @return
    * @throws Exception
    */
   Integer findSiteTypeById(String siteId)throws Exception;
	
	
}
