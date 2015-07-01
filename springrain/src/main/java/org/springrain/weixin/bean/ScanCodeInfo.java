/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 二维码扫描信息
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 * @date 2014年10月19日 下午5:01:35
 */
public class ScanCodeInfo {

	private String ScanType;	//扫描类型，一般是qrcode
	private String ScanResult;	//扫描结果，即二维码对应的字符串信息

	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
