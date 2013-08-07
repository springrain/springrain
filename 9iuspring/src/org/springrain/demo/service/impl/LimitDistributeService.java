package org.springrain.demo.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.springrain.demo.service.BaseSpringrainServiceImpl;
import org.springrain.demo.service.ILimitDistributeService;
import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.util.Finder;

@Service("limitDistributeService")
public class LimitDistributeService extends BaseSpringrainServiceImpl implements ILimitDistributeService{

	@Resource
	IBaseJdbcDao basedhxDao;
	@Override
	public IBaseJdbcDao getBaseDao() {
		// TODO Auto-generated method stub
		return basedhxDao;
	}
	@Override
	public void update(String roleId,String[] menus) throws Exception {
		// TODO Auto-generated method stub
		//删除现在的中间权限表
				Finder finder=new Finder("delete from t_role_menu  where roleId=:roleId ");
				finder.setParam("roleId", roleId);
				this.update(finder);
				//新加权限
				finder=new Finder("insert into t_role_menu(id,roleId,menuId) values(:id,:roleId,:menuId)");
				for(String menuId:menus){
					finder.setParam("id", UUID.randomUUID().toString());
					finder.setParam("roleId", roleId);
					finder.setParam("menuId", menuId);
					this.update(finder);
				}
	}

}
