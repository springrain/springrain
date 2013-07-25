package org.iu9.frame.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.iu9.frame.common.BaseLogger;
import org.iu9.frame.common.SessionUser;
import org.iu9.frame.dao.dialect.IDialect;
import org.iu9.frame.entity.IAuditLog;
import org.iu9.frame.util.ClassUtils;
import org.iu9.frame.util.EntityInfo;
import org.iu9.frame.util.Finder;
import org.iu9.frame.util.GlobalStatic;
import org.iu9.frame.util.Page;
import org.iu9.frame.util.RegexValidateUtils;
import org.iu9.frame.util.SecUtils;
import org.iu9.frame.util.WhereSQLInfo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * 基础的Dao父类,所有的Dao都必须继承此类,每个数据库都需要一个实现.</br>
 * 
 * 例如
 * testdb1数据的实现类是org.iu9.testdb1.dao.BaseTestdb1DaoImpl,testdb2数据的实现类是org.iu9.
 * testdb2.dao.Basetestdb2DaoImpl</br>
 * 
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version 2013-03-19 11:08:15
 * @see org.iu9.frame.dao.BaseJdbcDaoImpl
 */
public abstract class BaseJdbcDaoImpl extends BaseLogger implements
		IBaseJdbcDao {
	
	/**
	 * 抽象方法.每个数据库的代理Dao都必须实现.在多库情况下,用于区分底层数据库的连接对象,对数据库进行增删改查.</br>
	 * 例如:testdb1数据库的代理Dao org.iu9.testdb1.dao.BaseTestdb1DaoImpll
	 * 实现返回的是spring的beanjdbc.</br> testdb2 数据库的代理Dao
	 * org.iu9.testdb2.dao.BaseTestdb2DaoImpl 实现返回的是spring的bean
	 * jdbc_testdb2.</br>
	 * 
	 * @return
	 */
	public abstract NamedParameterJdbcTemplate getJdbc();

	
	/**
	 * 抽象方法.每个数据库的代理Dao都必须实现.在多库情况下,用于区分底层数据库的连接对象,调用数据库的函数和存储过程.</br>
	 * 例如:testdb1 数据库的代理Dao org.iu9.testdb1.dao.BaseTestdb1DaoImpl
	 * 实现返回的是spring的bean jdbcCall.</br> datalog 数据库的代理Dao
	 * org.iu9.testdb2.dao.BaseTestdb2DaoImpl
	 * 实现返回的是spring的beanjdbcCall_testdb2.</br>
	 * 
	 * @return
	 */
	public abstract SimpleJdbcCall getJdbcCall();

	/**
	 * 获取数据库方言,Dao 中注入spring bean.</br> 例如mysql的实现是 mysqlDialect. oracle的实现是
	 * oracleDialect. 如果使用了sequence 在entity使用@PKSequence实现自增 详见
	 * org.iu9.frame.dao.dialect.IDialect的实现
	 * 
	 * @return
	 */
	public abstract IDialect getDialect();
	
	
	/**
	 * 默认(return null)不记录日志,在多库情况下,用于区分数据库实例的日志记录表,
	 * 主要是为了兼容日志表(auditlog)的主键生成方式,UUID和自增.</br> testdb1 数据库的auditlog
	 * 是自增,testdb2 数据库的 auditlog 是UUID
	 * 
	 * @return
	 */
	
	public  IAuditLog getAuditLog(){
		return null;
	}
	/**
	 * 是否打印sql语句,默认false
	 * @return
	 */
	public boolean showsql(){
		return false;
	}
	
	/**
	 * 打印sql
	 * @param sql
	 */
	private void logInfoSql(String sql){
		if(showsql()){
			logger.error(sql);
		}
	}


	public BaseJdbcDaoImpl() {
	}

	@Override
	public <T> List<T> queryForList(Finder finder, Class<T> clazz)
			throws Exception {
		return queryForList(finder, clazz, null);

	}

	@Override
	public <T> List<T> queryForListByProc(Finder finder, Class<T> clazz)
			throws Exception {
		String procName = finder.getProcName();
		Map params=finder.getParams();
		List<T> list= (List<T>) getJdbcCall().withProcedureName(procName).execute(clazz, params);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryForList(Finder finder) {
		//打印sql
		logInfoSql(finder.getSql());
		return getJdbc().queryForList(finder.getSql(), finder.getParams());
	}

	@Override
	public Map<String, Object> queryForObject(Finder finder) throws Exception {
		Map<String, Object> map = null;
		try {
			//打印sql
			logInfoSql(finder.getSql());
			map = getJdbc().queryForMap(finder.getSql(), finder.getParams());
		} catch (EmptyResultDataAccessException e) {
			map = null;
		}

		return map;
	}
	
	
	@Override
	public List<Map<String, Object>> queryForList(Finder finder,Page page) throws Exception {
		String pageSql = getPageSql(page, finder);
		if (pageSql == null)
			return null;
		finder.setPageSql(pageSql);

		//打印sql
		logInfoSql(pageSql);

				return getJdbc().queryForList(pageSql, finder.getParams());
		
	}

	
	

	@Override
	public Integer update(Finder finder) throws Exception {
		//打印sql
		logInfoSql(finder.getSql());
		return getJdbc().update(finder.getSql(), finder.getParams());
	}

	@Override
	public <T> List<T> queryForList(Finder finder, Class<T> clazz, Page page)
			throws Exception {
		String pageSql = getPageSql(page, finder);
		if (pageSql == null)
			return null;
		finder.setPageSql(pageSql);

		//打印sql
		logInfoSql(pageSql);
		
		if (ClassUtils.isBaseType(clazz)) {
			if (getDialect().isRowNumber()) {
				return getJdbc().query(pageSql, finder.getParams(),
						new RowNumberSingleColumnRowMapper(clazz));
			} else {
				return getJdbc().queryForList(pageSql, finder.getParams(),
						clazz);
			}
		} else {
			return getJdbc().query(pageSql, finder.getParams(),
					ParameterizedBeanPropertyRowMapper.newInstance(clazz));
		}
	}

	@Override
	public <T> List<T> findListDateByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		EntityInfo entityInfoByEntity = ClassUtils
				.getEntityInfoByEntity(queryBean);
		String tableName = entityInfoByEntity.getTableName();
		String tableExt = entityInfoByEntity.getTableExt();
		if (StringUtils.isNotBlank(tableExt)) {
			tableName = tableName + tableExt;
		}
		if (finder == null) {
			finder = new Finder("SELECT * FROM " + tableName);
			finder.append(" WHERE 1=1 ");
			getFinderWhereByQueryBean(finder, queryBean);
		}
		int _index = RegexValidateUtils.getOrderByIndex(finder.getSql());
		if (_index > 0) {
			finder.setSql(finder.getSql().substring(0, _index));
		}

		if (page == null) {
			return this.queryForList(finder, clazz, page);
		}

		String sort = page.getSort();
		String order = page.getOrder();
		if (StringUtils.isNotBlank(order)) {
			order = order.trim();
			if (order.indexOf(" ") > -1 || order.indexOf(";") > -1) {// 认为是异常的,主要是防止注入
				return null;
			}

			if (RegexValidateUtils.getOrderByIndex(finder.getSql()) < 0) {
				finder.append(" order by ").append(order);
			}
			if (StringUtils.isNotBlank(sort)) {
				if ("asc".equals(sort.toLowerCase())
						&& (finder.getSql().toLowerCase().contains(" asc ") == false)) {
					finder.append(" asc ");
				} else if ("desc".equals(sort.toLowerCase())
						&& (finder.getSql().toLowerCase().contains(" desc ") == false)) {
					finder.append(" desc ");
				}
			}

		}

		List<T> datas = this.queryForList(finder, clazz, page);
		if (datas == null) {
			datas = new ArrayList<T>();
		}
		return datas;
	}

	@Override
	public Finder getFinderWhereByQueryBean(Finder finder, Object o)
			throws Exception {
		List<WhereSQLInfo> whereSQLInfo = ClassUtils.getWhereSQLInfo(o
				.getClass());

		Object alias_o = ClassUtils.getPropertieValue(
				GlobalStatic.frameTableAlias, o);
		String alias = null;
		if (alias_o != null) {
			alias = alias_o.toString();
		}

		if (CollectionUtils.isNotEmpty(whereSQLInfo)) {
			for (WhereSQLInfo whereinfo : whereSQLInfo) {
				String name = whereinfo.getName();
				Object value = ClassUtils.getPropertieValue(name, o);
				if (value == null || StringUtils.isBlank(value.toString())) {
					continue;
				}
				String wheresql = whereinfo.getWheresql();
				if (StringUtils.isNotBlank(alias)) {
					wheresql = alias + "." + wheresql;
				}
				String pname = wheresql.substring(wheresql.indexOf(":") + 1).trim();
				if(wheresql.toLowerCase().contains(" in ")&&pname.endsWith(")")){
					pname=pname.substring(0,pname.length()-1).trim();
				}
				if (wheresql.toLowerCase().contains(" like ")) {
					boolean qian = pname.trim().startsWith("%");
					boolean hou = pname.trim().endsWith("%");
					wheresql = wheresql.replaceAll("%", "");
					pname = pname.replaceAll("%", "");
					if (qian) {
						value = "%" + value;
					}
					if (hou) {
						value = value + "%";
					}

					finder.append(" and ").append(wheresql)
							.setParam(pname, value);
				} else {
					finder.append(" and ").append(wheresql)
							.setParam(pname, value);
				}
			}
		}

		return finder;

	}

	/**
	 * 根据page 和finder对象,拼装返回分页查询的语句
	 * 
	 * @param page
	 * @param finder
	 * @return
	 * @throws Exception
	 */
	private String getPageSql(Page page, Finder finder) throws Exception {
		String sql = finder.getSql();
		String orderSql = finder.getOrderSql();

		Map<String, Object> paramMap = finder.getParams();

		// 查询sql、统计sql不能为空
		if (StringUtils.isBlank(sql)) {
			return null;
		}
		if (RegexValidateUtils.getOrderByIndex(sql) > -1) {
			if (StringUtils.isBlank(orderSql)) {
				orderSql = sql.substring(RegexValidateUtils
						.getOrderByIndex(sql));
				sql = sql.substring(0, RegexValidateUtils.getOrderByIndex(sql));
			}
		} else {
			orderSql = " order by id";
		}

		if (page == null) {
			if (StringUtils.isNotBlank(orderSql))
				return sql + " " + orderSql;
			else
				return sql;
		}

		Integer count = null;

		if (finder.getCountFinder() == null) {
			String countSql = new String(sql);
			int order_int = RegexValidateUtils.getOrderByIndex(countSql);
			if (order_int > 1) {
				countSql = countSql.substring(0, order_int);
			}
			countSql = "SELECT count(*)  frame_row_count FROM (" + countSql
					+ ") temp_frame_noob_table_name WHERE 1=1 ";
			count = getJdbc().queryForInt(countSql, paramMap);
		} else {
			count = queryForObject(finder.getCountFinder(), Integer.class);
		}
		// 记录总行数(区分是否使用占位符)
		if (count == 0) {
			return null;
		} else {
			page.setTotalCount(count);
		}
		return getDialect().getPageSql(sql, orderSql, page);
	}

	@Override
	public <T> T queryForObject(Finder finder, Class<T> clazz) throws Exception {
		//打印sql
		logInfoSql(finder.getSql());
		T t = null;
		try {
			if (ClassUtils.isBaseType(clazz)) {
				t = (T) getJdbc().queryForObject(finder.getSql(),
						finder.getParams(), clazz);

			} else {
				t = (T) getJdbc().queryForObject(finder.getSql(),
						finder.getParams(),
						ParameterizedBeanPropertyRowMapper.newInstance(clazz));
			}
		} catch (EmptyResultDataAccessException e) {
			t = null;
		}

		return t;

	}

	/**
	 * 保存一个实体类,不记录日志
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private Object saveNoLog(Object entity) throws Exception {
		Class clazz = entity.getClass();
		// entity信息
		EntityInfo entityInfo = ClassUtils.getEntityInfoByEntity(entity);
		List<String> fdNames = ClassUtils.getAllDBFields(clazz);
		Map paramMap = new HashMap();// 对象内的参数
		String id = SecUtils.getUUID();// 主键

		String tableName = entityInfo.getTableName();

		// 获取 分表的扩展
		String tableExt = entityInfo.getTableExt();

		StringBuffer sql = new StringBuffer("INSERT INTO ").append(tableName)
				.append(tableExt).append("(");

		StringBuffer valueSql = new StringBuffer(" values(");

		Class<?> returnType = null;
		boolean isSequence = false;
		for (int i = 0; i < fdNames.size(); i++) {
			String fdName = fdNames.get(i);// 字段名称
			// fd.setAccessible(true);
			PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			Method setMethod = pd.getWriteMethod();// set 方法
			if (getMethod.isAnnotationPresent(Id.class)) {// 如果是ID,自动生成UUID
				returnType = getMethod.getReturnType();
				Object _getId = ClassUtils.getPKValue(entity); // 主键
				if (_getId == null) {
					if (returnType == String.class) {
						setMethod.invoke(entity, id);
					} else if (StringUtils.isNotBlank(entityInfo.getPksequence())) {// 如果包含主键序列注解
						String _sequence_value = entityInfo.getPksequence();
						if ((i + 1) == fdNames.size()) {
							sql.append(fdName).append(")");
							valueSql.append(_sequence_value).append(")");
							break;
						}
						sql.append(fdName).append(",");
						valueSql.append(_sequence_value).append(",");
						continue;
					} else {
						continue;
					}
				} else {
					id = _getId.toString();
				}
			}

			String mapKey = ":" + fdName;// 占位符

			Object fdValue = getMethod.invoke(entity);// 执行get方法返回一个Object, 字段值
			paramMap.put(fdName, fdValue);

			if ((i + 1) == fdNames.size()) {
				sql.append(fdName).append(")");
				valueSql.append(mapKey).append(")");
				break;
			}

			sql.append(fdName).append(",");
			valueSql.append(mapKey).append(",");

		}
		sql.append(valueSql);// sql语句
		//打印sql
		logInfoSql(sql.toString());
		if (returnType == String.class) {
			getJdbc().update(sql.toString(), paramMap);
			return id;

		} else {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource ss = new MapSqlParameterSource(paramMap);
			String _pkName = entityInfo.getPkName();
			if (StringUtils.isNotBlank(_pkName) && isSequence) {
				getJdbc().update(sql.toString(), ss, keyHolder,
						new String[] { _pkName });
			} else {
				getJdbc().update(sql.toString(), ss, keyHolder);
			}
			return keyHolder.getKey().longValue();
		}
	}

	@Override
	public Object save(Object entity) throws Exception {
		Object id = saveNoLog(entity);
		IAuditLog auditLog = getAuditLog();
		if(auditLog==null){
			return id;
		}

		String tableExt = ClassUtils.getTableExt(entity);
		if (StringUtils.isBlank(tableExt)) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			tableExt = GlobalStatic.tableExt + year;
		}

		
		auditLog.setOperationClass(entity.getClass().getName());
		auditLog.setOperationType(GlobalStatic.dataSave);
		auditLog.setOperatorName(SessionUser.getUserName());
		auditLog.setOperationClassId(id.toString());
		auditLog.setOperationTime(new Date());
		auditLog.setCurValue(entity.toString());
		auditLog.setPreValue("无");
		auditLog.setExt(tableExt);
		saveNoLog(auditLog);// 保存日志

		return id;
	}
	
	
	
	

	@Override
	public Integer update(Object entity) throws Exception {
		Class clazz = entity.getClass();
		//entity的信息
		EntityInfo entityInfo = ClassUtils.getEntityInfoByEntity(entity);
		List<String> fdNames = ClassUtils.getAllDBFields(clazz);
		Map paramMap = new HashMap();// 对象内的参数

		String tableName = entityInfo.getTableName();

		// 获取 分表的扩展
		String tableExt = entityInfo.getTableExt();

		StringBuffer sql = new StringBuffer("UPDATE ").append(tableName)
				.append(tableExt).append("  SET  ");

		StringBuffer whereSQL = new StringBuffer(" WHERE ");
		Object id = null;
		for (int i = 0; i < fdNames.size(); i++) {
			String fdName = fdNames.get(i);// 字段名称
			// fd.setAccessible(true);
			PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);

			Method getMethod = pd.getReadMethod();// 获得get方法
			//Method setMethod = pd.getWriteMethod();// set 方法

			Object fdValue = getMethod.invoke(entity);// 执行get方法返回一个Object, 字段值
			paramMap.put(fdName, fdValue);
			if (ClassUtils.isAnnotation(clazz, fdName, Id.class)) {// 如果是ID,生成
																	// WHERE 条件
				whereSQL.append(fdName).append("=:").append(fdName);
				id = fdValue;
				if (fdNames.size() > 1)// 至少还有一个字段!!!!
					continue;
			}

			if ((i + 1) == fdNames.size()) {
				sql.append(fdName).append("=:").append(fdName);
				break;
			}

			sql.append(fdName).append("=:").append(fdName).append(",");
		}

		sql.append(whereSQL);

	
		//打印sql
		logInfoSql(sql.toString());
	
		Object old_entity =null;
		IAuditLog auditLog = getAuditLog();
		if(auditLog!=null){
			old_entity = findByID(id, clazz, tableExt);
		}
		// 更新entity
		Integer hang = getJdbc().update(sql.toString(), paramMap);
		if(auditLog==null){
			return hang;
		}
		
		auditLog.setOperationClass(entity.getClass().getName());
		auditLog.setOperationType(GlobalStatic.dataUpdate);
		auditLog.setOperatorName(SessionUser.getUserName());
		auditLog.setOperationClassId(id.toString());
		auditLog.setOperationTime(new Date());
		auditLog.setPreValue(old_entity.toString());
		auditLog.setCurValue(entity.toString());

		String audit_tableExt = tableExt;
		if (StringUtils.isBlank(tableExt)) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			audit_tableExt = GlobalStatic.tableExt + year;
		}
		auditLog.setExt(audit_tableExt);

		
		// 保存日志
		saveNoLog(auditLog);
		return hang;

	}

	@Override
	public <T> T findByID(Object id, Class<T> clazz) throws Exception {
		return findByID(id, clazz, null);
	}

	@Override
	public <T> T findByID(Object id, Class<T> clazz, String tableExt)
			throws Exception {
		EntityInfo entityInfo = ClassUtils.getEntityInfoByClass(clazz);
		String tableName = entityInfo.getTableName();
		String idName = entityInfo.getPkName();
		if (StringUtils.isNotBlank(tableExt)) {
			tableName += tableExt;
		}
		String sql = "SELECT * FROM " + tableName + " WHERE " + idName + "=:id";
		Finder finder = new Finder(sql);
		finder.setParam("id", id);
		//打印sql
		logInfoSql(finder.getSql());
		return queryForObject(finder, clazz);
	}

	@Override
	public Object saveorupdate(Object entity) throws Exception {
		Object o = ClassUtils.getPKValue(entity);
		if (o == null)
			return save(entity);
		else
			return update(entity);

	}

	@Override
	public Object queryObjectByFunction(Finder finder) {
		String funName = finder.getFunName();
		Object o = null;
		if (StringUtils.isEmpty(funName)) {
			return null;
		}
		Map<String, Object> params = finder.getParams();
		if (params == null) {
			throw new InvalidDataAccessApiUsageException(
					"参数不能为空,大哥,spring jdbc 没有你期望的方法,你可以自己封装一个啊!");
		} else {
			try {
				return getJdbcCall().withFunctionName(funName).execute(params);
			} catch (EmptyResultDataAccessException e) {
				o = null;
			}

		}
		return o;
	}

	@Override
	public Map<String, Object> queryObjectByProc(Finder finder) {
		String procName = finder.getProcName();
		Map<String, Object> map = null;
		if (StringUtils.isEmpty(procName)) {
			return null;
		}
		Map<String, Object> params = finder.getParams();
		if (params == null) {
			throw new InvalidDataAccessApiUsageException(
					"参数不能为空,大哥,spring jdbc 没有你期望的方法,你可以自己封装一个啊!");

		} else {
			try {
				map = getJdbcCall().withProcedureName(procName).execute(params);
			} catch (EmptyResultDataAccessException e) {
				map = null;
			}
		}
		return map;
	}

	@Override
	public void deleteById(Object id, Class clazz) throws Exception {
		if (id == null)
			return;
		EntityInfo entityInfo = ClassUtils.getEntityInfoByClass(clazz);
		String tableName = entityInfo.getTableName();
		String idName = entityInfo.getPkName();
		String sql = "Delete FROM " + tableName + " WHERE " + idName + "=:id";
		Finder finder = new Finder(sql);
		finder.setParam("id", id);
		
		
		
		IAuditLog auditLog = getAuditLog();
		Object findEntityByID =null;
		
		if(auditLog!=null){
			findEntityByID = findByID(id, clazz);
		}
		//打印sql
		logInfoSql(finder.getSql());
		update(finder);
		
		if(auditLog==null){
			return;
		}
		
	

		/**
		 * 删除还有个 bug,就是删除分表的数据,日志记录有问题 没有分表
		 */

		int year = Calendar.getInstance().get(Calendar.YEAR);
		String tableExt = GlobalStatic.tableExt + year;

	
	
	
		auditLog.setOperationClass(clazz.getName());
		auditLog.setOperationType(GlobalStatic.dataDelete);
		auditLog.setOperatorName(SessionUser.getUserName());
		auditLog.setOperationClassId(id.toString());
		auditLog.setOperationTime(new Date());
		auditLog.setPreValue(findEntityByID.toString());
		auditLog.setExt(tableExt);
		auditLog.setCurValue("无");
		// 保存日志
		saveNoLog(auditLog);

	
	}



	@Override
	public <T> List<T> queryForListByFunction(Finder finder, Class<T> clazz)
			throws Exception {
		throw new Exception("不好意思,方法未实现!");
	}

	@Override
	public <T> T queryForObjectByProc(Finder finder, Class<T> clazz)
			throws Exception {
		T t = null;
		String procName = finder.getProcName();
		if (StringUtils.isEmpty(procName)) {
			return null;
		}
		Map<String, Object> params = finder.getParams();
		if (params == null) {
			throw new InvalidDataAccessApiUsageException(
					"参数不能为空,大哥,spring jdbc 没有你期望的方法,你可以自己封装一个啊!");

		} else {
			try {
				t = (T) getJdbcCall().withProcedureName(procName).execute(
						clazz, params);
			} catch (EmptyResultDataAccessException e) {
				t = null;
			}

		}

		return t;
	}

	@Override
	public <T> T queryForObjectByByFunction(Finder finder, Class<T> clazz)
			throws Exception {
		String funName = finder.getFunName();
		T t = null;
		if (StringUtils.isEmpty(funName)) {
			return null;
		}
		Map<String, Object> params = finder.getParams();
		if (params == null) {
			throw new InvalidDataAccessApiUsageException(
					"参数不能为空,大哥,spring jdbc 没有你期望的方法,你可以自己封装一个啊!");

		} else {
			try {
				t = getJdbcCall().withFunctionName(funName).executeFunction(
						clazz, params);
			} catch (EmptyResultDataAccessException e) {
				t = null;
			}
		}
		return t;
	}

	/**
	 * Entity作为查询的query bean,并返回Entity
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> T queryForObject(T entity) throws Exception {

		String tableName = getTableNameByEntity(entity);

		Finder finder = new Finder("SELECT * FROM ");
		finder.append(tableName).append("  WHERE 1=1 ");
		getFinderWhereByQueryBean(finder, entity);
		//打印sql
		logInfoSql(finder.getSql());
		return (T) queryForObject(finder, entity.getClass());

	}

	/**
	 * 根据 entity 确定表名
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private String getTableNameByEntity(Object entity) throws Exception {
		EntityInfo entityInfo = ClassUtils.getEntityInfoByEntity(entity);
		String tableName = entityInfo.getTableName();
		String ext = entityInfo.getTableExt();
		if (StringUtils.isNotBlank(ext)) {
			tableName = tableName + ext;
		}
		return tableName;
	}

	/**
	 * Entity作为查询的query bean,并返回Entity
	 * 
	 * @param entity
	 * @param page
	 *            分页对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> queryForListByEntity(T entity, Page page) throws Exception {
		String tableName = getTableNameByEntity(entity);
		Finder finder = new Finder("SELECT * FROM ");
		finder.append(tableName).append("  WHERE 1=1 ");
		getFinderWhereByQueryBean(finder, entity);
		//打印sql
		logInfoSql(finder.getSql());
		return (List<T>) queryForList(finder, entity.getClass(), page);

	}
	
	/*
	 //private String dataBaseType = null;
	//private String dataBaseVersion = null;
	//private List<String> dataBaseAllTables;
	@Override
	public String getDataBaseVersion() {
		if (dataBaseVersion != null) {
			return dataBaseVersion;
		}
		getDataBaseType();
		return dataBaseVersion;
	}

	@Override
	public String getDataBaseType() {
		if (dataBaseType != null) {
			return dataBaseType;
		}
		String databaseProductName = getJdbc().getJdbcOperations().execute(
				new ConnectionCallback<String>() {
					public String doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						String databaseProductName = connection.getMetaData()
								.getDatabaseProductName();
						dataBaseVersion = connection.getMetaData()
								.getDatabaseProductVersion();
						return databaseProductName;
					}
				});
		if (databaseProductName == null) {
			throw new NullPointerException("dataBase DriverName is null");
		}
		databaseProductName = databaseProductName.trim().toLowerCase();
		if (databaseProductName.contains("mysql")) {
			this.dataBaseType = "mysql";
		} else if (databaseProductName.contains("oracle")) {
			this.dataBaseType = "oracle";
		} else if (databaseProductName.contains("db2")) {
			this.dataBaseType = "db2";
		} else if (databaseProductName.contains("sqlserver")) {
			this.dataBaseType = "mssql";
		} else if (databaseProductName.contains("jtds")) {
			this.dataBaseType = "mssql";
		} else if (databaseProductName.contains("sybase")) {
			this.dataBaseType = "sybase";
		}

		return dataBaseType;
	}

	@Override
	public List<String> getDataBaseAllTables() {
		if (dataBaseAllTables != null)
			return dataBaseAllTables;
		dataBaseAllTables = getJdbc().getJdbcOperations().execute(
				new ConnectionCallback<List<String>>() {
					public List<String> doInConnection(Connection connection)
							throws SQLException, DataAccessException {
						List<String> tables = new ArrayList<String>();
						DatabaseMetaData dbMetaData = connection.getMetaData();
						// 可为:"TABLE", "VIEW", "SYSTEM   TABLE",
						// "GLOBAL   TEMPORARY", "LOCAL   TEMPORARY", "ALIAS",
						// "SYNONYM"
						String[] types = { "TABLE" };
						ResultSet tabs = dbMetaData.getTables(null, null, null,
								types);//只要表就好了 
						while (tabs.next()) {
							// 只要表名这一列
							tables.add(tabs.getString("TABLE_NAME"));
						}
						return tables;
					}
				});
		return dataBaseAllTables;
	}
	*/
}
