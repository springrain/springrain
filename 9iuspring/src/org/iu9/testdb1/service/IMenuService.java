package org.iu9.testdb1.service;

import java.util.List;

import org.iu9.testdb1.entity.Menu;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 16:02:58
 * @see org.iu9.testdb1.service.Menu
 */
public interface IMenuService extends IBaseTestdb1Service {
/**
	 * 保存 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	String saveMenu(Menu entity) throws Exception;
	/**
	 * 修改或者保存,根据id是否为空判断
	 * @param entity
	 * @return
	 * @throws Exception
	 */
    String saveorupdateMenu(Menu entity) throws Exception;
	 /**
     * 更新
     * @param entity
     * @return
     * @throws Exception
     */
	Integer updateMenu(Menu entity) throws Exception;
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Menu findMenuById(Object id) throws Exception;
	
	
	/**
	 * 
	 * @Title: findListById
	 * @Description: 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 * @return List<Menu>
	 * @throws
	 */
	List<Menu> findListById(Object id) throws Exception;
	
	
}
