package org.springrain.system.manager.service;

import java.util.List;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.manager.entity.Menu;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:02:58
 * @see org.springrain.springrain.service.Menu
 */
@RpcServiceAnnotation
public interface IMenuService extends IBaseSpringrainService {
	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	String saveMenu(Menu entity) throws Exception;

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	String deleteMenuById(String menuId) throws Exception;

	/**
	 * 修改或者保存,根据id是否为空判断
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	String saveorupdateMenu(Menu entity) throws Exception;

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer updateMenu(Menu entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Menu findMenuById(Object id) throws Exception;

	/**
	 * 
	 * @Title: findListById @Description: 根据ID查找 @param id @return @throws
	 *         Exception @return List<Menu> @throws
	 */
	List<Menu> findListById(Object id) throws Exception;

	/**
	 * 根据pageurl查询菜单名称
	 * 
	 * @param pageurl
	 * @return
	 * @throws Exception
	 */
	String getNameByPageurl(String pageurl) throws Exception;

	/**
	 * 根据父节点ID查找子节点
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Menu> findListByPid(String id) throws Exception;

	/**
	 * 根据父菜单id查询出所有子菜单
	 * 
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	List<Menu> findAllChildByPid(String pid) throws Exception;

	/**
	 * 更新菜单状态
	 * 
	 * @param ids
	 * @param active
	 * @return
	 * @throws Exception
	 */
	Integer updateMenuActiveByIds(List<String> ids, Integer active) throws Exception;

	/**
	 * 根据子菜单ID查找该子菜单的所有父菜单
	 * 
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	List<Menu> findAllParentByChildId(String pid) throws Exception;

	/**
	 * 从菜单集合中获取父菜单（递归）
	 * 
	 * @param pid
	 * @param allMenuList
	 * @return
	 * @throws Exception
	 */
	List<Menu> findAllParentByChildIdFromAll(String pid, List<Menu> allMenuList) throws Exception;
}
