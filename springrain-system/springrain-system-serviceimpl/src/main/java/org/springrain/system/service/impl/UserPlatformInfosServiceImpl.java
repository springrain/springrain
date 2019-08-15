package org.springrain.system.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.UserPlatformInfos;
import org.springrain.system.service.IUserPlatformInfosService;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2017-07-31 16:41:34
 * @copyright {@link weicms.net}
 * @see org.springrain.cms.base.service.impl.UserPlatformInfos
 */
@Service("userPlatformInfosService")
public class UserPlatformInfosServiceImpl extends BaseSpringrainServiceImpl implements IUserPlatformInfosService {

    @Override
    public String save(IBaseEntity entity) throws Exception {
        UserPlatformInfos userPlatformInfos = (UserPlatformInfos) entity;
        return super.save(userPlatformInfos).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        UserPlatformInfos userPlatformInfos = (UserPlatformInfos) entity;
        return super.update(userPlatformInfos);
    }

    @Override
    public UserPlatformInfos findUserPlatformInfosById(Object id) throws Exception {
        return super.findById(id, UserPlatformInfos.class);
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
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz, Object o) throws Exception {
        return super.findListDataByFinder(finder, page, clazz, o);
    }

    @Override
    public List<UserPlatformInfos> findUserPlatformsByUserId(String userId) throws Exception {
        UserPlatformInfos infos = new UserPlatformInfos();
        infos.setUserId(userId);
        return findListDataByFinder(null, null, UserPlatformInfos.class, infos);
    }

    @Override
    public UserPlatformInfos findUserPlateformByUserIdAndType(String userId, Integer type) throws Exception {
        Finder finder = Finder.getSelectFinder(UserPlatformInfos.class);
        finder.append(" where userId=:userId and deviceType=:deviceType ");
        finder.setParam("deviceType", type).setParam("userId", userId);
        return queryForObject(finder, UserPlatformInfos.class);

    }

    @Override
    public String findTokenIdByUserIdAndType(String userId, Integer type) throws Exception {
        UserPlatformInfos userPlatformInfos = findUserPlateformByUserIdAndType(userId, type);
        if (userPlatformInfos != null) {
            return userPlatformInfos.getOpenId();
        }
        return null;
    }
}
