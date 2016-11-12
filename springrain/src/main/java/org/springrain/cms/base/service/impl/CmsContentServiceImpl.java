package org.springrain.cms.base.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsContent;
import org.springrain.cms.base.service.ICmsContentService;
import org.springrain.frame.entity.IBaseEntity;
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
    @Override
	public String  save(Object entity ) throws Exception{
    	if(entity==null){
    		return null;
    	}
	      CmsContent cmsContent=(CmsContent) entity;
	      
		    String id= tableindexService.updateNewId(CmsContent.class,cmsContent.getSiteId());
		    if(StringUtils.isEmpty(id)){
		    	return null;
		    }
		    cmsContent.setId(id);
		    
		    super.save(cmsContent);
		      
		     return id;
	}

  
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsContent cmsContent=(CmsContent) entity;
	return super.update(cmsContent);
    }
    @Override
	public CmsContent findCmsContentById(String id) throws Exception{
	 return super.findById(id,CmsContent.class);
	}
	


}
