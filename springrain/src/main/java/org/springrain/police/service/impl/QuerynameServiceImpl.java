package org.springrain.police.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import  org.springrain.frame.util.Page;
import org.springrain.police.dto.QueryNameResult;
import org.springrain.police.entity.Queryname;
import org.springrain.police.service.IQuerynameService;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-23 09:35:23
 * @see org.springrain.police.entity.base.service.impl.Queryname
 */
@Service("querynameService")
public class QuerynameServiceImpl extends BaseSpringrainServiceImpl implements IQuerynameService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Queryname queryname=(Queryname) entity;
	      queryname.setLeadTime(new Date());
	       return super.save(queryname).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Queryname queryname=(Queryname) entity;
		 return super.saveorupdate(queryname).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Queryname queryname=(Queryname) entity;
	return super.update(queryname);
    }
    @Override
	public Queryname findQuerynameById(Object id) throws Exception{
	 return super.findById(id,Queryname.class);
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
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	

	@Override
	public List<Map<String, Object>> findQueryNameResultList(String name, Class<QueryNameResult> clazz) throws Exception {
		Finder finder= new Finder();//Finder.
		finder.append("select district,count(district) as size  from "+Finder.getTableName(Queryname.class));
		if(StringUtils.isNotBlank(name)){
			finder.append(" where name=:name").setParam("name", name);
		}
		finder.append(" group by district");
		
		return super.queryForList(finder);
	}

}
