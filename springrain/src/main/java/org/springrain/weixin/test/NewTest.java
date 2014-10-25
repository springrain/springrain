package org.springrain.weixin.test;

import org.springrain.weixin.bean.InMessage;
import org.springrain.weixin.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

public class NewTest {

	public static void main(String[] args) {
		String xml1 = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>" +
			"<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>" +
			"<CreateTime>1408090606</CreateTime>" +
			"<MsgType><![CDATA[event]]></MsgType>" +
			"<Event><![CDATA[scancode_waitmsg]]></Event>" +
			"<EventKey><![CDATA[6]]></EventKey>" +
			"<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>" +
			"<ScanResult><![CDATA[2]]></ScanResult>" +
			"</ScanCodeInfo>" +
			"</xml>";
		
		XStream xs = XStreamFactory.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);
        InMessage msg = (InMessage) xs.fromXML(xml1);
        
        System.out.println(msg.toMap());
       // System.out.println(msg.toJson());
        
        String xml2 = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>" +
			"<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>" +
			"<CreateTime>1408090651</CreateTime>" +
			"<MsgType><![CDATA[event]]></MsgType>" +
			"<Event><![CDATA[pic_sysphoto]]></Event>" +
			"<EventKey><![CDATA[6]]></EventKey>" +
			"<SendPicsInfo><Count>1</Count>" +
			"<PicList>"+ 
				//"<item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum></item>" +
			"</PicList>" +
			"</SendPicsInfo>" +
			"</xml>";

        XStream xs1 = XStreamFactory.init(false);
        xs1.ignoreUnknownElements();
        xs1.alias("xml", InMessage.class);
        InMessage msg1 = (InMessage) xs1.fromXML(xml2);
        
        System.out.println(msg1.toMap());
        //System.out.println(msg1.toJson());
        
        String xml3 = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>" +
			"<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>" +
			"<CreateTime>1408091189</CreateTime>" +
			"<MsgType><![CDATA[event]]></MsgType>" +
			"<Event><![CDATA[location_select]]></Event>" +
			"<EventKey><![CDATA[6]]></EventKey>" +
			"<SendLocationInfo><Location_X><![CDATA[23]]></Location_X>" +
			"<Location_Y><![CDATA[113]]></Location_Y>" +
			"<Scale><![CDATA[15]]></Scale>" +
			"<Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>" +
			"<Poiname><![CDATA[]]></Poiname>" +
			"</SendLocationInfo>" +
			"</xml>";

        XStream xs2 = XStreamFactory.init(false);
        xs2.ignoreUnknownElements();
        xs2.alias("xml", InMessage.class);
        InMessage msg2 = (InMessage) xs1.fromXML(xml3);
        
        System.out.println(msg2.toMap());
       // System.out.println(msg2.toJson());
	}
}
