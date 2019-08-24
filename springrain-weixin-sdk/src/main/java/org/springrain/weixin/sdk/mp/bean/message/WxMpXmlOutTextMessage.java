package org.springrain.weixin.sdk.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxMpXmlOutTextMessage extends WxMpXmlOutMessage {

    /**
     *
     */
    private static final long serialVersionUID = -3972786455288763361L;
    @XStreamAlias("Content")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String content;

    public WxMpXmlOutTextMessage() {
        this.msgType = WxConsts.XML_MSG_TEXT;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
