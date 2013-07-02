package org.iu9.testdb1.dao;

import javax.annotation.Resource;

import org.iu9.frame.dao.BaseJdbcDaoImpl;
import org.iu9.frame.dao.dialect.IDialect;
import org.iu9.frame.entity.IAuditLog;
import org.iu9.testdb1.entity.AuditLog;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 *   9iuspring项目的基础Dao,代理testdb1数据库
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.testdb1.dao.BaseTestdb1DaoImpl
 */
@Repository("baseTestdb1Dao")
public class BaseTestdb1DaoImpl extends BaseJdbcDaoImpl implements IBaseTestdb1Dao{

	/**
	 * testdb1  数据库的jdbc,对应 spring配置的 jdbc bean
	 */
	@Resource
	NamedParameterJdbcTemplate jdbc;
	/**
	 * testdb1  数据库的jdbcCall,对应 spring配置的 jdbcCall bean
	 */
	@Resource
	public SimpleJdbcCall jdbcCall;
/**
 * mysqlDialect 是mysql的方言,springBean的name,可以参考 IDialect的实现
 */
	@Resource
	public IDialect mysqlDialect;

	public BaseTestdb1DaoImpl() {
	}


	/**
	 * 实现父类方法,testdb1  数据库的jdbc,对应 spring配置的 jdbc bean
	 */
	@Override
	public SimpleJdbcCall getJdbcCall() {
		return this.jdbcCall;
	}
	/**
	 * 实现父类方法,testdb1  数据库的jdbcCall,对应 spring配置的 jdbcCall bean
	 */
	@Override
	public NamedParameterJdbcTemplate getJdbc() {
		return jdbc;
	}


/**
 * 实现父类方法,返回记录日志的Entity接口实现
 */
	@Override
	public IAuditLog getAuditLog() {
		return new AuditLog();
	}


@Override
public IDialect getDialect() {
	return mysqlDialect;
}

}
