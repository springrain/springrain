/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.service;

/**
 * 消息类型
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 *
 */
public enum MsgTypes {
	TEXT(), 
	LOCATION(), 
	IMAGE(),
	LINK(),
	VOICE(),
	EVENT(),
	VIDEO(),
	NEWS(),
	MUSIC(),
	VERIFY();
	
	public String getType() {
		return this.name().toLowerCase();
	}
}
