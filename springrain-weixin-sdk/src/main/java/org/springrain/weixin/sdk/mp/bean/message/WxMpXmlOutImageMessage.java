package org.springrain.weixin.sdk.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.springrain.weixin.sdk.common.service.WxConsts;
import org.springrain.weixin.sdk.common.util.xml.XStreamMediaIdConverter;

@XStreamAlias("xml")
public class WxMpXmlOutImageMessage extends WxMpXmlOutMessage {

    /**
     *
     */
    private static final long serialVersionUID = -2684778597067990723L;
    @XStreamAlias("Image")
    @XStreamConverter(value = XStreamMediaIdConverter.class)
    private String mediaId;

    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public WxMpXmlOutImageMessage() {
        this.msgType = WxConsts.XML_MSG_IMAGE;
    }

}
