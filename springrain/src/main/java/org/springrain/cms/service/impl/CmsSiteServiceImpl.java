package org.springrain.cms.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.entity.CmsSite;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.util.Enumerations.UserOrgType;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.UserOrg;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IOrgService;
import org.springrain.system.service.ITableindexService;
import org.springrain.system.service.IUserOrgService;




/**
 * 在此加入类描述
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
	
	@Resource
	private IOrgService orgService;
	@Resource
	private IUserOrgService userOrgService;
	
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
		evictByKey(GlobalStatic.cacheKey, "cmsSiteService_findListDataByFinder");
		return siteId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		List<String> orgIds = userOrgService.findOrgIdsByManagerUserId(SessionUser.getUserId());
		finder = Finder.getSelectFinder(CmsSite.class).append(" where orgId in (:orgIds)");
		finder.setParam("orgIds", orgIds);
		List<CmsSite> siteList;
		if(page.getPageIndex()==1){
			siteList = getByCache(GlobalStatic.cacheKey, "cmsSiteService_findListDataByFinder", ArrayList.class);
			if(CollectionUtils.isEmpty(siteList)){//缓存中没有
				siteList =  super.findListDataByFinder(finder, page, CmsSite.class, queryBean);
				putByCache(GlobalStatic.cacheKey, "cmsSiteService_findListDataByFinder", siteList);
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
	    
    	 //创建站点部门
		 Org org = new Org();
		 org.setName(cmsSite.getName());
		 org.setDescription(cmsSite.getDescription());
		 org.setOrgType(cmsSite.getSiteType());
		 org.setPid(cmsSite.getOrgId());
		 org.setActive(1);
		 String orgId = orgService.saveOrg(org);
		 //创建站点用户关联新
		 UserOrg userOrg = new UserOrg(SecUtils.getUUID());
		 userOrg.setUserId(SessionUser.getUserId());
		 userOrg.setOrgId(orgId);
		 userOrg.setHasleaf(0);
		 userOrg.setManagerType(UserOrgType.getUserOrgTypeByName(UserOrgType.主管.name()).getType());
		 userOrgService.save(userOrg);
    	
    	
	    String id= tableindexService.updateNewId(CmsSite.class);
	    if(StringUtils.isEmpty(id)){
	    	return null;
	    }
	    cmsSite.setId(id);
	    cmsSite.setOrgId(orgId);
	    super.save(cmsSite);
	    

	    
	    //保存 相应的 link 链接
	    
	    CmsLink cmsLink=new CmsLink();
	    
	    cmsLink.setBusinessId(id);
	    cmsLink.setSiteId(id);
	    cmsLink.setName(cmsSite.getName());
	    cmsLink.setJumpType(0);
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
		 
		 putByCache(id, "cmsSiteService_findCmsSiteById_"+id, cmsSite);
		 
		
		 return id;
	}
	

	@Override
	public CmsSite findCmsSiteById(String id) throws Exception{
    	CmsSite site = getByCache(id, "cmsSiteService_findCmsSiteById_"+id, CmsSite.class);
    	if(site == null){
    		site = findById(id,CmsSite.class);
    		putByCache(id,  "cmsSiteService_findCmsSiteById_"+id, site);
    	}
    	return site;
	}



	@Override
	public String updateCmsSite(CmsSite cmsSite) throws Exception {
		super.update(cmsSite,true);
		String siteId = cmsSite.getId();
		//evictByKey(siteId, "findCmsSiteById_"+siteId);
		putByCache(siteId, "cmsSiteService_findCmsSiteById_"+siteId, cmsSite);
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
	
	@Override
	public <T> T findById(Object id, Class<T> clazz) throws Exception {
		return super.findById(id, clazz);
	}

	@Override
	public String saveTmpLogo(MultipartFile tempFile, HttpServletRequest request) throws IOException {
		String filePath = "upload"+File.separator+"tmp"+File.separator+tempFile.getOriginalFilename();
		File file = new File(request.getServletContext().getRealPath("/")+filePath);
		FileUtils.copyToFile(tempFile.getInputStream(), file);
		return filePath;
	}
}
