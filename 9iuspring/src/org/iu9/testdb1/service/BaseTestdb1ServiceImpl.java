package org.iu9.testdb1.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.iu9.frame.dao.IBaseJdbcDao;
import org.iu9.frame.entity.IBaseEntity;
import org.iu9.frame.service.BaseServiceImpl;
import org.iu9.frame.util.Finder;
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

	/**
	 * 导入Excle文件
	 * @param excel
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	
	public <T> String saveImportExcelFile(File excel,Class<T> clazz)throws Exception{
		return super.saveImportExcelFile(excel, clazz);
	}
	
	/**
	 * Excel 导入时会循环调用该方法
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public String saveFromExcel(Object entity, int index, boolean istest,
			List<String> listTitle) throws Exception {
		if(istest){
			return null;
		}
		return save(entity).toString();
	}

   @Override
	public Integer update(Finder finder) throws Exception {
		return super.update(finder);
	}
   /**
    * 保存一个对象
    * @param <T>
    * @param clazz
    * @return
    */
	public  Object save( Object entity) throws Exception{
		return super.save(entity);
	}
	 /**
    * 更新一个对象
    * @param <T>
    * @param clazz
    * @return
    */
	public  Integer update( IBaseEntity entity) throws Exception{
		return super.update(entity);
	}

	/**
	 * 根据Id 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(Object id,Class clazz) throws Exception{
		 super.deleteById(id, clazz);
	}
	
	/**
	 * 判断主键是否有值 save or update 对象
	 * @param entity
	 * @return
	 */
	public Object saveorupdate(Object entity) throws Exception {
		return super.saveorupdate(entity);
	}




}
