package org.springrain.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.rpc.sessionuser.UserVO;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserService;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 */
@Service("userService")
public class UserServiceImpl extends BaseSpringrainServiceImpl implements IUserService {


    @Override
    public String save(IBaseEntity entity) throws Exception {
        User user = (User) entity;
        return super.save(entity).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        User user = (User) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey,"findUserById_"+user.getId());
        return super.update(entity);
    }

    @Override
    public User findUserById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findUserById_" + id;
        User user = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, User.class);
        if (user != null) {
            return user;
        }
        user = super.findById(id, User.class);
        if (user == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, user);
        return user;
    }

    /**
     * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
     *
     * @param finder
     * @param page
     * @param clazz
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
                                            Object o) throws Exception {
        return super.findListDataByFinder(finder, page, clazz, o);
    }

    @Override
    public UserVO findUserVOByUserId(String userId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return null;
        }
        User user = findUserById(userId);
        if (user == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setAccount(user.getAccount());
        userVO.setEmail(user.getEmail());
        userVO.setUserName(user.getUserName());
        userVO.setUserType(user.getUserType());
        userVO.setActive(user.getActive());


        return userVO;
    }

    @Override
    public String findUserIdByOpenId(String openId) throws Exception {

        if (StringUtils.isBlank(openId)){
            return null;
        }
        Finder finder=Finder.getSelectFinder(User.class," id ").append(" WHERE openId=:openId ").setParam("openId",openId);
        return super.queryForObject(finder,String.class);
    }


    @Override
    public User findLoginUser(String account, String password, Integer userType) throws Exception {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return null;
        }
        // Finder finder = new Finder("SELECT * FROM t_user WHERE account=:account ");
        Finder finder = Finder.getSelectFinder(User.class).append(" WHERE  account=:account  and password=:password  and active=1 ");
        finder.setParam("account", account).setParam("password", password);

        return super.queryForObject(finder, User.class);
    }


}
