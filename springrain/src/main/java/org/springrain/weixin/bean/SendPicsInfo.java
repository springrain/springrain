/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 发送的图片信息
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 * @date 2014年10月19日 下午5:05:58
 */
public class SendPicsInfo {

	private int Count;
	private PicList PicList;
	
	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public PicList getPicList() {
		return PicList;
	}

	public void setPicList(PicList picList) {
		PicList = picList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
