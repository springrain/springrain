package org.springrain.cms.base.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsLink;
import org.springrain.cms.base.entity.CmsSite;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.cms.base.service.ICmsSiteService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:21
 * @see org.springrain.demo.service.impl.CmsSite
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
		if(StringUtils.isBlank(cmsSite.getId())){
			cmsSite.setUserId(SessionUser.getUserId());
			return this.saveCmsSite(cmsSite);
		}else{
			return this.updateCmsSite(cmsSite);
		}
		
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
	    cmsLink.setFtlfile("/u/"+id+"/index.html");
	    cmsLink.setNodeftlfile("/u/"+id+"/channel.html");
	    
	    
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
		      
		 return id;
	 
	}
	
    @Override
	public CmsSite findCmsSiteById(String id) throws Exception{
	 return super.findById(id,CmsSite.class);
	}



	@Override
	public String updateCmsSite(CmsSite cmsSite) throws Exception {
		super.update(cmsSite,true);
		
		return null;
	}

	@Override
	@Cacheable(value = GlobalStatic.cacheKey, key = "'findSiteTypeById_'+#siteId")
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
