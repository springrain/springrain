package org.springrain.weixin.base.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.springrain.weixin.base.common.api.WxConsts;
import org.springrain.weixin.base.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxMpXmlOutTransferKefuMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = 1850903037285841322L;

  @XStreamAlias("TransInfo")
  protected TransInfo transInfo;

  public WxMpXmlOutTransferKefuMessage() {
    this.msgType = WxConsts.CUSTOM_MSG_TRANSFER_CUSTOMER_SERVICE;
  }

  public TransInfo getTransInfo() {
    return this.transInfo;
  }

  public void setTransInfo(TransInfo transInfo) {
    this.transInfo = transInfo;
  }

  @XStreamAlias("TransInfo")
  public static class TransInfo {

    @XStreamAlias("KfAccount")
    @XStreamConverter(value=XStreamCDataConverter.class)
    private String kfAccount;

    public String getKfAccount() {
      return this.kfAccount;
    }

    public void setKfAccount(String kfAccount) {
      this.kfAccount = kfAccount;
    }
  }
}