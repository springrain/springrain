/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 图片信息
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 * @date 2014年10月19日 下午6:02:15
 */
public class PicItem {

	private String PicMd5Sum;
	
	public String getPicMd5Sum() {
		return PicMd5Sum;
	}

	public void setPicMd5Sum(String picMd5Sum) {
		PicMd5Sum = picMd5Sum;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}