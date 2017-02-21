package org.springrain.weixin.sdk.common.api;

public interface IWxMpConfig extends IWxConfig {

	boolean isCardApiTicketExpired();

	void setCardApiTicket(String cardApiTicket);

	String getPartnerId();

	String getPartnerKey();


}
