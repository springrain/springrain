package org.springrain.demo.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.springrain.demo.entity.Role;
import org.springrain.demo.service.BaseSpringrainServiceImpl;
import org.springrain.demo.service.IRoleService;
import org.springrain.demo.service.IUserRoleMenuService;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:02:59
 * @see org.springrain.demo.service.impl.Role
 */
@Service("roleService")
public class RoleServiceImpl extends BaseSpringrainServiceImpl implements IRoleService {
	@Resource
private IUserRoleMenuService userRoleMenuService;
   
    @Override
	public String  saveRole(Role entity) throws Exception{
	       return super.save(entity).toString();
	}

    @Override
	public String  saveorupdateRole(Role entity) throws Exception{
	       return super.saveorupdate(entity).toString();
	}
	
	@Override
    public Integer updateRole(Role entity) throws Exception{
	return super.update(entity);
    }
    @Override
	public Role findRoleById(Object id) throws Exception{
	 return super.findById(id,Role.class);
	}

/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	@Override
	public String findNameById(String roleId) throws Exception {
		// TODO Auto-generated method stub
		Finder finder=new Finder("select name from t_role where id=:id");
		finder.setParam("id", roleId);
		String name=super.queryForObject(finder,String.class);
		return name;
	}


}
