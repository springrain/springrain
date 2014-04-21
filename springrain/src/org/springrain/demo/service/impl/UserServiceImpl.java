package org.springrain.demo.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springrain.demo.entity.User;
import org.springrain.demo.entity.UserRole;
import org.springrain.demo.service.BaseDemoServiceImpl;
import org.springrain.demo.service.IUserService;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.springrain.demo.service.impl.User
 */
@Service("userService")
public class UserServiceImpl extends BaseDemoServiceImpl implements IUserService {

   
    @Override
	public String  saveUser(User entity) throws Exception{
	       return super.save(entity).toString();
	}

    @Override
	public String  saveorupdateUser(User entity) throws Exception{
	       return super.saveorupdate(entity).toString();
	}
	
	@Override
    public Integer updateUser(User entity) throws Exception{
	return super.update(entity);
    }
    @Override
	public User findUserById(Object id) throws Exception{
	 return super.findById(id,User.class);
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
	@Caching(evict={@CacheEvict(value = GlobalStatic.cacheKey,key = "'findRoleByUserId_'+#userId"),@CacheEvict(value = GlobalStatic.cacheKey,key = "'getRolesAsString_'+#userId"),@CacheEvict(value = GlobalStatic.cacheKey,key = "'findUserByRoleId_'+#userId"),@CacheEvict(value = GlobalStatic.cacheKey,key = "'findAllRoleAndMenu'")})
	public void updateRoleUser(String userId, String roleId) throws Exception {
		//删除
		//Finder finder=new Finder("delete from t_user_role where userId=:userId");
		Finder finder=Finder.getDeleteFinder(UserRole.class).append(" WHERE userId=:userId");
		
		finder.setParam("userId", userId);
		this.update(finder);
		//添加
		String[] roleIds=roleId.split(",");
		//finder=new Finder("insert into t_user_role(id,userId,roleId) values(:id,:userId,:roleId)");
		//finder.setParam("userId", userId);
		
		List<UserRole> list=new ArrayList<UserRole>();
		
		for(String str:roleIds){
			if(StringUtils.isBlank(str)){
				continue;
			}
			UserRole ur=new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(str);
			list.add(ur);
		}
		
		if(!CollectionUtils.isEmpty(list)){
			super.save(list);
		}
		
		
		
	}





		
}