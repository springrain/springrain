package org.springrain.cms.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.entity.CmsSite;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:21
 * @see org.springrain.cms.entity.demo.service.impl.CmsSite
 */
@Service("cmsSiteService")
public class CmsSiteServiceImpl extends BaseSpringrainServiceImpl implements ICmsSiteService {

	@Resource
   private ITableindexService tableindexService;
	
	@Resource
	private ICmsLinkService cmsLinkService;
	
	@Override
	public Object saveorupdate(Object entity) throws Exception {
		CmsSite cmsSite = (CmsSite) entity;
		String siteId;
		if(StringUtils.isBlank(cmsSite.getId())){
			cmsSite.setUserId(SessionUser.getUserId());
			siteId = this.saveCmsSite(cmsSite);
			//清除站点下的站点列表缓存
		}else{
			siteId = this.updateCmsSite(cmsSite);
		}
		evictByKey("siteList", "'findListDataByFinder'");
		return siteId;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		List<CmsSite> siteList;
		if(page.getPageIndex()==1){
			siteList = getByCache("siteList", "'findListDataByFinder'", ArrayList.class);
			if(CollectionUtils.isEmpty(siteList)){//缓存中没有
				siteList =  super.findListDataByFinder(finder, page, CmsSite.class, queryBean);
				putByCache("siteList", "'findListDataByFinder'", siteList);
			}
		}else{
			siteList =  super.findListDataByFinder(finder, page, CmsSite.class, queryBean);
		}
		
		return (List<T>) siteList;
	}
	
    @Override
	public String  saveCmsSite(CmsSite cmsSite ) throws Exception{
    	if(cmsSite==null){
    		return null;
    	}
	    
	    String id= tableindexService.updateNewId(CmsSite.class);
	    if(StringUtils.isEmpty(id)){
	    	return null;
	    }
	    cmsSite.setId(id);
	    
	    super.save(cmsSite);
	    

	    
	    //保存 相应的 link 链接
	    
	    CmsLink cmsLink=new CmsLink();
	    
	    cmsLink.setBusinessId(id);
	    cmsLink.setSiteId(id);
	    cmsLink.setName(cmsSite.getName());
	    cmsLink.setModelType(0);//网站
	    cmsLink.setLookcount(1);
	    cmsLink.setStatichtml(0);//默认不静态化
	    cmsLink.setActive(0);//默认可以使用
	    cmsLink.setSortno(1);
	    //首页默认
	    String _index="/f/"+cmsSite.getSiteType()+"/"+id+"/index";
	    cmsLink.setDefaultLink(_index);
	    cmsLink.setLink(_index);
	    //设置模板路径
	    cmsLink.setFtlfile("/u/"+id+"/index");
	    cmsLink.setNodeftlfile("/u/"+id+"/channel");
	    
	    
	    cmsLinkService.save(cmsLink);
	    
	    
	   File t_file=new File(GlobalStatic.webinfodir+"/u/"+id+"/");
	   if(!t_file.exists()){
		   t_file.mkdirs();
		 }
	    
	    
	    
	    
	    
		 String str_jsdir=GlobalStatic.rootdir+"/u/"+id+"/js/";
		 String str_cssdir=GlobalStatic.rootdir+"/u/"+id+"/css/";
		 String str_imgdir=GlobalStatic.rootdir+"/u/"+id+"/img/";
		 
		 String str_upload=GlobalStatic.rootdir+"/upload/u/"+id+"/";
		 
		 File jsdir=new File(str_jsdir);
		 if(!jsdir.exists()){
			 jsdir.mkdirs();
		 }
		 File cssdir=new File(str_cssdir);
		 if(!cssdir.exists()){
			 cssdir.mkdirs();
		 }
		 File imgdir=new File(str_imgdir);
		 if(!imgdir.exists()){
			 imgdir.mkdirs();
		 }
		 
		 File uploaddir=new File(str_upload);
		 if(!uploaddir.exists()){
			 uploaddir.mkdirs();
		 }
		 
		 putByCache(id, "'findCmsSiteById_'+#"+id, cmsSite);
		 
		 return id;
	 
	}
	
    @Override
	public CmsSite findCmsSiteById(String id) throws Exception{
    	CmsSite site = getByCache(id, "'findCmsSiteById_'+#"+id, CmsSite.class);
    	if(site == null){
    		site = super.findById(id,CmsSite.class);
    		putByCache(id, "'findCmsSiteById_'+#"+id, site);
    	}
    	return site;
	}



	@Override
	public String updateCmsSite(CmsSite cmsSite) throws Exception {
		super.update(cmsSite,true);
		String siteId = cmsSite.getId();
		evictByKey(siteId, "'findCmsSiteById_'+#"+siteId);
		putByCache(siteId, "'findCmsSiteById_'+#"+siteId, cmsSite);
		return null;
	}

	@Override
	public Integer findSiteTypeById(String siteId) throws Exception {
		if(StringUtils.isBlank(siteId)){
			return null;
		}
		Finder finder=Finder.getSelectFinder(CmsSite.class, "siteType").append(" WHERE id=:siteId ");
		finder.setParam("siteId", siteId);
		
		return super.queryForObject(finder, Integer.class);
	}

	@Override
	public List<CmsSite> findSiteByUserId(String userId) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsSite.class).append(" WHERE userId=:userId");
		finder.setParam("userId", userId);
		return super.queryForList(finder, CmsSite.class);
	}
	
}
