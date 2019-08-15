package org.springrain.weixin.sdk.cp.bean;

import java.io.Serializable;

import org.springrain.weixin.sdk.common.service.IWxCpConfig;
import org.springrain.weixin.sdk.common.util.xml.XStreamCDataConverter;
import org.springrain.weixin.sdk.cp.bean.outxmlbuilder.ImageBuilder;
import org.springrain.weixin.sdk.cp.bean.outxmlbuilder.NewsBuilder;
import org.springrain.weixin.sdk.cp.bean.outxmlbuilder.TextBuilder;
import org.springrain.weixin.sdk.cp.bean.outxmlbuilder.VideoBuilder;
import org.springrain.weixin.sdk.cp.bean.outxmlbuilder.VoiceBuilder;
import org.springrain.weixin.sdk.cp.util.crypto.WxCpCryptUtil;
import org.springrain.weixin.sdk.cp.util.xml.XStreamTransformer;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("xml")
public abstract class WxCpXmlOutMessage implements Serializable {

  private static final long serialVersionUID = 1418629839964153110L;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String toUserName;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String msgType;

  /**
   * 获得文本消息builder
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得图文消息builder
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  public String getToUserName() {
    return this.toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  public String getFromUserName() {
    return this.fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public Long getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getMsgType() {
    return this.msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
protected String toXml() {
    return XStreamTransformer.toXml((Class) this.getClass(), this);
  }

  /**
   * 转换成加密的xml格式
   */
  public String toEncryptedXml(IWxCpConfig wxcpconfig) {
    String plainXml = toXml();
    WxCpCryptUtil pc = new WxCpCryptUtil(wxcpconfig);
    return pc.encrypt(plainXml);
  }
}
