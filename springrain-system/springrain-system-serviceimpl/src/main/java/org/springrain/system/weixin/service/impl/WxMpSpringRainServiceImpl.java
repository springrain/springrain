package org.springrain.system.weixin.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.system.core.service.impl.BaseSpringrainServiceImpl;
import org.springrain.system.weixin.entity.WxMpConfig;
import org.springrain.system.weixin.service.IWxMpSpringRainService;

@Service("wxMpSpringRainService")
public class WxMpSpringRainServiceImpl extends BaseSpringrainServiceImpl implements IWxMpSpringRainService {
	@Override
	public void saveWxMpConfig(WxMpConfig wxMpConfig) throws Exception {
		super.save(wxMpConfig);
	}

	@Override
	public void updateWxMpConfig(WxMpConfig wxMpConfig) throws Exception {
		super.update(wxMpConfig);
		super.evictByKey(wxMpConfig.getSiteId(), GlobalStatic.mpConfigCacheKey);
	}
}
