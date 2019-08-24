package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.weixin.entity.WxMenu;
import org.springrain.weixin.service.IWxMenuService;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2017-02-06 17:23:12
 */
@Service("wxMenuService")
public class WxMenuServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxMenuService {


    @Override
    public String save(IBaseEntity entity) throws Exception {
        WxMenu cmsWxMenu = (WxMenu) entity;
        return super.save(cmsWxMenu).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        WxMenu cmsWxMenu = (WxMenu) entity;
        return super.update(cmsWxMenu);
    }

    @Override
    public WxMenu findCmsWxMenuById(Object id) throws Exception {
        return super.findById(id, WxMenu.class);
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
    public List<WxMenu> findParentMenuList(String siteId) throws Exception {
        Finder finder = Finder.getSelectFinder(WxMenu.class);
        finder.append(" where siteId=:siteId and pid is null ");
        finder.setParam("siteId", siteId);
        return super.queryForList(finder, WxMenu.class);
    }

    @Override
    public List<WxMenu> findChildMenuListByPid(String pid) throws Exception {
        Finder finder = Finder.getSelectFinder(WxMenu.class);
        finder.append(" where 1=1");
        if (StringUtils.isNotBlank(pid)) {
            finder.append(" and pid=:pid").setParam("pid", pid);
        }
        List<WxMenu> list = super.queryForList(finder, WxMenu.class);
        return list;
    }


}
