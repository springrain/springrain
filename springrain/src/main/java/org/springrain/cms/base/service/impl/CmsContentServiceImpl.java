package org.springrain.cms.base.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsChannelContent;
import org.springrain.cms.base.entity.CmsContent;
import org.springrain.cms.base.entity.CmsLink;
import org.springrain.cms.base.service.ICmsContentService;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.cms.base.service.ICmsSiteService;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:19
 * @see org.springrain.demo.service.impl.CmsContent
 */
@Service("cmsContentService")
public class CmsContentServiceImpl extends BaseSpringrainServiceImpl implements ICmsContentService {

	@Resource
	private ITableindexService tableindexService;
	
	@Resource
	private ICmsSiteService cmsSiteService;
	
	@Resource
	private ICmsLinkService cmsLinkService;
	
	
    @Override
	public String  saveContent(CmsContent cmsContent ) throws Exception{
    	if(cmsContent==null){
    		return null;
    	}
    	
    	String siteId=cmsContent.getSiteId();
    	if(StringUtils.isBlank(siteId)){
    		return null;
    	}
    	
    	
    	 Integer siteType=cmsSiteService.findSiteTypeById(cmsContent.getSiteId());
         if(siteType==null){
         	return null;
         }
	   String id= tableindexService.updateNewId(CmsContent.class);
	   if(StringUtils.isBlank(id)){
		    	return null;
	   }
	   cmsContent.setId(id);
		    
	   super.save(cmsContent);
	   
	   //保存中间对应
	   CmsChannelContent ccc=new CmsChannelContent();
	   ccc.setChannelId(cmsContent.getChannelId());
	   ccc.setSortno(cmsContent.getSortno());
	   ccc.setState(cmsContent.getState());
	   ccc.setContentId(id);
	   ccc.setSiteId(cmsContent.getSiteId());
	   super.save(ccc);
	   
	   
	 //保存 相应的 link 链接
	    CmsLink cmsLink=new CmsLink();
	    
	    cmsLink.setBusinessId(id);
	    cmsLink.setSiteId(cmsContent.getSiteId());
	    cmsLink.setName(cmsContent.getName());
	    cmsLink.setModelType(2);//内容
	    cmsLink.setLookcount(1);
	    cmsLink.setStatichtml(0);//默认不静态化
	    cmsLink.setState(cmsContent.getState());//默认可以使用
	    cmsLink.setSortno(cmsContent.getSortno());
	    //首页默认
	    String _index="/f/"+siteType+"/"+siteId+"/"+id;
	    cmsLink.setDefaultLink(_index);
	    cmsLink.setLink(_index);
	    //设置模板路径
	    cmsLink.setFtlfile("/u/"+siteId+"/content.html");
	    cmsLinkService.save(cmsLink);
	    
	    return id;
	   
	}

  
	@Override
    public Integer updateCmsContent(CmsContent cmsContent) throws Exception{
		if(cmsContent==null){
    		return null;
    	}
	    return super.update(cmsContent,true);
    }
    @Override
	public CmsContent findCmsContentById(String id) throws Exception{
	 return super.findById(id,CmsContent.class);
	}
	


}
