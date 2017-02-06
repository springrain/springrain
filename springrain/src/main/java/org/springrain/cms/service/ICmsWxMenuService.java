package org.springrain.cms.service;

import java.util.List;

import org.springrain.cms.entity.CmsWxMenu;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-06 17:23:12
 * @see org.springrain.cms.base.service.CmsWxMenu
 */
public interface ICmsWxMenuService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsWxMenu findCmsWxMenuById(Object id) throws Exception;

	/**
	 * 查找所有的顶级菜单
	 * @param siteId 
	 * @return
	 * @throws Exception 
	 */
	List<CmsWxMenu> findParentMenuList(String siteId) throws Exception;

	/**
	 * 根据父级ID查找二级菜单
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	List<CmsWxMenu> findChildMenuListByPid(String pid) throws Exception;
	
	
	
}
