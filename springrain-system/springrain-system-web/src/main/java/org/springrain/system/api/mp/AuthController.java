package org.springrain.system.api.mp;


import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserService;
import org.springrain.weixin.sdk.common.ApiResult;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.springrain.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.springrain.weixin.sdk.miniapp.MiniappAuthApi;
import org.springrain.weixin.service.IWxMpConfigService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(value="/api/mp/auth", method = RequestMethod.POST)
public class AuthController  extends BaseController {

    @Resource
    private IWxMpConfigService wxMpConfigService;

    @Resource
    private IUserService userService;



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnDatas login(@RequestBody Map map) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();

        return returnObject;
    }
}
