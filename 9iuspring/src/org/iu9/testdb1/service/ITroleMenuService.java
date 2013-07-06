package org.iu9.testdb1.service;

import org.iu9.testdb1.entity.TroleMenu;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 15:28:17
 * @see org.iu9.testdb1.service.TroleMenu
 */
public interface ITroleMenuService extends IBaseTestdb1Service {
/**
	 * 保存 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	String saveTroleMenu(TroleMenu entity) throws Exception;
	/**
	 * 修改或者保存,根据id是否为空判断
	 * @param entity
	 * @return
	 * @throws Exception
	 */
    String saveorupdateTroleMenu(TroleMenu entity) throws Exception;
	 /**
     * 更新
     * @param entity
     * @return
     * @throws Exception
     */
	Integer updateTroleMenu(TroleMenu entity) throws Exception;
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	TroleMenu findTroleMenuById(Object id) throws Exception;
	
	
}
