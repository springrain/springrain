package org.springrain.weixin.sdk.cp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxCpXmlOutTextMessage extends WxCpXmlOutMessage {
    private static final long serialVersionUID = 2569239617185930232L;

    @XStreamAlias("Content")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String content;

    public WxCpXmlOutTextMessage() {
        this.msgType = WxConsts.XML_MSG_TEXT;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
