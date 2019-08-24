package org.springrain.weixin.sdk.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.springrain.weixin.sdk.common.service.WxConsts;
import org.springrain.weixin.sdk.common.util.xml.XStreamMediaIdConverter;

@XStreamAlias("xml")
public class WxMpXmlOutVoiceMessage extends WxMpXmlOutMessage {

    /**
     *
     */
    private static final long serialVersionUID = 240367390249860551L;
    @XStreamAlias("Voice")
    @XStreamConverter(value = XStreamMediaIdConverter.class)
    private String mediaId;

    public WxMpXmlOutVoiceMessage() {
        this.msgType = WxConsts.XML_MSG_VOICE;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
