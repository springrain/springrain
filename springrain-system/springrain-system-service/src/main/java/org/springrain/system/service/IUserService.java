package org.springrain.system.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.rpc.sessionuser.UserVO;
import org.springrain.system.entity.User;

/**
 * 用户管理查询的基础Servcie
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 */
@RpcServiceAnnotation
public interface IUserService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    User findUserById(String id) throws Exception;

    /**
     * 根据userId查找UserVO
     *
     * @param userId
     * @return
     * @throws Exception
     */
    UserVO findUserVOByUserId(String userId) throws Exception;


    /**
     * 根据账号密码 验证是否能够登录,userType用于区分用户类型
     *
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    User findLoginUser(String account, String password, Integer userType) throws Exception;

}
