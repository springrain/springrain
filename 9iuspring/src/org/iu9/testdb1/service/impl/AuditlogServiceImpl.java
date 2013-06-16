package org.iu9.testdb1.service.impl;

import java.io.File;
import java.util.List;

import org.iu9.frame.service.IBaseService;
import org.iu9.frame.util.Finder;
import org.iu9.frame.util.Page;
import org.iu9.testdb1.entity.AuditLog;
import org.iu9.testdb1.service.BaseTestdb1ServiceImpl;
import org.iu9.testdb1.service.IAuditlogService;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-04-02 10:17:31
 * @see org.iu9.testdb1.service.impl.AuditLog
 */
@Service("auditlogService")
public class AuditlogServiceImpl extends BaseTestdb1ServiceImpl implements IAuditlogService {

   
    @Override
	public String  saveAuditlog(AuditLog entity) throws Exception{
	       return (String) super.save(entity);
	}

    @Override
	public String  saveorupdateAuditlog(AuditLog entity) throws Exception{
	       return (String) super.saveorupdate(entity);
	}
	
	@Override
    public Integer updateAuditlog(AuditLog entity) throws Exception{
	return super.update(entity);
    }
    @Override
	public AuditLog findAuditlogById(Object id) throws Exception{
    	
    	if(id==null){
    		return null;
    	}
    	
    	AuditLog autidLog=new AuditLog();
    	//使用finder 构建查询语句
       Finder finder=new Finder("SELECT * FROM ");
       //确定年度分表,实际可以根据ID的前四位确定年份,例如,我的Id前四位是2013 就是2013年的数据
       finder.append("auditlog").append(autidLog.getExt());
       //添加where 条件
       finder.append(" WHERE id=:id");
       //设置参数值
       finder.setParam("id", id.toString());
         	
	 return super.queryForObject(finder, AuditLog.class);
	}

/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDateByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDateByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param baseService service 调用
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, IBaseService baseService, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,baseService,o);
		}
		
}
