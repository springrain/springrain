package org.springrain.cms.base.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsLink;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.frame.util.Finder;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:20
 * @see org.springrain.demo.service.impl.CmsLink
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
	public String findFtlFileByBussinessId(String bussinessId) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsLink.class).append(" WHERE businessId=:bussinessId");
		finder.setParam("bussinessId", bussinessId);
		CmsLink link = super.queryForObject(finder, CmsLink.class); 
		return link.getFtlfile();
	}

	@Override
	public String findLinkByBusinessId(String bussinessId) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsLink.class).append(" WHERE businessId=:bussinessId");
		finder.setParam("bussinessId", bussinessId);
		CmsLink link = super.queryForObject(finder, CmsLink.class); 
		return link.getFtlfile();
	}


}
