package org.iu9.testdb1.service;

import javax.annotation.Resource;

import org.iu9.frame.dao.IBaseJdbcDao;
import org.iu9.frame.service.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service("baseTestdb1Service")
public class BaseTestdb1ServiceImpl extends BaseServiceImpl implements IBaseTestdb1Service {
	
	@Resource
	IBaseJdbcDao baseTestdb2Dao;
	
	public BaseTestdb1ServiceImpl(){}

	@Override
	public IBaseJdbcDao getBaseDao() {
		return baseTestdb2Dao;
	}






}
