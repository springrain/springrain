package org.springrain.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.service.IBaseService;
import org.springrain.demo.service.BaseSpringrainServiceImpl;
import org.springrain.demo.entity.UserRole;
import org.springrain.demo.service.IUserRoleService;
import java.util.List;
import java.io.File;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.Finder;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:48
 * @see org.springrain.demo.service.impl.UserRole
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseSpringrainServiceImpl implements IUserRoleService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      UserRole userRole=(UserRole) entity;
	       return (String) super.save(userRole);
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      UserRole userRole=(UserRole) entity;
		 return super.saveorupdate(userRole).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 UserRole userRole=(UserRole) entity;
	return super.update(userRole);
    }
    @Override
	public UserRole findUserRoleById(Object id) throws Exception{
	 return super.findById(id,UserRole.class);
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

}
