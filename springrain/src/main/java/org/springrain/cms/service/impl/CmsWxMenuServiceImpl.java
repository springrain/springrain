package org.springrain.cms.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsWxMenu;
import org.springrain.cms.service.ICmsWxMenuService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-06 17:23:12
 * @see org.springrain.cms.base.service.impl.CmsWxMenu
 */
@Service("cmsWxMenuService")
public class CmsWxMenuServiceImpl extends BaseSpringrainServiceImpl implements ICmsWxMenuService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      CmsWxMenu cmsWxMenu=(CmsWxMenu) entity;
	       return super.save(cmsWxMenu).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      CmsWxMenu cmsWxMenu=(CmsWxMenu) entity;
		 return super.saveorupdate(cmsWxMenu).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsWxMenu cmsWxMenu=(CmsWxMenu) entity;
	return super.update(cmsWxMenu);
    }
    @Override
	public CmsWxMenu findCmsWxMenuById(Object id) throws Exception{
	 return super.findById(id,CmsWxMenu.class);
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
	public List<CmsWxMenu> findParentMenuList(String siteId) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsWxMenu.class);
		finder.append(" where siteId=:siteId and pid is null ");
		finder.setParam("siteId", siteId);
		return super.queryForList(finder,CmsWxMenu.class);
	}

	@Override
	public List<CmsWxMenu> findChildMenuListByPid(String pid) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsWxMenu.class);
		finder.append(" where 1=1");
		if(StringUtils.isNotBlank(pid)){
			finder.append(" and pid=:pid").setParam("pid", pid);
		}
		List<CmsWxMenu> list = super.queryForList(finder,CmsWxMenu.class);
		return list;
	}
}
