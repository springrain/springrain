/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.springrain.system.entity.Kefu;
import org.springrain.system.service.IKefuService;
import org.springrain.system.service.IWeiXinSystemService;
import org.springrain.weixin.bean.InMessage;
import org.springrain.weixin.bean.OutMessage;
import org.springrain.weixin.bean.TextOutMessage;
import org.springrain.weixin.util.WeiXinUtils;
@Service("defaultMessageProcessingService")
public class DefaultMessageProcessingServiceImpl implements IMessageProcessingService{
	
	private String kefuWeiXinId="o6IHDji0Ppav40BiSq1ubMYlgQ0o";
	
	private String logoUrl="http://www.9iu.org/images/front/logo.png";
	
	private String kefuUrl="http://www.9iu.org/weixin/f/dati/pre?kefuId=";
	
	
	@Resource
	private IWeiXinSystemService weiXinSystemService;
	@Resource
	private IKefuService kefuService;
	
	
	public OutMessage defaultOMS=new TextOutMessage("默认回复");
	
	
	@Override
	public OutMessage eventTypeMsg(InMessage msg,String weixinId) throws Exception {
		OutMessage out=null;
		String event = msg.getEvent().toLowerCase();
		if("subscribe".equals(event)){
			weiXinSystemService.updateMemberinfoByweixinId(msg.getFromUserName(), 0);
			out=new TextOutMessage("欢迎关注!");
		}else if("unsubscribe".equals(event)){
			weiXinSystemService.updateMemberinfoByweixinId(msg.getFromUserName(), 1);
			out=new TextOutMessage("非常遗憾,取消关注!");
		}else if("location".equals(event)){
			out=new TextOutMessage("你的地址位置:"+msg.getContent());
		}else {
		//msg.getEventKey();
		}
		if (out == null) {
			return defaultOMS;
		}
		return out;
	}

	@Override
	public OutMessage getOutMessageByInMessage(InMessage msg, String type,String weixinId) throws Exception  {
		if("text".equals(type)){
			return textTypeMsg(msg,weixinId);
		}else if("location".equals(type)){
			return locationTypeMsg(msg,weixinId);
		}else if("image".equals(type)){
			return imageTypeMsg(msg,weixinId);
		}else if("video".equals(type)){
			return videoTypeMsg(msg,weixinId);
		}else if("voice".equals(type)){
			return voiceTypeMsg(msg,weixinId);
		}else if("link".equals(type)){
			return linkTypeMsg(msg,weixinId);
		}else if("verify".equals(type)){
			return verifyTypeMsg(msg,weixinId);
		}else if("event".equals(type)){
			return eventTypeMsg(msg,weixinId);
		}
		TextOutMessage out = new TextOutMessage();
		out.setContent("您的消息已经收到！");
		return out;
	}
	
	@Override
	public OutMessage textTypeMsg(InMessage msg,String weixinId)  throws Exception {
		
		
		String fromUserName = msg.getFromUserName();
		String content = msg.getContent();
		if("o6IHDji0Ppav40BiSq1ubMYlgQ0o".equals(fromUserName)){
			return null;
		}

		Kefu kefu=new Kefu();           
		kefu.setKefuType("weixin");
		kefu.setMemberId(fromUserName);
		kefu.setKefuMemberId(kefuWeiXinId);
		kefu.setContent(content);
		Object saveId = kefuService.save(kefu);
		
		List<Map> maps=new ArrayList<Map>();
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("title", "解答新问题");
		map.put("description", content);
		map.put("picurl", logoUrl);
		map.put("url", kefuUrl+saveId);
		maps.add(map);
		WeiXinUtils.customMessage.sendNewsCustomMessage(null, kefuWeiXinId, maps);
		TextOutMessage out=new TextOutMessage(content);
		out.setFromUserName(msg.getToUserName());
		out.setCreateTime(new Date().getTime());
		out.setContent("亲,你的问题已经成功提交,学霸正在解答,请耐心等待!");
		
		return out;
	

	}

	@Override
	public OutMessage locationTypeMsg(InMessage msg,String weixinId) throws Exception  {
		return null;
	}

	@Override
	public OutMessage imageTypeMsg(InMessage msg,String weixinId) throws Exception  {
		return null;
	}
	
	@Override
	public OutMessage videoTypeMsg(InMessage msg,String weixinId)  throws Exception {
		return null;
	}
	
	@Override
	public OutMessage voiceTypeMsg(InMessage msg,String weixinId) throws Exception  {
		return null;
	}

	@Override
	public OutMessage linkTypeMsg(InMessage msg,String weixinId) throws Exception  {
		return null;
	}
	
	@Override
	public OutMessage verifyTypeMsg(InMessage msg,String weixinId)  throws Exception {
		return null;
		
	}
	
	@Override
	public void afterProcess(InMessage inMessage,OutMessage outMessage,String weixinId)  throws Exception {
		
	}
}
