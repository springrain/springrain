package org.springrain.lbs.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.lbs.entity.Peacemap;
import org.springrain.lbs.service.IPeacemapService;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-14 10:44:41
 * @see org.springrain.lbs.entity.base.service.impl.Peacemap
 */
@Service("peacemapService")
public class PeacemapServiceImpl extends BaseSpringrainServiceImpl implements IPeacemapService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Peacemap peacemap=(Peacemap) entity;
	       return super.save(peacemap).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Peacemap peacemap=(Peacemap) entity;
		 return super.saveorupdate(peacemap).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Peacemap peacemap=(Peacemap) entity;
	return super.update(peacemap);
    }
    @Override
	public Peacemap findPeacemapById(Object id) throws Exception{
	 return super.findById(id,Peacemap.class);
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
	
		/**
		 * 根据sid和cid获取数据
		*/
		@Override
	public List<Peacemap> findPeacemapBySidCid(String sid, String cid, Class<Peacemap> clazz, Page page) 
			throws Exception {
		// TODO Auto-generated method stub
		Finder finder=Finder.getSelectFinder(Peacemap.class);
		finder.append(" where 1=1");
		if(StringUtils.isNotBlank(sid)){
			finder.append(" and siteId=:siteId").setParam("siteId", sid);
		}
		if(StringUtils.isNotBlank(cid)){
			finder.append(" and channelId=:channelId").setParam("channelId", cid);
		}
		finder.append(" order by sort desc");
		return super.queryForList(finder, clazz, page);
	}

		@Override
		public List<Peacemap> findPeacemapByName(String sid, String cid, String name, Class<Peacemap> clazz, Page page)
				throws Exception {
			// TODO Auto-generated method stub
			Finder finder=Finder.getSelectFinder(Peacemap.class);
			finder.append(" where 1=1");
			if(StringUtils.isNotBlank(sid)){
				finder.append(" and siteId=:siteId").setParam("siteId", sid);
			}
			if(StringUtils.isNotBlank(cid)){
				finder.append(" and channelId=:channelId").setParam("channelId", cid);
			}
			if(StringUtils.isNotBlank(name)){
				finder.append(" and name=:name").setParam("name", name);
			}
			finder.append(" order by sort desc");
			return super.queryForList(finder, clazz, page);
		}
	
	
	

	
		
	

}
