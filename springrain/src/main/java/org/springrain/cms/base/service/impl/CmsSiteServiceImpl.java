package org.springrain.cms.base.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsSite;
import org.springrain.cms.base.service.ICmsSiteService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:21
 * @see org.springrain.demo.service.impl.CmsSite
 */
@Service("cmsSiteService")
public class CmsSiteServiceImpl extends BaseSpringrainServiceImpl implements ICmsSiteService {

	@Resource
   private ITableindexService tableindexService;
    @Override
	public String  save(Object entity ) throws Exception{
    	if(entity==null){
    		return null;
    	}
	    CmsSite cmsSite=(CmsSite) entity;
	    
	    String id= tableindexService.updateNewId(CmsSite.class,cmsSite.getId());
	    if(StringUtils.isEmpty(id)){
	    	return null;
	    }
	    cmsSite.setId(id);
	    
	    super.save(cmsSite);
	      
	     return id;
	}

  
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsSite cmsSite=(CmsSite) entity;
	return super.update(cmsSite);
    }
    @Override
	public CmsSite findCmsSiteById(String id) throws Exception{
	 return super.findById(id,CmsSite.class);
	}
	

}
