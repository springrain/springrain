package org.springrain.system.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.CommonEnum;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.Role;
import org.springrain.system.entity.User;
import org.springrain.system.service.IRoleService;
import org.springrain.system.service.IUserRoleOrgService;

import java.util.List;


/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */

@Service("roleService")
public class RoleServiceImpl extends BaseSpringrainServiceImpl implements IRoleService {


    @Override
    public String save(IBaseEntity entity) throws Exception {
        Role role = (Role) entity;
        return super.save(entity).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Role role = (Role) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findRoleById_" + role.getId());
        return super.update(entity);
    }

    @Override
    public Role findRoleById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findRoleById_" + id;
        Role role = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, Role.class);
        if (role != null) {
            return role;
        }
        role = super.findById(id, Role.class);
        if (role == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, role);
        return role;
    }

    @Override
    public List<Role> findRoleList(Page<Role> page) throws Exception {
        Finder finder = Finder.getSelectFinder(Role.class)
                .append(" WHERE active=:active");
        finder.setParam("active", CommonEnum.ACTIVE.未删除.getState());

        // 处理查询条件
        Role queryBean = page.getData();
        if(queryBean != null) {

            if(StringUtils.isNotBlank(queryBean.getName())) {
                // 按姓名查询
                finder.append(" AND name like :name")
                        .setParam("name", "%" + queryBean.getName() + "%");
            }

            if(StringUtils.isNotBlank(queryBean.getCode())) {
                // 按手机号查询
                finder.append(" AND code like :code")
                        .setParam("code", "%" + queryBean.getCode() + "%");
            }


            if(page.getBeginTime()!=null && page.getEndTime()!=null) {
                finder.append(" AND createTime>=:beginTime AND createTime<=:endTime ")
                        .setParam("beginTime", page.getBeginTime())
                        .setParam("endTime", DateUtils.addDays(page.getEndTime(),1));
            }
        }

        return this.queryForList(finder, Role.class, page);
    }


}
