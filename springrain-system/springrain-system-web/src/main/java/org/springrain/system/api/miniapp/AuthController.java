package org.springrain.system.api.miniapp;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserService;
import org.springrain.weixin.sdk.common.ApiResult;
import org.springrain.weixin.sdk.common.WxCryptUtils;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.springrain.weixin.sdk.miniapp.MiniappAuthApi;
import org.springrain.weixin.service.IWxMiniappConfigService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(value="/api/miniapp/auth", method = RequestMethod.POST)
public class AuthController extends BaseController {


    @Resource
    private IWxMiniappConfigService wxMiniappConfigService;
    @Resource
    private IUserService userService;
    /**
     * 查看的Json格式数据,为APP端提供数据
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnDatas login(@RequestBody Map map) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
       String appId=(String)map.get("appId");
       String jsCode=(String)map.get("jsCode");
       IWxMiniappConfig config = wxMiniappConfigService.findWxMiniappConfigById(appId);

       if (config==null){
           return ReturnDatas.getErrorReturnDatas("appId不存在");
       }

       ApiResult apiResult = MiniappAuthApi.code2Session(config, jsCode);

       if (apiResult.isSucceed()==false){
           return ReturnDatas.getErrorReturnDatas("数据错误");
       }

       String openId=apiResult.getOpenId();

       if(StringUtils.isBlank(openId)){
           return ReturnDatas.getErrorReturnDatas("数据错误");
       }
       String userId=userService.findUserIdByOpenId(openId);
        User user=null;
       if (StringUtils.isNotBlank(userId)){
            user=userService.findUserById(userId);
        }else{
           user=new User();
           String id=SecUtils.getTimeNO();
           user.setId(id);
           user.setOpenId(openId);
           user.setUnionID(apiResult.getUnionid());
           user.setAccount(id);
           user.setPassword(SecUtils.encoderByMd5With32Bit(id+openId));
           user.setSex(GlobalStatic.sexMap.get(map.get("gender")));
           user.setUserType(0);
           user.setActive(1);
           user.setUserName((String)map.get("avatarUrl"));
           userService.save(user);
       }

        ConcurrentMap resutltMap = Maps.newConcurrentMap();

        String jwtToken = userService.wrapJwtTokenByUser(user);
        resutltMap.put(GlobalStatic.jwtTokenKey, jwtToken);
        returnObject.setResult(resutltMap);

        userService.putByCache(GlobalStatic.wxConfigCacheKey,"sessionKey_"+user.getId(),apiResult.getSessionKey());
        return returnObject;
    }


    @RequestMapping(value = "/getPhone", method = RequestMethod.POST)
    public ReturnDatas getPhone(@RequestBody Map<String,String> map) throws Exception {
          String encryptedData=map.get("encryptedData");
          String iv=map.get("iv");
          String userId= SessionUser.getUserId();
          String sessionKey=userService.getByCache(GlobalStatic.wxConfigCacheKey,"sessionKey_"+userId,String.class);
          String json=WxCryptUtils.decrypt(sessionKey,encryptedData,iv);
          Map m= JsonUtils.readValue(json,Map.class);
          String phone=(String)map.get("phoneNumber");
          if (StringUtils.isBlank(phone)){
              return ReturnDatas.getErrorReturnDatas("数据错误");
          }
          User user=new User();
          user.setId(userId);
          user.setMobile(phone);
        userService.update(user,true);
        ReturnDatas returnDatas=ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(phone);
        return returnDatas;
    }




}