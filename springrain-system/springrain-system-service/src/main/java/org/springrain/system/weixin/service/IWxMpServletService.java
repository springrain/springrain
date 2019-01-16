package org.springrain.system.weixin.service;

import org.springrain.system.core.service.IBaseSpringrainService;
import org.springrain.system.weixin.entity.WxMpConfig;

public interface IWxMpServletService extends IBaseSpringrainService{

	void saveWxMpConfig(WxMpConfig wxMpConfig) throws Exception;

	void updateWxMpConfig(WxMpConfig wxMpConfig) throws Exception;

}
