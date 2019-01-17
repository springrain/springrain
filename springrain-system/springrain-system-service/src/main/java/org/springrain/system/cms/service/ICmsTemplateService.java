package org.springrain.system.cms.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.cms.entity.CmsTemplate;
import org.springrain.system.manager.service.IBaseSpringrainService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2016-11-10 11:55:22
 * @see org.springrain.cms.entity.demo.service.CmsTemplate
 */
@RpcServiceAnnotation
public interface ICmsTemplateService extends IBaseSpringrainService {

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsTemplate findCmsTemplateById(String id) throws Exception;

}
