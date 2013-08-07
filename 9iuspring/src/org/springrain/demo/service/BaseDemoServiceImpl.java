package org.springrain.demo.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.service.BaseServiceImpl;
import org.springrain.frame.util.Finder;


@Service("baseDemoService")
public class BaseDemoServiceImpl extends BaseServiceImpl implements IBaseDemoService {
	
	@Resource
	IBaseJdbcDao baseSpringrainDao;
	
	public BaseDemoServiceImpl(){}

	@Override
	public IBaseJdbcDao getBaseDao() {
		return baseSpringrainDao;
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
