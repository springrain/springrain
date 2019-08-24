package org.springrain.weixin.service;

import org.springrain.frame.service.IBaseService;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.weixin.entity.WxMpConfig;

@RpcServiceAnnotation
public interface IWxMpConfigDBService extends IBaseService {

    void saveWxMpConfig(WxMpConfig wxMpConfig) throws Exception;

    void updateWxMpConfig(WxMpConfig wxMpConfig) throws Exception;

}
