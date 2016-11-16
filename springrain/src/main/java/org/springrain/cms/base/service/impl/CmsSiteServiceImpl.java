package org.springrain.cms.base.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsLink;
import org.springrain.cms.base.entity.CmsSite;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.cms.base.service.ICmsSiteService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
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
	public String  saveCmsSite(CmsSite entity ) throws Exception{
    	if(entity==null){
    		return null;
    	}
	    CmsSite cmsSite=(CmsSite) entity;
	    
	    String id= tableindexService.updateNewId(CmsSite.class,cmsSite.getId());
	    if(StringUtils.isEmpty(id)){
	    	return null;
	    }
	    cmsSite.setId(id);
	    
	    super.save(cmsSite);
	    
	    //产生店铺的栏目和内容编号索引
	    tableindexService.saveIndexBySiteId(id);
	    
	    //保存 相应的 link 链接
	    
	    CmsLink cmsLink=new CmsLink();
	    
	    cmsLink.setBusinessId(id);
	    cmsLink.setSiteId(id);
	    cmsLink.setName(cmsSite.getName());
	    cmsLink.setModelType(0);//网站
	    cmsLink.setLookcount(1);
	    cmsLink.setStatichtml(0);//默认不静态化
	    cmsLink.setState(0);//默认可以使用
	    //首页默认
	    String _index="/f/"+cmsSite.getSiteType()+"/index";
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
		      
		 return id;
	 
	}

  
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsSite cmsSite=(CmsSite) entity;
	return super.update(cmsSite);
    }
    @Override
	public CmsSite findCmsSiteById(String id) throws Exception{
	 return super.findById(id,CmsSite.class);
	}



	@Override
	public String updateCmsSite(CmsSite cmsSite) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
