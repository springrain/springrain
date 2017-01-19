package org.springrain.cms.service.impl;

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
	public String findFtlFileByBussinessId(String bussinessId) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsLink.class,"ftlfile").append(" WHERE businessId=:bussinessId");
		finder.setParam("bussinessId", bussinessId);
		String ftlfile = super.queryForObject(finder, String.class); 
		return ftlfile;
	}

	@Override
	public String findLinkByBusinessId(String bussinessId) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsLink.class,"link").append(" WHERE businessId=:bussinessId");
		finder.setParam("bussinessId", bussinessId);
		String link = super.queryForObject(finder, String.class); 
		return link;
	}


}
