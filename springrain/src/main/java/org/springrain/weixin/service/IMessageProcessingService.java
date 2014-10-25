/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.service;

import org.springrain.weixin.bean.InMessage;
import org.springrain.weixin.bean.OutMessage;
/**
 * 消息处理器
 * @author GodSon
 *
 */
public interface IMessageProcessingService {
	
	/**
	 * 文字内容的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage textTypeMsg(InMessage msg,String weixinId) throws Exception ;
	
	/**
	 * 地理位置类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage locationTypeMsg(InMessage msg,String weixinId) throws Exception ;
	
	/**
	 * 图片类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage imageTypeMsg(InMessage msg,String weixinId) throws Exception ;
	
	/**
	 * 视频类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage videoTypeMsg(InMessage msg,String weixinId) throws Exception ;
	
	/**
	 * 链接类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage linkTypeMsg(InMessage msg,String weixinId) throws Exception ;

	/**
	 * 语音类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage voiceTypeMsg(InMessage msg,String weixinId) throws Exception ;
	
	/**
	 * 验证消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage verifyTypeMsg(InMessage msg,String weixinId) throws Exception ;

	/**
	 * 事件类型的消息处理。<br/>
	 * 在用户首次关注公众账号时，系统将会推送一条subscribe的事件
	 * @param msg
	 * @return
	 */
	public OutMessage eventTypeMsg(InMessage msg,String weixinId) throws Exception ;
	
	/**
	 * 根据消息类型 处理不同的消息
	 * @param msg
	 * @param type
	 */
	public OutMessage getOutMessageByInMessage(InMessage msg,String type,String weixinId) throws Exception ;
	
	

	/**
	 * 处理流程结束，返回输出信息之前执行
	 */
	public void afterProcess(InMessage inMsg,OutMessage outMsg,String weixinId) throws Exception ;
	
	
	
	
	
	
	
	
	
	
	
}
