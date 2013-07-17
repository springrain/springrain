package org.iu9.testdb1.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.iu9.frame.util.Finder;
import org.iu9.frame.util.Page;
import org.iu9.testdb1.entity.Menu;
import org.iu9.testdb1.service.BaseTestdb1ServiceImpl;
import org.iu9.testdb1.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version 2013-07-06 16:02:58
 * @see org.iu9.testdb1.service.impl.Menu
 */
@Service("menuService")
public class MenuServiceImpl extends BaseTestdb1ServiceImpl implements
		IMenuService {

	@Override
	public String saveMenu(Menu entity) throws Exception {
		return super.save(entity).toString();
	}

	@Override
	public String saveorupdateMenu(Menu entity) throws Exception {
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
		List<Menu> menuList=new ArrayList<Menu>();
		String sql = "SELECT * FROM t_menu where pid=:pid";
		if(id==null || id.equals("")){
			sql = "SELECT * FROM t_menu where pid is :pid";
		}
		Finder finder = new Finder(sql);
		finder.setParam("pid", id);
		List<Menu> list = super.queryForList(finder,Menu.class);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		for(Menu m:list){
			menuList.add(addLeafMenu(m));
		}
		return menuList;
	}

	private Menu addLeafMenu(Menu menu) throws Exception{
		if(menu==null){
			return null;
		}
		String id=menu.getId();
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		Finder finder=new Finder("SELECT * FROM t_menu where pid=:pid ");
		finder.setParam("pid", id);
		List<Menu> list = super.queryForList(finder,Menu.class);
		if(CollectionUtils.isEmpty(list)){
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
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object o) throws Exception {
		return super.findListDataByFinder(finder, page, clazz, o);
	}

	/**
	 * 根据查询列表的宏,导出Excel
	 * 
	 * @param finder
	 *            为空则只查询 clazz表
	 * @param ftlurl
	 *            类表的模版宏
	 * @param page
	 *            分页对象
	 * @param clazz
	 *            要查询的对象
	 * @param o
	 *            querybean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl,
			Page page, Class<T> clazz, Object o) throws Exception {
		return super.findDataExportExcel(finder, ftlurl, page, clazz, o);
	}

}
