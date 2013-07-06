package org.iu9.testdb1.service;

import org.iu9.testdb1.entity.Subsystem;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 16:03:00
 * @see org.iu9.testdb1.service.Subsystem
 */
public interface ISubsystemService extends IBaseTestdb1Service {
/**
	 * 保存 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	String saveSubsystem(Subsystem entity) throws Exception;
	/**
	 * 修改或者保存,根据id是否为空判断
	 * @param entity
	 * @return
	 * @throws Exception
	 */
    String saveorupdateSubsystem(Subsystem entity) throws Exception;
	 /**
     * 更新
     * @param entity
     * @return
     * @throws Exception
     */
	Integer updateSubsystem(Subsystem entity) throws Exception;
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Subsystem findSubsystemById(Object id) throws Exception;
	
	
}
