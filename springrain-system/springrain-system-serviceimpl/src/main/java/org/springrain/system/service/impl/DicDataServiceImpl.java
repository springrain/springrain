package org.springrain.system.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.DicData;
import org.springrain.system.service.IDicDataService;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author weicms.net<Auto generate>
 * @version 2013-07-31 15:56:45
 */
@Service("dicDataService")
public class DicDataServiceImpl extends BaseSpringrainServiceImpl implements IDicDataService {

    @Override
    public String save(IBaseEntity entity) throws Exception {
        DicData dicData = (DicData) entity;
        return (String) super.save(dicData);
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        DicData dicData = (DicData) entity;
        return super.update(dicData);
    }

    @Override
    public DicData findDicDataById(Object id) throws Exception {
        return super.findById(id, DicData.class);
    }

    @Override
    public DicData findDicDataById(String id, String pathtypekey) throws Exception {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(pathtypekey)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(DicData.class)
                .append("  where id=:id and  typekey=:typekey   order by name ");
        finder.setParam("typekey", pathtypekey).setParam("id", id);
        DicData dicData = super.queryForObject(finder, DicData.class);


        return dicData;
    }

    @Override
    // @Cacheable(value = GlobalStatic.cacheKey, key =
    // "'findListDicData_'+#pathtypekey")
    public List<DicData> findListDicData(String pathtypekey, Page page, DicData dicData) throws Exception {
        if (StringUtils.isBlank(pathtypekey)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE typekey=:typekey ");
        finder.setParam("typekey", pathtypekey);
        return super.queryForList(finder, DicData.class, page);
    }

    @Override
    // @CacheEvict(value = GlobalStatic.cacheKey, key =
    // "'findListDicData_'+#pathtypekey")
    public void deleteDicDataById(String id, String pathtypekey) throws Exception {
        String key = "findListDicData_" + pathtypekey;
        super.evictByKey(GlobalStatic.cacheKey, key);

        if (StringUtils.isBlank(id) || StringUtils.isBlank(pathtypekey)) {
            return;
        }

        Finder finder = Finder.getDeleteFinder(DicData.class).append(" where id=:id and  typekey=:typekey  ");
        finder.setParam("typekey", pathtypekey).setParam("id", id);
        super.update(finder);

    }

    @Override
    // @CacheEvict(value = GlobalStatic.cacheKey, key =
    // "'findListDicData_'+#pathtypekey")
    public void deleteDicDataByIds(List<String> ids, String pathtypekey) throws Exception {

        String key = "findListDicData_" + pathtypekey;
        super.evictByKey(GlobalStatic.cacheKey, key);

        if (CollectionUtils.isEmpty(ids) || StringUtils.isBlank(pathtypekey)) {
            return;
        }

        Finder finder = Finder.getDeleteFinder(DicData.class).append(" where id in(:ids) and  typekey=:typekey  ");
        finder.setParam("typekey", pathtypekey).setParam("ids", ids);
        super.update(finder);

    }

    @Override
    public String findCacheNameById(String id, String pathtypekey) throws Exception {
        List<DicData> findListDicData = findListDicData(pathtypekey, null, null);
        if (CollectionUtils.isEmpty(findListDicData) || StringUtils.isBlank(id)) {
            return null;
        }
        for (DicData dicData : findListDicData) {
            if (dicData.getId().equals(id)) {
                return dicData.getName();
            }
        }

        return null;
    }

    @Override
    public List<DicData> findDisDataByPathtypeKey(String pathtypekey, String sort, Integer active) throws Exception {
        if (StringUtils.isBlank(pathtypekey)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(DicData.class).append(" WHERE typekey=:typekey and active = :active ");
        finder.setParam("typekey", pathtypekey).setParam("active", active);
        if (StringUtils.isNotBlank(sort)) {
            finder.append(" order by sortno asc");
        }
        return super.queryForList(finder, DicData.class);
    }

    @Override
    public DicData findByCodeAndTypeKey(String code, String typeKey) throws Exception {
        Finder finder = Finder.getSelectFinder(DicData.class)
                .append(" where code=:code and typekey = :typeKey and active=1 ");
        finder.setParam("code", code).setParam("typeKey", typeKey);
        List<DicData> datas = super.queryForList(finder, DicData.class);
        if (!CollectionUtils.isEmpty(datas)) {
            return datas.get(0);
        }
        return null;
    }
}
