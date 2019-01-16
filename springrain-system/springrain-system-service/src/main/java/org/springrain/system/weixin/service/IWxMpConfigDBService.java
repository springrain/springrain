package org.springrain.system.weixin.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.core.service.IBaseSpringrainService;
import org.springrain.system.weixin.entity.WxMpConfig;

@RpcServiceAnnotation
public interface IWxMpConfigDBService extends IBaseSpringrainService {

	void saveWxMpConfig(WxMpConfig wxMpConfig) throws Exception;

	void updateWxMpConfig(WxMpConfig wxMpConfig) throws Exception;

}
