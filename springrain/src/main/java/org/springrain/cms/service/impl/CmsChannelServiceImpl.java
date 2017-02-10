package org.springrain.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsChannel;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsChannelService;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.util.Enumerations.SiteType;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;



/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:17
 * @see org.springrain.cms.entity.demo.service.impl.CmsChannel
 */
@Service("cmsChannelService")
public class CmsChannelServiceImpl extends BaseSpringrainServiceImpl implements ICmsChannelService {

	@Resource
	private ITableindexService tableindexService;
	
	@Resource
	private ICmsLinkService cmsLinkService;
	
	
	@Resource
	private ICmsSiteService cmsSiteService;
	
	@Override
	public Object saveorupdate(Object entity) throws Exception {
		CmsChannel channel = (CmsChannel) entity;
		evictByKey(GlobalStatic.cacheKey, "cmsChannelService_findListDataByFinder");
		if(StringUtils.isBlank(channel.getId())){
			return this.saveChannel(channel);
		}else{
			return this.updateChannel(channel);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		List<CmsChannel> channelList;
		if(page.getPageIndex()==1){
			channelList = getByCache(GlobalStatic.cacheKey, "cmsChannelService_findListDataByFinder", List.class,page);
			if(CollectionUtils.isEmpty(channelList)){
				channelList = super.findListDataByFinder(finder, page, CmsChannel.class, queryBean);
				putByCache(GlobalStatic.cacheKey, "cmsChannelService_findListDataByFinder", channelList,page);
			}
		}else{
			channelList =  super.findListDataByFinder(finder, page, CmsChannel.class, queryBean);
		}
		return (List<T>) channelList;
	}
	
    @Override
	public String  saveChannel(CmsChannel cmsChannel) throws Exception{
    	if(cmsChannel==null){
    		return null;
    	}
        String siteId=cmsChannel.getSiteId();
        
        if(StringUtils.isBlank(siteId)){
        	return null;
        }
        
        Integer siteType=cmsSiteService.findSiteTypeById(siteId);
        if(siteType==null){
        	return null;
        }
        
    	
	    String id= tableindexService.updateNewId(CmsChannel.class);
	    if(StringUtils.isEmpty(id)){
	    	return null;
	    }
	    cmsChannel.setId(id);
	    String _c=	findChannelNewComcode(id, cmsChannel.getPid(),siteId);
	    cmsChannel.setComcode(_c);
	    super.save(cmsChannel);
	    
	
	    
	    
	    
       //保存 相应的 link 链接
	    CmsLink cmsLink=new CmsLink();
	    
	    cmsLink.setBusinessId(id);
	    cmsLink.setSiteId(siteId);
	    cmsLink.setName(cmsChannel.getName());
	    cmsLink.setJumpType(0);
	    cmsLink.setLookcount(1);
	    cmsLink.setStatichtml(0);//默认不静态化
	    cmsLink.setActive(0);//默认可以使用
	    cmsLink.setSortno(1);
	    //首页默认
	    String _index="/f/"+SiteType.getSiteType(siteType).name()+"/"+siteId+"/"+id;
	    cmsLink.setDefaultLink(_index);
	    cmsLink.setLink(_index);
	    //设置模板路径
	    cmsLink.setFtlfile("/u/"+siteId+"/channel");
	    cmsLink.setNodeftlfile("/u/"+siteId+"/content.html");
	    cmsLinkService.save(cmsLink);
	    
	    evictByKey(siteId, "cmsChannelService_findTreeByPid_"+cmsChannel.getPid()+"_"+siteId);
	    putByCache(siteId, "cmsChannelService_findTreeChannel_"+siteId, cmsChannel);
	    return id;
	}

  
	@Override
    public Integer updateChannel(CmsChannel cmsChannel) throws Exception{
		if(cmsChannel==null){
    		return null;
    	}
		
		String id=cmsChannel.getId();
		String pid=cmsChannel.getPid();
		String siteId=cmsChannel.getSiteId();
		
		if(StringUtils.isBlank(siteId)||StringUtils.isBlank(id)){
			return null;
		}
		
		

		Finder f_old_c=Finder.getSelectFinder(CmsChannel.class, "comcode").append(" WHERE id=:id ").setParam("id", id);
		
		String old_c=super.queryForObject(f_old_c, String.class);
		
		String new_c=findChannelNewComcode(id, pid,siteId);
		
		if(new_c.equalsIgnoreCase(old_c)){//编码没有改变
			evictByKey(siteId, "cmsChannelService_findTreeByPid_"+pid+"_"+siteId);
			return super.update(cmsChannel,true);
		}
		cmsChannel.setComcode(new_c);
		Integer update = super.update(cmsChannel,true);
		//级联更新
		Finder f_s_list=Finder.getSelectFinder(CmsChannel.class, "id,comcode").append(" WHERE comcode like :comcode and id<>:id ").setParam("comcode", old_c+"%").setParam("id", id);
	    List<CmsChannel> list = super.queryForList(f_s_list, CmsChannel.class);
	    if(CollectionUtils.isEmpty(list)){
	    	 return update;
	    }
		
	    for(CmsChannel ch:list){
	    	String _id=ch.getId();
		    String _c=	findChannelNewComcode(_id, id,siteId);
		    ch.setComcode(_c);
		    ch.setPid(id);
	    }
	    
		
		super.update(list,true);
		
		evictByKey(siteId, "cmsChannelService_findTreeByPid_"+pid+"_"+siteId);
	    return update;

    }
    @Override
	public CmsChannel findCmsChannelById(String id) throws Exception{
    	return findById(id,CmsChannel.class);
	}
    
    
    
    @SuppressWarnings("unchecked")
	@Override
	public List<CmsChannel> findTreeByPid(String pid,String siteId) throws Exception {
    	if(StringUtils.isBlank(siteId)){
    		return null;
    	}
    	
    	List<CmsChannel> channelList = getByCache(siteId, "cmsChannelService_findTreeByPid_"+pid+"_"+siteId, List.class);
    	if(CollectionUtils.isEmpty(channelList)){
    		Finder finder=Finder.getSelectFinder(CmsChannel.class).append("  WHERE active=:active and siteId=:siteId ").setParam("siteId", siteId).setParam("active", 1);
            
            if(StringUtils.isNotBlank(pid)){
            	finder.append(" and comcode like :comcode and id<>:pid ").setParam("comcode", "%,"+pid+",%").setParam("pid", pid);
            }
            finder.append(" order by sortno asc ");
            
            channelList=super.queryForList(finder, CmsChannel.class);
    		if(CollectionUtils.isEmpty(channelList)){
    			return null;
    		}
    		List<CmsChannel> wrapList=new ArrayList<CmsChannel>();
    		diguiwrapList(channelList, wrapList, null);
    		putByCache(siteId, "cmsChannelService_findTreeByPid_"+pid+"_"+siteId,wrapList);
    		return wrapList;
    	}else{
    		return channelList;
    	}
	}
	@Override
	public List<CmsChannel> findTreeChannel(String siteId) throws Exception {
		return findTreeByPid(null, siteId);
	}

	@Override
	public String findChannelNewComcode(String channelId,String pid,String siteId) throws Exception {
		
		if(StringUtils.isBlank(channelId)||StringUtils.isBlank(siteId)){
			return null;
		}
		
		String comcode=null;
		Finder f_p_c=Finder.getSelectFinder(CmsChannel.class, "comcode").append(" WHERE id=:id and siteId=:siteId ").setParam("siteId", siteId).setParam("id", pid);
		String p_c=super.queryForObject(f_p_c, String.class);
		//如果没有上级部门
		if(StringUtils.isEmpty(p_c)){
			p_c=",";
		}
		
		comcode=p_c+channelId+",";
		
		return comcode;
	}
	
	
	private List<CmsChannel> diguiwrapList(List<CmsChannel> from,List<CmsChannel> tolist,String parentId){
		if(CollectionUtils.isEmpty(from)){
			return null;
		}
		
		for(int i=0;i<from.size();i++){
			CmsChannel m=from.get(i);
			if(m==null){
				continue;
			}
		
			String pid=m.getPid();
			if((pid==null)&&(parentId!=null)){
				continue;
			}
		
			if((parentId==m.getPid())||(m.getPid().equals(parentId))){
				List<CmsChannel> leaf=new ArrayList<CmsChannel>();
				m.setLeaf(leaf);
				tolist.add(m);
			    diguiwrapList(from, leaf, m.getId());
			}
		}
		
		return tolist;
	}
	@Override
	public <T> T findById(Object id, Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		return super.findById(id, clazz);
	}
}
