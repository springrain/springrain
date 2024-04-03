package org.springrain.weixin.constant;


/**
 * @descriptions: 微信配置类
 * @author xiaohua
 * @date 2022-12-23 17:46:21
 */
public class WXCommonConst {
    /**
     * H5
     */
    public static final String H5_SITE = "1";
    /**
     * APP
     */
    public static final String APP_SITE = "2";

    /**
     * 验证site参数
     */
    public static String siteVerity(String site){
        if(WXCommonConst.H5_SITE.equals(site) || WXCommonConst.APP_SITE.equals(site)){
            return site;
        }
        return H5_SITE;
    }

}
