
/**
 * 
 */
package org.iu9.frame.dao;

import java.util.List;
import java.util.Map;

import org.iu9.frame.util.Finder;
import org.iu9.frame.util.Page;


/**
 * 基础的Dao接口,所有的Dao都必须实现此接口,每个数据库都需要一个实现.</br> 
 * 例如 testdb1数据的实现接口是org.iu9.testdb1.dao.IBaseCerpDao,testdb2数据的实现接口是org.iu9.testdb2.dao.IBasetestdb2Dao</br>
 * 
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.frame.dao.IBaseJdbcDao
 */

public interface IBaseJdbcDao {
	
	
	/**
	 * 查询结果是 List<Entity>
	 * @param finder
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> queryForList(Finder finder, Class<T> clazz) throws Exception;
	
	/**
	 * 调用存储过程  查询结果是 List<Entity>
	 * @param finder
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> queryForListByProc(Finder finder, Class<T> clazz) throws Exception;
	
	
	/**
	 * 调用数据库函数  查询结果是 List<Entity>
	 * @param finder
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> queryForListByFunction(Finder finder, Class<T> clazz) throws Exception;
	
	
	

	
	/**
	 * 调用数据库存储过程  返回指定 对象
	 * @param <T>
	 * @param sql
	 * @param paramMap
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T queryForObjectByProc(Finder finder, Class<T> clazz) throws Exception ;
	/**
	 *  调用数据库函数  返回指定 对象
	 * @param <T>
	 * @param sql
	 * @param paramMap
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T queryForObjectByByFunction(Finder finder, Class<T> clazz) throws Exception ;
	
	
	/**
	 * 执行函数 返回执行结果为Map
	 * @param finder
	 * @return
	 */
	public Object queryObjectByFunction(Finder finder);
	
	
	/**
	 * 执行存储过程 返回执行结果为
	 * @param finder
	 * @return
	 */
	public Map<String,Object> queryObjectByProc(Finder finder);
	
	
	
	
	/**
	 * 返回结果是List<Map>
	 * @param sql
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryForList(Finder finder)  throws Exception ;
	
	
/**
 *  返回结果是List<Map>
 * @param finder
 * @param page
 * @return
 * @throws Exception
 */
	public List<Map<String,Object>> queryForList(Finder finder,Page page)  throws Exception ;
	
	
	
	/**
	 * 查询一个对象
	 * @param <T>
	 * @param sql
	 * @param paramMap
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T queryForObject(Finder finder, Class<T> clazz) throws Exception ;
	
	/**
	 * Entity作为查询的query bean,并返回Entity
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <T> T queryForObject(T entity) throws Exception ;
	
/**
 *  Entity作为查询的query bean,并返回Entity
 * @param entity
 * @param page
 * @return
 * @throws Exception
 */
	public <T> List<T> queryForListByEntity(T entity,Page page) throws Exception ;
	
	

	
	
	
	/**
	 * 查询返回一个map
	 * @param sql
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public  Map<String,Object>  queryForObject(Finder finder) throws Exception;
	
	/**
	 * 更新数据
	 * @param sql
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Integer update(Finder finder) throws Exception;
	


  
	
	
	/**
	 * 分页查询,自己提供详细参数
	 * @param <T>
	 * @param sql
	 * @param countSql
	 * @param orderSql
	 * @param paramMap
	 * @param clazz
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> queryForList(Finder finder, Class<T> clazz, Page page) throws Exception;
	
	
	

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * @param finder
	 * @param page 分页对象
	 * @param clazz
	 * @param queryBean 是queryBean
	 * @return
	 * @throws Exception
	 */
	public <T> List<T>  findListDataByFinder(Finder finder,Page page,Class<T> clazz,Object queryBean) throws Exception;
    /**
     * 保存一个对象
     * @param <T>
     * @param clazz
     * @return
     */
	public  Object save( Object entity) throws Exception;
	 /**
     * 更新一个对象
     * @param <T>
     * @param clazz
     * @return
     */
	public  Integer update( Object entity) throws Exception;
	/**
	 * 根据Id 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(Object id,Class clazz) throws Exception;
	
	/**
	 * 根据ID 查询对象,主要用于分表
	 * @param <T>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public <T> T findByID(Object id,Class<T> clazz)throws Exception;
	/**
	 * 根据ID 查询对象,用于分表
	 * @param id
	 * @param clazz
	 * @param tableExt
	 * @return
	 * @throws Exception
	 */
	public <T> T findByID(Object id,Class<T> clazz,String tableExt) throws Exception ;
	
	/**
	 * 判断主键是否有值 save or update 对象
	 * @param entity
	 * @return
	 */
	public Object saveorupdate(Object entity) throws Exception ;
	
	/**
	 * 根据查询的queryBean,拼接Finder 的 Where条件,只包含 and 条件,用于普通查询
	 * @param finder
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Finder getFinderWhereByQueryBean(Finder finder,Object o) throws Exception;
	
	
	
	/**
	 * 获取数据库的版本
	 * @return
	 */
	//public String getDataBaseVersion();
	/**
	 * 获取数据库的类型
	 * @return
	 */
	//public String getDataBaseType() ;
	/**
	 * 获取数据库所有的表
	 * @return
	 */
	//public List<String> getDataBaseAllTables();
	
	
	
}
