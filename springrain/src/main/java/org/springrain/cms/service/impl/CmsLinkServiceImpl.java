package org.springrain.cms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.frame.util.Finder;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:20
 * @see org.springrain.cms.entity.demo.service.impl.CmsLink
 */
@Service("cmsLinkService")
public class CmsLinkServiceImpl extends BaseSpringrainServiceImpl implements ICmsLinkService {

   
    @Override
	public String  saveCmsLink(CmsLink cmsLink ) throws Exception{
	       return super.save(cmsLink).toString();
	}

    
	@Override
    public Integer updateCmsLink(CmsLink cmsLink ) throws Exception{
	return super.update(cmsLink);
    }
    @Override
	public CmsLink findCmsLinkById(String id) throws Exception{
	 return super.findById(id,CmsLink.class);
	}


	@Override
	public CmsLink findLinkBySiteBusinessId(String siteId,String bussinessId,Integer type) throws Exception {
		
		if(StringUtils.isBlank(siteId)||StringUtils.isBlank(bussinessId)){
			return null;
		}
		String cacheKey="findLinkBySiteBusinessId_"+siteId+"_"+bussinessId+"_"+type;
		
		CmsLink link =super.getByCache(siteId, cacheKey, CmsLink.class);
		if(link!=null){
			return link;
		}
		
		
		Finder finder = Finder.getSelectFinder(CmsLink.class).append(" WHERE siteId=:siteId and  businessId=:bussinessId and type=:type");
		finder.setParam("siteId", siteId).setParam("bussinessId", bussinessId).setParam("type", type);
		link = super.queryForObject(finder, CmsLink.class); 
		
		super.putByCache(siteId, cacheKey, link);
		
		return link;
	}
}
