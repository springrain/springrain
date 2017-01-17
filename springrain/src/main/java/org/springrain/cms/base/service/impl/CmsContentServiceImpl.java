package org.springrain.cms.base.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsChannel;
import org.springrain.cms.base.entity.CmsChannelContent;
import org.springrain.cms.base.entity.CmsContent;
import org.springrain.cms.base.entity.CmsLink;
import org.springrain.cms.base.service.ICmsChannelService;
import org.springrain.cms.base.service.ICmsContentService;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.cms.base.service.ICmsSiteService;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
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
	@Resource
	private ICmsChannelService cmsChannelService;

	@Override
	public Object saveorupdate(Object entity) throws Exception {
		CmsContent cmsContent = (CmsContent) entity;
		cmsContent.setCreateDate(new Date());
		
		return super.saveorupdate(cmsContent);
	}
	
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
	   cmsContent.setCreateDate(new Date());
	   super.save(cmsContent);
	   
	   //保存中间对应
	   CmsChannelContent ccc=new CmsChannelContent();
	   ccc.setChannelId(cmsContent.getChannelId());
	   ccc.setSortno(cmsContent.getSortno());
	   ccc.setActive(cmsContent.getActive());
	   ccc.setContentId(id);
	   ccc.setSiteId(cmsContent.getSiteId());
	   super.save(ccc);
	   
	   
	 //保存 相应的 link 链接
	    CmsLink cmsLink=new CmsLink();
	    
	    cmsLink.setBusinessId(id);
	    cmsLink.setSiteId(cmsContent.getSiteId());
	    cmsLink.setName(cmsContent.getTitle());
	    cmsLink.setModelType(2);//内容
	    cmsLink.setLookcount(1);
	    cmsLink.setStatichtml(0);//默认不静态化
	    cmsLink.setActive(cmsContent.getActive());//默认可以使用
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

	@Override
	public List<CmsContent> findListBySiteId(String siteId, Page page) throws Exception {
		Finder finder = new Finder("SELECT c.* FROM cms_site a INNER JOIN cms_channel_content b ON a.id=b.siteId INNER JOIN cms_content c ON c.id=b.contentId WHERE a.id=:siteId");
		finder.setParam("siteId", siteId);
		List<CmsContent> contentList = super.queryForList(finder, CmsContent.class, page);
		for (CmsContent cmsContent : contentList) {
			CmsChannel channel = cmsChannelService.findChannelBySiteAndContentId(siteId,cmsContent.getId());
			cmsContent.setChannelId(channel.getId());
		}
		return super.queryForList(finder, CmsContent.class, page);
	}

	@Override
	public List<CmsContent> findContentByChannelId(String channelId, Page page) throws Exception {
		Finder finder = new Finder("SELECT c.* FROM cms_channel a INNER JOIN cms_channel_content b ON a.id=b.channelId INNER JOIN cms_content c ON c.id=b.contentId WHERE a.id=:channelId");
		finder.setParam("channelId", channelId);
		return super.queryForList(finder, CmsContent.class, page);
	}

	@Override
	public CmsContent findCmsContentByChannelAndContentId(String channelId,
			String contentId) throws Exception {
		Finder finder = new Finder("SELECT b.* FROM cms_channel_content a INNER JOIN cms_content b ON a.contentId=b.id WHERE a.channelId=:channelId AND a.contentId=:contentId");
		finder.setParam("channelId", channelId).setParam("contentId", contentId);
		return super.queryForObject(finder, CmsContent.class);
	}
	


}
