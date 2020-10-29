package org.springrain.system.service;

import java.util.List;

import org.springrain.frame.entity.AuditLog;
import org.springrain.frame.util.Page;
import org.springrain.rpc.annotation.RpcServiceAnnotation;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-04-02 10:17:31
 * @copyright {@link weicms.net}
 * @see org.springrain.frame.entity.springrain.service.AuditLog
 */
@RpcServiceAnnotation
public interface IAuditlogService extends IBaseSpringrainService {
    String saveAuditlog(AuditLog entity) throws Exception;


    Integer updateAuditlog(AuditLog entity) throws Exception;

    AuditLog findAuditlogById(Object id) throws Exception;
}
