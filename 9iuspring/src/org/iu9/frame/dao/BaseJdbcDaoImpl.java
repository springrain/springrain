package org.iu9.frame.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.iu9.frame.entity.IAuditLog;
import org.iu9.frame.util.ClassUtils;
import org.iu9.frame.util.EntityInfo;
import org.iu9.frame.util.Finder;
import org.iu9.frame.util.GlobalStatic;
import org.iu9.frame.util.Page;
import org.iu9.frame.util.SecUtils;
import org.iu9.frame.util.WhereSQLInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.ConnectionCallback;
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
 * 例如 testdb1数据的实现类是org.iu9.testdb1.dao.BaseTestdb1DaoImpl,testdb2数据的实现类是org.iu9.testdb2.dao.Basetestdb2DaoImpl</br>
 * 
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.frame.dao.BaseJdbcDaoImpl
 */
public abstract class BaseJdbcDaoImpl extends BaseLogger implements
		IBaseJdbcDao {
	private static List<Class> baseClassList = new ArrayList<Class>();
	private String dataBaseType = null;
	private String dataBaseVersion = null;
	private List<String> dataBaseAllTables;

	public BaseJdbcDaoImpl() {
	}

	/**
	 * 初始化基础的对象类型,用于查询一个字段时的判断映射</br> 主要用于spring jdbc对返回值的映射方法.</br>
	 * 例如:只查询返回UserId(Integer),不需要使用特殊的封装映射类, 查询返回User对象 就需要使用
	 * ParameterizedBeanPropertyRowMapper映射类
	 */
	static {
		baseClassList.add(BigInteger.class);
		baseClassList.add(Integer.class);
		baseClassList.add(String.class);
		baseClassList.add(BigDecimal.class);
		baseClassList.add(Long.class);
		baseClassList.add(Double.class);
		baseClassList.add(Float.class);
		baseClassList.add(Boolean.class);
		baseClassList.add(Short.class);
		baseClassList.add(Date.class);
		baseClassList.add(java.sql.Date.class);
		baseClassList.add(java.sql.Time.class);
		baseClassList.add(java.sql.Timestamp.class);
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
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList(Finder finder) {
		return getJdbc().queryForList(finder.getSql(), finder.getParams());
	}

	@Override
	public Map<String, Object> queryForObject(Finder finder) throws Exception {
		Map<String, Object> map = null;
		try {
			map = getJdbc().queryForMap(finder.getSql(), finder.getParams());
		} catch (EmptyResultDataAccessException e) {
			map = null;
		}

		return map;
	}

	@Override
	public Integer update(Finder finder) throws Exception {
		return getJdbc().update(finder.getSql(), finder.getParams());
	}

	@Override
	public <T> List<T> queryForList(Finder finder, Class<T> clazz, Page page)
			throws Exception {
		String pageSql = getPageSql(page, finder);
		if (pageSql == null)
			return null;
		finder.setPageSql(pageSql);

		if (baseClassList.contains(clazz)) {
			if (getDataBaseType() == null || getDataBaseType().equals("mssql")) {
				return getJdbc().query(pageSql, finder.getParams(),
						new CFSingleColumnRowMapper(clazz));
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
		EntityInfo entityInfoByEntity = ClassUtils.getEntityInfoByEntity(queryBean);
		String tableName=entityInfoByEntity.getTableName();
		String tableExt=entityInfoByEntity.getTableExt();
		if(StringUtils.isNotBlank(tableExt)){
			tableName=tableName+tableExt;
		}
		if (finder == null) {
			finder = new Finder("SELECT * FROM " + tableName);
			finder.append(" WHERE 1=1 ");
			getFinderWhereByQueryBean(finder, queryBean);
		}
		int _index = finder.getSql().toLowerCase().indexOf(" order by ");
		if (_index > 0) {
			finder.setSql(finder.getSql().substring(0, _index));
		}
		// getFinderWhereByQueryBean(finder, o);

		if (page == null) {
			return this.queryForList(finder, clazz, page);
		}

		String sort = page.getSort();
		String order = page.getOrder();
		if (StringUtils.isNotBlank(order)) {
			order = order.trim();
			// String[] orders=order.split(",");
			boolean istrue = true;
			if (order.indexOf(" ") > -1) {// 有空格的order by 认为是异常的,主要是防止注入
				istrue = false;
			}
			/*
			 * for(String s:orders){ if(allDBFields.contains(s)==false){
			 * istrue=false; break; } }
			 */

			if (istrue
					&& (finder.getSql().toLowerCase().contains(" order by ") == false)) {
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
		
		Object alias_o=ClassUtils.getPropertieValue(GlobalStatic.frameTableAlias, o);
		String alias=null;
		if(alias_o!=null){
			alias=alias_o.toString();
		}

		if (CollectionUtils.isNotEmpty(whereSQLInfo)) {
			for (WhereSQLInfo whereinfo : whereSQLInfo) {
				String name = whereinfo.getName();
				Object value = ClassUtils.getPropertieValue(name, o);
				if (value == null || StringUtils.isBlank(value.toString())) {
					continue;
				}
				String wheresql = whereinfo.getWheresql();
				if(StringUtils.isNotBlank(alias)){
					wheresql=alias+"."+wheresql;
				}
				String pname = wheresql.substring(wheresql.indexOf(":") + 1);
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
		if (StringUtils.isBlank(sql))
			return null;

		if (sql.toLowerCase().indexOf(" order by ") > -1) {
			if (StringUtils.isBlank(orderSql)) {
				orderSql = sql.substring(sql.toLowerCase()
						.indexOf(" order by "));
				sql = sql.substring(0, sql.toLowerCase().indexOf(" order by "));
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
		String countSql = "";
		int order_int = sql.toLowerCase().lastIndexOf(" order ");
		if (order_int > 1) {
			countSql = sql.substring(0, order_int);
		}
		countSql="SELECT count(*) FROM ("+countSql+") as temp_frame_noob_table_name";
		count = getJdbc().queryForInt(countSql, paramMap);
		} else {
			count = queryForObject(finder.getCountFinder(), Integer.class);
		}

		// 设置分页参数
		int pageSize = page.getPageSize();
		int pageNo = page.getPageIndex();
		// 记录总行数(区分是否使用占位符)

		if (count == 0) {
			return null;
		} else {
			page.setTotalCount(count);
		}
		// 去掉select
		if (sql.toLowerCase().indexOf("select") != -1) {
			int index = sql.toLowerCase().indexOf("select");
			sql = sql.substring(index + 6, sql.length());
		}
		String clums = " "
				+ sql.substring(0, sql.toLowerCase().lastIndexOf("from")) + " ";

		// 分页语句
		StringBuffer sb = new StringBuffer();

		if (getDataBaseType() == null || getDataBaseType().equals("mssql")) {
			sb.append("SELECT TOP ");
			sb.append(pageSize);
			sb.append(" * from (SELECT ROW_NUMBER() OVER (");
			sb.append(orderSql);
			sb.append(") AS mssqlserver_row_number,");
			sb.append(sql);
			sb.append("  ) A WHERE mssqlserver_row_number > ");
			sb.append(pageSize * (pageNo - 1));
			sb.append(" order by mssqlserver_row_number ");
		} else if (getDataBaseType().equals("mysql")) {
			sb.append("SELECT  ");
			sb.append(sql);
			if (StringUtils.isNotBlank(orderSql))
				sb.append(orderSql);
			sb.append(" limit ").append(pageSize * (pageNo - 1)).append(",")
					.append(pageSize);
		}

		return sb.toString();
	}

	@Override
	public <T> T queryForObject(Finder finder, Class<T> clazz) throws Exception {

		T t = null;
		try {
			if (baseClassList.contains(clazz)) {

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
		// 所有和数据库对应的字段
		// List<String> fdNames = ClassUtils.getAllDBFields(clazz);
		EntityInfo entityInfo = ClassUtils.getEntityInfoByEntity(entity);
		List<String> fdNames = entityInfo.getFdNames();
		Map paramMap = new HashMap();// 对象内的参数
		String id = SecUtils.getUUID();// 主键

		String tableName = entityInfo.getTableName();

		// 获取 分表的扩展
		String tableExt = entityInfo.getTableExt();
		/*
		 * if(StringUtils.isNotBlank(groupName)){ tableExt=(String)
		 * ClassUtils.getPropertieValue(groupName, entity); }
		 */
		StringBuffer sql = new StringBuffer("INSERT ").append(tableName)
				.append(tableExt).append("(");

		StringBuffer valueSql = new StringBuffer(" values(");

		Class<?> returnType = null;

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

		if (returnType == String.class) {
			getJdbc().update(sql.toString(), paramMap);
			return id;

		} else {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource ss = new MapSqlParameterSource(paramMap);
			getJdbc().update(sql.toString(), ss, keyHolder);
			return keyHolder.getKey().longValue();
		}
	}

	@Override
	public Object save(Object entity) throws Exception {
		Object id = saveNoLog(entity);
		IAuditLog auditLog = getAuditLog();
		String tableExt = ClassUtils.getTableExt(entity);
		if (StringUtils.isNotBlank(tableExt)) {
			auditLog.setExt(tableExt);
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
		// 所有和数据库对应的字段
		// List<String> fdNames = ClassUtils.getAllDBFields(clazz);
		EntityInfo entityInfo = ClassUtils.getEntityInfoByEntity(entity);
		List<String> fdNames = entityInfo.getFdNames();
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
			Method setMethod = pd.getWriteMethod();// set 方法

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

		Object old_entity = findByID(id, clazz, tableExt);
		IAuditLog auditLog = getAuditLog();
		auditLog.setOperationClass(entity.getClass().getName());
		auditLog.setOperationType(GlobalStatic.dataUpdate);
		auditLog.setOperatorName(SessionUser.getUserName());
		auditLog.setOperationClassId(id.toString());
		auditLog.setOperationTime(new Date());
		auditLog.setPreValue(old_entity.toString());
		auditLog.setCurValue(entity.toString());

		if (StringUtils.isNotBlank(tableExt)) {
			auditLog.setExt(tableExt);
		}

		// 更新entity
		Integer hang = getJdbc().update(sql.toString(), paramMap);
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
		// String tableName=ClassUtils.getTableName(clazz);
		// String idName=ClassUtils.getPKName(clazz);
		String tableName = entityInfo.getTableName();
		String idName = entityInfo.getPkName();
		if (StringUtils.isNotBlank(tableExt)) {
			tableName += tableExt;
		}
		String sql = "SELECT * FROM " + tableName + " WHERE " + idName + "=:id";
		Finder finder = new Finder(sql);
		finder.setParam("id", id);
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

		/*
		 * if(clazz.equals(IAuditLog.class)){ update(finder); return; }
		 */
		/**
		 * 删除还有个 bug,就是删除分表的数据,日志记录有问题,默认是日志的当前年分表.</br>
		 * 具体实现可以是ID的前四位是记录所属的年份,例如  2013,这一点根据实际业务实现吧
		 */


		Object findEntityByID = findByID(id, clazz);
		IAuditLog auditLog = getAuditLog();
		auditLog.setOperationClass(clazz.getName());
		auditLog.setOperationType(GlobalStatic.dataDelete);
		auditLog.setOperatorName(SessionUser.getUserName());
		auditLog.setOperationClassId(id.toString());
		auditLog.setOperationTime(new Date());
		auditLog.setPreValue(findEntityByID.toString());
		auditLog.setCurValue("无");
		// 保存日志
		saveNoLog(auditLog);

		update(finder);
	}

	@Override
	public <T> List<T> queryForListByFunciton(Finder finder, Class<T> clazz)
			throws Exception {

		throw new Exception("不好意思,方法未实现!");
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
	public <T> List<T> queryForList(T entity, Page page)
			throws Exception {
		String tableName = getTableNameByEntity(entity);
		Finder finder = new Finder("SELECT * FROM ");
		finder.append(tableName).append("  WHERE 1=1 ");
		getFinderWhereByQueryBean(finder, entity);
		return (List<T>) queryForList(finder, entity.getClass(), page);

	}

	

	/**
	 * 抽象方法.每个数据库的代理Dao都必须实现.在多库情况下,用于区分底层数据库的连接对象,对数据库进行增删改查.</br> 例如:oa_human
	 * 数据库的代理Dao com.centfor.cerp.dao.BaseCerpDaoImpl 实现返回的是spring的bean
	 * jdbc.</br> datalog 数据库的代理Dao com.centfor.datalog.dao.BaseDataLogDaoImpl
	 * 实现返回的是spring的bean jdbc_datalog.</br>
	 * 
	 * @return
	 */
	public abstract NamedParameterJdbcTemplate getJdbc();

	/**
	 * 抽象方法.每个数据库的代理Dao都必须实现.在多库情况下,用于区分数据库实例的日志记录表,主要是为了兼容日志表(auditlog)的主键生成方式,
	 * UUID和自增.</br> oa_human 数据库的auditlog 是自增,datalog是UUID
	 * 
	 * @return
	 */
	public abstract IAuditLog getAuditLog();

	/**
	 * 抽象方法.每个数据库的代理Dao都必须实现.在多库情况下,用于区分底层数据库的连接对象,调用数据库的函数和存储过程.</br>
	 * 例如:oa_human 数据库的代理Dao com.centfor.cerp.dao.BaseCerpDaoImpl
	 * 实现返回的是spring的bean jdbcCall.</br> datalog 数据库的代理Dao
	 * com.centfor.datalog.dao.BaseDataLogDaoImpl 实现返回的是spring的bean
	 * jdbcCall_datalog.</br>
	 * 
	 * @return
	 */
	public abstract SimpleJdbcCall getJdbcCall();

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

		// System.out.println(driverName+":"+dataBaseType);

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
								types/* 只要表就好了 */);
						while (tabs.next()) {
							// 只要表名这一列
							tables.add(tabs.getString("TABLE_NAME"));
						}
						return tables;
					}
				});
		return dataBaseAllTables;
	}

}
