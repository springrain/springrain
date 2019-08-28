package org.springrain.system.api.miniapp;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.system.base.BaseController;
import org.springrain.weixin.entity.WxMiniappConfig;
import org.springrain.weixin.sdk.common.ApiResult;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.springrain.weixin.sdk.miniapp.MiniappAuthApi;

import java.util.Map;

@RestController
@RequestMapping(value="/api/miniapp/auth", method = RequestMethod.POST)
public class AuthController extends BaseController {

    /**
     * 查看的Json格式数据,为APP端提供数据
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnDatas login(@RequestBody Map map) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();

        IWxMiniappConfig config = new WxMiniappConfig();
        config.setAppId("wx95217af982ed4f53");
        config.setSecret("8a4fe0d1b47d46282774d9fe77f6bb19");

        ApiResult apiResult = MiniappAuthApi.code2Session(config, (String)map.get("code"));
        returnObject.setResult(apiResult);
        return returnObject;

    }
}
