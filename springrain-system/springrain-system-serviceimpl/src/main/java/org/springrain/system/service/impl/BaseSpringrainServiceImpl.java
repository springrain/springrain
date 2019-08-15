package org.springrain.system.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.service.BaseServiceImpl;
import org.springrain.frame.util.Finder;
import org.springrain.system.service.IBaseSpringrainService;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service("baseSpringrainService")
public class BaseSpringrainServiceImpl extends BaseServiceImpl implements IBaseSpringrainService {

    @Resource
    IBaseJdbcDao baseSpringrainDao;

    public BaseSpringrainServiceImpl() {
    }

    @Override
    public IBaseJdbcDao getBaseDao() {
        return baseSpringrainDao;
    }

    @Override
    public <T> String saveImportExcelFile(File excel, Class<T> clazz, String siteId, String businessId)
            throws Exception {
        return super.saveImportExcelFile(excel, clazz, siteId, businessId);
    }

    @Override
    public String saveFromExcel(IBaseEntity entity, int index, boolean istest, List<String> listTitle) throws Exception {
        if (istest) {
            return null;
        }
        return save(entity).toString();
    }

    @Override
    public Integer update(Finder finder) throws Exception {
        return super.update(finder);
    }

    @Override
    public Object save(IBaseEntity entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public Integer update(IBaseEntity entity, boolean onlyupdatenotnull) throws Exception {
        return super.update(entity, onlyupdatenotnull);
    }

    @Override
    public List<Integer> update(List list, boolean onlyupdatenotnull) throws Exception {
        return super.update(list, onlyupdatenotnull);
    }

    @Override
    public void deleteById(Object id, Class clazz) throws Exception {
        super.deleteById(id, clazz);
    }


    @Override
    public List<Integer> update(List list) throws Exception {
        return super.update(list);
    }

    @Override
    public List<Integer> save(List list) throws Exception {
        return super.save(list);
    }

}
