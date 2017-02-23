package org.springrain.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsChannelContent;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.entity.CmsProperty;
import org.springrain.cms.service.ICmsChannelService;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsPropertyService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.util.Enumerations.SiteType;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
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
	@Resource
	private ICmsPropertyService cmsPropertyService;
	

	

	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		CmsContent q=(CmsContent) queryBean;
		
	   finder=new Finder("SELECT c.*, re.siteId siteId,re.channelId channelId,link.link link  FROM ");
	   finder.append(Finder.getTableName(CmsContent.class)).append(" c,")
	         .append(Finder.getTableName(CmsChannelContent.class)).append(" re,")
	         .append(Finder.getTableName(CmsLink.class)).append(" link ");
	   
	   finder.append(" WHERE c.id=re.contentId and c.id=link.businessId and  re.siteId=link.siteId ");
	   q.setFrameTableAlias("c");
	   
	   super.getFinderWhereByQueryBean(finder, q);
	   
	   finder.append(" order by c.createDate desc ");
	   
	   
	   super.queryForList(finder, clazz, page);
	   
		
		return (List<T>) super.queryForList(finder, clazz, page);
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
	 
	   cmsContent.setId(id);
	   cmsContent.setCreateDate(new Date());
	   if(cmsContent.getSortno() == null)
		   cmsContent.setSortno(Integer.parseInt(id.substring(2)));
	   super.save(cmsContent);
	   
	   List<CmsProperty> propertyList = cmsContent.getPropertyList();
	   if(CollectionUtils.isNotEmpty(propertyList)){//有扩展属性
		   int listSize = propertyList.size();
		   for (int i = 0; i < listSize; i++) {
			 
			   CmsProperty cmsProperty = propertyList.get(i);
			   String pvalue = cmsProperty.getPvalue();
			   CmsProperty tmpProperty = cmsPropertyService.findCmsPropertyById(cmsProperty.getId());
			   BeanUtils.copyProperties(cmsProperty, tmpProperty);
			   cmsProperty.setId(null);
			   cmsProperty.setBusinessId(id);
			   cmsProperty.setPvalue(pvalue);
		   }
	   }
	   cmsPropertyService.save(propertyList);
	   
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
	    cmsLink.setModelType(0);
	    //首页默认
	    String _index="/f/"+SiteType.getSiteType(siteType).name()+"/"+siteId+"/"+id;
	    cmsLink.setDefaultLink(_index);
	    cmsLink.setLink(_index);
	    //设置模板路径
	    cmsLink.setFtlfile("/u/"+siteId+"/f/content");
	    cmsLink.setLoginuser(cmsContent.getLoginuser());
	    cmsLinkService.save(cmsLink);
	    
	    //清除缓存
	    super.cleanCache(cmsContent.getSiteId());
	   
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
			cmsLinkService.update(link);
			String cacheKey="findLinkBySiteBusinessId_"+cmsContent.getSiteId()+"_"+cmsContent.getId();
			super.evictByKey(cmsContent.getSiteId(), cacheKey);
		}
		Integer update = super.update(cmsContent,true);
	    
		List<CmsProperty> propertyList = cmsContent.getPropertyList();
		if(CollectionUtils.isNotEmpty(propertyList)){//有扩展属性
			int listSize = propertyList.size();
			for (int i = 0; i < listSize; i++) {
			   CmsProperty cmsProperty = propertyList.get(i);
			   String pvalue = cmsProperty.getPvalue();
			   CmsProperty tmpProperty = cmsPropertyService.findCmsPropertyById(cmsProperty.getId());
			   BeanUtils.copyProperties(cmsProperty, tmpProperty);
			   cmsProperty.setPvalue(pvalue);
			}
		}
		cmsPropertyService.update(propertyList);
		
		
	    super.cleanCache(cmsContent.getSiteId());
	    
	    
	    return update;
    }
    @Override
	public CmsContent findCmsContentById(String siteId,String id) throws Exception{
    	
    	if(StringUtils.isBlank(siteId)||StringUtils.isBlank(id)){
    		return null;
    	}
    	
    	String key="cmsContentService_findCmsContentById_"+id;
    	
    	CmsContent content= getByCache(siteId, key, CmsContent.class);
    	
    	if(content!=null){
    		return content;
    	}
    	
    	
    	
    	 content= findById(id,CmsContent.class);
    	
    	
		
		putByCache(siteId, key, content);
    	
    	
	 return content;
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
	
		String cacheKey="cmsContentService_findContentByChannelId_"+siteId+"_"+channelId;
		
		if(page.getPageIndex()==1){
			contentList = getByCache(siteId, cacheKey, List.class);
			if(CollectionUtils.isNotEmpty(contentList)){
				return contentList;
			}
		}
		
		Finder finder = new Finder("SELECT c.*,d.link FROM cms_channel a INNER JOIN cms_channel_content b ON a.id=b.channelId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON c.id=d.businessId WHERE a.id=:channelId");
		finder.setParam("channelId", channelId);
		contentList = super.queryForList(finder, CmsContent.class, page);
	
		putByCache(siteId, cacheKey, contentList);
		
		return contentList;
	}

	@Override
	public void deleteById(String id, String siteId) throws Exception {
		//清除缓存
	    super.cleanCache(siteId);
		super.deleteById(id, CmsContent.class);
	}
	@Override
	public void deleteByIds(List<String> ids,String siteId) throws Exception {
		 super.cleanCache(siteId);
		super.deleteByIds(ids, CmsContent.class);
	}
}
