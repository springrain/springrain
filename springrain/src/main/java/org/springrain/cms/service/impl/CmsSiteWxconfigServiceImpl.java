package org.springrain.cms.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsSiteWxconfig;
import org.springrain.cms.service.ICmsSiteWxconfigService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IUserOrgService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-06 11:38:43
 * @see org.springrain.cms.base.service.impl.CmsSiteWxconfig
 */
@Service("cmsSiteWxconfigService")
public class CmsSiteWxconfigServiceImpl extends BaseSpringrainServiceImpl implements ICmsSiteWxconfigService {

	@Resource
    private IUserOrgService userOrgService;

	@Override
	public String  save(Object entity ) throws Exception{
	      CmsSiteWxconfig cmsSiteWxconfig=(CmsSiteWxconfig) entity;
	       return super.save(cmsSiteWxconfig).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      CmsSiteWxconfig cmsSiteWxconfig=(CmsSiteWxconfig) entity;
		 return super.saveorupdate(cmsSiteWxconfig).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsSiteWxconfig cmsSiteWxconfig=(CmsSiteWxconfig) entity;
	return super.update(cmsSiteWxconfig);
    }
    @Override
	public CmsSiteWxconfig findCmsSiteWxconfigById(Object id) throws Exception{
	 return super.findById(id,CmsSiteWxconfig.class);
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
	public List<CmsSiteWxconfig> findWxconfigByUserId(String userId) throws Exception {
		List<String> orgIds = userOrgService.findOrgIdsByManagerUserId(userId);
		Finder finder = new Finder("SELECT a.* FROM cms_site_wxconfig a INNER JOIN cms_site b ON a.siteId = b.id WHERE a.active=1 AND b.active=1 AND b.orgId in (:orgIds)");
		finder.setParam("orgIds", orgIds);
		return super.queryForList(finder, CmsSiteWxconfig.class);
	}

}
