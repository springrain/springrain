package org.springrain.weixin.sdk.mp.bean.material;

import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;

import java.io.Serializable;

/**
 * @author springrain
 */
public class WxMediaImgUploadResult implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1996392453428768829L;
    private String url;

    public static WxMediaImgUploadResult fromJson(String json) {
        return WxJsonBuilder.fromJson(json, WxMediaImgUploadResult.class);
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
