package org.iu9.testdb1.service;

import org.iu9.testdb1.entity.AuditLog;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-04-02 10:17:31
 * @see org.iu9.testdb1.service.AuditLog
 */
public interface IAuditlogService extends IBaseTestdb1Service {
	String saveAuditlog(AuditLog entity) throws Exception;
    String saveorupdateAuditlog(AuditLog entity) throws Exception;
	Integer updateAuditlog(AuditLog entity) throws Exception;
	AuditLog findAuditlogById(Object id) throws Exception;
	
}
