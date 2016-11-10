package org.springrain.cms.base.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsChannel;
import org.springrain.cms.base.service.ICmsChannelService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;



/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:17
 * @see org.springrain.demo.service.impl.CmsChannel
 */
@Service("cmsChannelService")
public class CmsChannelServiceImpl extends BaseSpringrainServiceImpl implements ICmsChannelService {

	@Resource
	private ITableindexService tableindexService;
    @Override
	public String  save(Object entity ) throws Exception{
    	if(entity==null){
    		return null;
    	}
	    CmsChannel cmsChannel=(CmsChannel) entity;
	    String id= tableindexService.updateNewId(CmsChannel.class);
	    if(StringUtils.isEmpty(id)){
	    	return null;
	    }
	    cmsChannel.setId(id);
	    super.save(cmsChannel);
	    return id;
	}

  
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsChannel cmsChannel=(CmsChannel) entity;
	return super.update(cmsChannel);
    }
    @Override
	public CmsChannel findCmsChannelById(String id) throws Exception{
	 return super.findById(id,CmsChannel.class);
	}
	


}
