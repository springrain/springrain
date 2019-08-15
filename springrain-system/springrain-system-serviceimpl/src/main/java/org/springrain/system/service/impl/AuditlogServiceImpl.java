package org.springrain.system.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.AuditLog;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.IAuditlogService;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-04-02 10:17:31
 * @copyright {@link weicms.net}
 * @see org.springrain.frame.entity.springrain.service.impl.AuditLog
 */
@Service("auditlogService")
public class AuditlogServiceImpl extends BaseSpringrainServiceImpl implements IAuditlogService {

    @Override
    public String saveAuditlog(AuditLog entity) throws Exception {
        return (String) super.save(entity);
    }


    @Override
    public Integer updateAuditlog(AuditLog entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public AuditLog findAuditlogById(Object id) throws Exception {

        if (id == null) {
            return null;
        }

        AuditLog autidLog = new AuditLog();
        autidLog.setId(id.toString());

        /*
         * //使用finder 构建查询语句 Finder finder=new Finder("SELECT * FROM ");
         * //确定年度分表,实际可以根据ID的前四位确定年份,例如,我的Id前四位是2013 就是2013年的数据
         * finder.append("t_auditlog").append(autidLog.getExt()); //添加where 条件
         * finder.append(" WHERE id=:id"); //设置参数值 finder.setParam("id", id.toString());
         * return super.queryForObject(finder, AuditLog.class);
         */
        return super.queryForObject(autidLog);

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

}
