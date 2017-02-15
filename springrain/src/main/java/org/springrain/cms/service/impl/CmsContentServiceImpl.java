package org.springrain.cms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsChannelContent;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsChannelService;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.Enumerations.SiteType;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:19
 * @see org.springrain.cms.entity.demo.service.impl.CmsContent
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		List<CmsContent> contentList;
		if(page.getPageIndex()==1){
			contentList = getByCache(GlobalStatic.cacheKey, "cmsContentService_findListDataByFinder", List.class,page);
			if(CollectionUtils.isEmpty(contentList)){
				contentList = super.findListDataByFinder(finder, page, CmsContent.class, queryBean);
				putByCache(GlobalStatic.cacheKey, "cmsContentService_findListDataByFinder", contentList,page);
			}
		}else{
			contentList = super.findListDataByFinder(finder, page, CmsContent.class, queryBean);
		}
		for (CmsContent cmsContent : contentList) {
			Map<String, Object> addtionInfo = super.queryForObject(new Finder("SELECT a.siteId,a.channelId,b.link FROM cms_channel_content a INNER JOIN cms_link b ON a.contentId=b.businessId WHERE a.contentId=:contentId").setParam("contentId", cmsContent.getId()));
			cmsContent.setSiteId((String) addtionInfo.get("siteId"));
			cmsContent.setChannelId((String) addtionInfo.get("channelId"));
			cmsContent.setLink((String) addtionInfo.get("link"));
		}
		return (List<T>) contentList;
	}
	
    @Override
	public String  saveContent(CmsContent cmsContent ) throws Exception{
    	if(cmsContent==null){
    		return null;
    	}
    	
    	evictByKey(GlobalStatic.cacheKey, "cmsContentService_findListDataByFinder");//清空后台列表缓存
    	
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
	    cmsLink.setJumpType(0);
	    cmsLink.setLookcount(1);
	    cmsLink.setStatichtml(0);//默认不静态化
	    cmsLink.setActive(cmsContent.getActive());//默认可以使用
	    cmsLink.setSortno(cmsContent.getSortno());
	    //首页默认
	    String _index="/f/"+SiteType.getSiteType(siteType).name()+"/"+siteId+"/"+id;
	    cmsLink.setDefaultLink(_index);
	    cmsLink.setLink(_index);
	    //设置模板路径
	    cmsLink.setFtlfile("/u/"+siteId+"/content");
	    cmsLink.setLoginuser(cmsContent.getLoginuser());
	    cmsLinkService.save(cmsLink);
	    
	    //清除缓存
	    evictByKey(siteId, "cmsContentService_findContentByChannelId_"+siteId+"_"+cmsContent.getChannelId());
	    evictByKey(siteId, "cmsContentService_findListBySiteId_"+siteId);
	    //添加新缓存
	    return id;
	}

  
	@Override
    public Integer updateCmsContent(CmsContent cmsContent) throws Exception{
		if(cmsContent==null){
    		return null;
    	}
		CmsLink link = cmsLinkService.findLinkBySiteBusinessId(cmsContent.getSiteId(), cmsContent.getId());
		if(link!=null){
			link.setLoginuser(cmsContent.getLoginuser());
			cmsLinkService.saveorupdate(link);
		}
		//清除缓存
		evictByKey(GlobalStatic.cacheKey, "cmsContentService_findListDataByFinder");//清空后台列表缓存
		evictByKey(cmsContent.getSiteId(), "cmsContentService_findContentByChannelId_"+cmsContent.getSiteId()+"_"+cmsContent.getChannelId());
	    evictByKey(cmsContent.getSiteId(), "cmsContentService_findListBySiteId_"+cmsContent.getSiteId());
	    
	    return super.update(cmsContent,true);
    }
    @Override
	public CmsContent findCmsContentById(String id) throws Exception{
	 return findById(id,CmsContent.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsContent> findListBySiteId(String siteId, Page page) throws Exception {
		if(page.getPageIndex() == 1){
			List<CmsContent> contentList = getByCache(siteId, "cmsContentService_findListBySiteId_"+siteId, List.class);
			if(CollectionUtils.isEmpty(contentList)){
				Finder finder = new Finder("SELECT c.*,d.link FROM cms_site a INNER JOIN cms_channel_content b ON a.id=b.siteId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON d.businessId = c.id WHERE a.id=:siteId");
				finder.setParam("siteId", siteId);
				contentList = super.queryForList(finder, CmsContent.class, page);
				putByCache(siteId, "cmsContentService_findListBySiteId_"+siteId, contentList);
			}
			return contentList;
		}else{
			Finder finder = new Finder("SELECT c.*,d.link FROM cms_site a INNER JOIN cms_channel_content b ON a.id=b.siteId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON d.businessId = c.id WHERE a.id=:siteId");
			finder.setParam("siteId", siteId);
			return super.queryForList(finder, CmsContent.class, page);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsContent> findContentByChannelId(String siteId,String channelId, Page page) throws Exception {
		List<CmsContent> contentList;
		if(page.getPageIndex()==1){
			contentList = getByCache(siteId, "cmsContentService_findContentByChannelId_"+siteId+"_"+channelId, List.class);
			if(CollectionUtils.isEmpty(contentList)){
				Finder finder = new Finder("SELECT c.*,d.link FROM cms_channel a INNER JOIN cms_channel_content b ON a.id=b.channelId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON c.id=d.businessId WHERE a.id=:channelId");
				finder.setParam("channelId", channelId);
				contentList = super.queryForList(finder, CmsContent.class, page);
				putByCache(siteId, "cmsContentService_findContentByChannelId_"+siteId+"_"+channelId, contentList);
			}
		}else{
			Finder finder = new Finder("SELECT c.*,d.link FROM cms_channel a INNER JOIN cms_channel_content b ON a.id=b.channelId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON c.id=d.businessId WHERE a.id=:channelId");
			finder.setParam("channelId", channelId);
			contentList = super.queryForList(finder, CmsContent.class, page);
		}
		
		return contentList;
	}
	
	@Override
	public <T> T findById(Object id, Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		return super.findById(id, clazz);
	}
}
