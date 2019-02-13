package org.springrain.system.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.system.manager.entity.Menu;
import org.springrain.system.manager.entity.RoleMenu;
import org.springrain.system.manager.service.IMenuService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:02:58
 * @see org.springrain.springrain.service.impl.Menu
 */
@Service("menuService")
public class MenuServiceImpl extends BaseSpringrainServiceImpl implements IMenuService {

	@Override
	public String saveMenu(Menu entity) throws Exception {
		return super.save(entity).toString();
	}

	@Override
	public String saveorupdateMenu(Menu entity) throws Exception {
		super.cleanCache(GlobalStatic.qxCacheKey);
		// 禁用，启用所有子菜单
		if (entity != null && entity.getActive() != null && entity.getId() != null) {
			List<Menu> childList = findAllChildByPid(entity.getId());
			if (childList != null && !childList.isEmpty()) {
				List<String> childIds = new ArrayList<String>();
				for (Menu child : childList) {
					childIds.add(child.getId());
				}
				updateMenuActiveByIds(childIds, entity.getActive());
			}
		}

		return super.saveorupdate(entity).toString();
	}

	@Override
	public Integer updateMenu(Menu entity) throws Exception {
		return super.update(entity);
	}

	@Override
	public Menu findMenuById(Object id) throws Exception {
		return super.findById(id, Menu.class);
	}

	public List<Menu> findListById(Object id) throws Exception {
		List<Menu> menuList = new ArrayList<>();
		Finder finder = Finder.getSelectFinder(Menu.class);

		if (id == null || StringUtils.isBlank(id.toString())) {
			finder.append(" where pid is :pid");
		} else {
			finder.append(" where pid=:pid ");
		}
		finder.setParam("pid", id);
		List<Menu> list = super.queryForList(finder, Menu.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		for (Menu m : list) {
			menuList.add(addLeafMenu(m));
		}
		return menuList;
	}

	private Menu addLeafMenu(Menu menu) throws Exception {
		if (menu == null) {
			return null;
		}
		String id = menu.getId();
		if (StringUtils.isBlank(id)) {
			return null;
		}

		Finder finder = Finder.getSelectFinder(Menu.class).append(" where pid=:pid ");
		finder.setParam("pid", id);
		List<Menu> list = super.queryForList(finder, Menu.class);
		if (CollectionUtils.isEmpty(list)) {
			return menu;
		}
		menu.setLeaf(list);
		return menu;
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz, Object o) throws Exception {
		finder = Finder.getSelectFinder(Menu.class).append(" WHERE 1=1  order by sortno asc ");
		return super.queryForList(finder, clazz);
	}

	@Override
	// @Cacheable(value = GlobalStatic.cacheKey, key =
	// "'getNameByPageurl_'+#pageurl")
	public String getNameByPageurl(String pageurl) throws Exception {

		if (StringUtils.isBlank(pageurl)) {
			return null;
		}

		List<String> list = null;
		String key = "getNameByPageurl_" + pageurl;
		list = super.getByCache(GlobalStatic.cacheKey, key, List.class);
		if (list != null) {
			return list.toString();
		}

		Finder finder = Finder.getSelectFinder(Menu.class, "name").append(" WHERE pageurl=:pageurl ");
		finder.setParam("pageurl", pageurl);
		list = queryForList(finder, String.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		// 加上缓存
		super.putByCache(GlobalStatic.cacheKey, key, list);

		return list.toString();
	}

	@Override
	public String deleteMenuById(String menuId) throws Exception {
		super.cleanCache(GlobalStatic.qxCacheKey);

		if (StringUtils.isBlank(menuId)) {
			return null;
		}
		Finder finder_del_user = Finder.getDeleteFinder(RoleMenu.class).append(" WHERE menuId=:menuId ")
				.setParam("menuId", menuId);
		super.update(finder_del_user);
		super.deleteById(menuId, Menu.class);
		return null;
	}

	@Override
	public List<Menu> findListByPid(String id) throws Exception {
		Finder finder = Finder.getSelectFinder(Menu.class).append(" where pid = :id ").setParam("id", id);
		return super.queryForList(finder, Menu.class);
	}

	@Override
	public List<Menu> findAllChildByPid(String pid) throws Exception {
		Finder finder = Finder.getSelectFinder(Menu.class);
		List<Menu> allMenuList = super.queryForList(finder, Menu.class);
		List<Menu> resList = findChildByPidFromAll(pid, allMenuList);
		return resList;
	}

	/**
	 * 从菜单集合中获取子菜单（递归）
	 * 
	 * @param pid
	 * @param allMenuList
	 * @return
	 * @throws Exception
	 */
	private List<Menu> findChildByPidFromAll(String pid, List<Menu> allMenuList) throws Exception {
		if (allMenuList == null || allMenuList.isEmpty()) {
			return null;
		}

		List<Menu> subMenuList = new ArrayList<Menu>();

		for (Menu menu : allMenuList) {
			if (pid.equals(menu.getPid())) {
				subMenuList.add(menu);
			}
		}

		if (subMenuList.isEmpty()) {
			return subMenuList;
		}

		List<Menu> resMenuList = new ArrayList<Menu>();
		resMenuList.addAll(subMenuList);
		for (Menu menu : subMenuList) {
			List<Menu> childMenuList = findChildByPidFromAll(menu.getId(), allMenuList);
			if (childMenuList == null || childMenuList.isEmpty()) {
				continue;
			}
			resMenuList.addAll(childMenuList);
		}
		return resMenuList;
	}

	@Override
	public Integer updateMenuActiveByIds(List<String> ids, Integer active) throws Exception {
		if (ids == null || ids.isEmpty()) {
			return null;
		}

		Finder finder = Finder.getUpdateFinder(Menu.class).append(" active = :active where id in (:ids) ")
				.setParam("active", active).setParam("ids", ids);
		;

		return super.update(finder);
	}

	@Override
	public List<Menu> findAllParentByChildId(String pid) throws Exception {
		Finder finder = Finder.getSelectFinder(Menu.class);
		List<Menu> allMenuList = super.queryForList(finder, Menu.class);
		List<Menu> resList = findAllParentByChildIdFromAll(pid, allMenuList);
		return resList;
	}

	/**
	 * 从菜单集合中获取父菜单（递归）
	 * 
	 * @param pid
	 * @param allMenuList
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Menu> findAllParentByChildIdFromAll(String pid, List<Menu> allMenuList) throws Exception {
		if (pid == null) {
			return null;
		}
		if (allMenuList == null || allMenuList.isEmpty()) {
			return null;
		}

		List<Menu> pMenus = new ArrayList<Menu>();
		for (Menu menu : allMenuList) {
			if (menu.getId().equals(pid)) {
				pMenus.add(menu);

				List<Menu> pMenus2 = findAllParentByChildIdFromAll(menu.getPid(), allMenuList);
				if (pMenus2 != null) {
					pMenus.addAll(pMenus2);
				}
			}
		}

		return pMenus;
	}

}
