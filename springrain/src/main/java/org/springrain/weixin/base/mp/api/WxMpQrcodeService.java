package org.springrain.weixin.base.mp.api;

import java.io.File;

import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.mp.bean.result.WxMpQrCodeTicket;
import org.springrain.weixin.entity.WxMpConfig;

/**
 * 二维码相关操作接口
 *
 * @author Binary Wang
 */
public interface WxMpQrcodeService {

  /**
   * <pre>
   * 换取临时二维码ticket
   * 详情请见: <a href="http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html">生成带参数的二维码</a>
   * </pre>
   *
   * @param scene_id       参数。
   * @param expire_seconds 过期秒数，默认60秒，最小60秒，最大1800秒
   */
  WxMpQrCodeTicket qrCodeCreateTmpTicket(WxMpConfig wxmpconfig,int scene_id, Integer expire_seconds) throws WxErrorException;

  /**
   * <pre>
   * 换取永久二维码ticket
   * 详情请见: <a href="http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html">生成带参数的二维码</a>
   * </pre>
   *
   * @param scene_id 参数。永久二维码时最大值为100000（目前参数只支持1--100000）
   */
  WxMpQrCodeTicket qrCodeCreateLastTicket(WxMpConfig wxmpconfig,int scene_id) throws WxErrorException;

  /**
   * <pre>
   * 换取永久字符串二维码ticket
   * 详情请见: <a href="http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html">生成带参数的二维码</a>
   * </pre>
   *
   * @param scene_str 参数。字符串类型长度现在为1到64
   */
  WxMpQrCodeTicket qrCodeCreateLastTicket(WxMpConfig wxmpconfig,String scene_str) throws WxErrorException;

  /**
   * <pre>
   * 换取二维码图片文件，jpg格式
   * 详情请见: <a href="http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html">生成带参数的二维码</a>
   * </pre>
   *
   * @param ticket 二维码ticket
   */
  File qrCodePicture(WxMpConfig wxmpconfig,WxMpQrCodeTicket ticket) throws WxErrorException;

  /**
   * <pre>
   * 换取二维码图片url地址（可以选择是否生成压缩的网址）
   * 详情请见: <a href="http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html">生成带参数的二维码</a>
   * </pre>
   *
   * @param ticket       二维码ticket
   * @param needShortUrl 是否需要压缩的二维码地址
   */
  String qrCodePictureUrl(WxMpConfig wxmpconfig,String ticket, boolean needShortUrl) throws WxErrorException;

  /**
   * <pre>
   * 换取二维码图片url地址
   * 详情请见: <a href="http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html">生成带参数的二维码</a>
   * </pre>
   *
   * @param ticket 二维码ticket
   */
  String qrCodePictureUrl(WxMpConfig wxmpconfig,String ticket) throws WxErrorException;

}
