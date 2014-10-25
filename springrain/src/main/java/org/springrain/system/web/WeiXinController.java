package  org.springrain.system.web;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springrain.frame.controller.BaseController;
import org.springrain.weixin.util.Tools;
import org.springrain.weixin.util.WeiXinUtils;


/**
 * TODO 在此加入类描述
 * @copyright {@link bbz}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:46
 * @see com.bibizao.bbz.web.Role
 */
@Controller
@RequestMapping(value="/weixin")
public class WeiXinController  extends BaseController {
	
	
	
	@RequestMapping(value = "/token/{weixintoken}",method=RequestMethod.POST)
	public void toPOSTtoken(@PathVariable String weixintoken,HttpServletRequest request,HttpServletResponse response) throws Exception{
	    response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
	    ServletInputStream in = request.getInputStream();
        String xmlMsg = Tools.inputStream2String(in);
        if(StringUtils.isNotBlank(xmlMsg)){
        	   logger.debug("输入消息:[" + xmlMsg + "]");
               String xml = WeiXinUtils.processing(xmlMsg,weixintoken);
               response.getWriter().write(xml);
        }
     
	}
	
	@RequestMapping(value = "/token/{weixintoken}",method=RequestMethod.GET)
	public void toGETtoken(@PathVariable String weixintoken,HttpServletRequest request,HttpServletResponse response) throws IOException{
	           response.setCharacterEncoding("UTF-8");
               response.setContentType("text/xml");
        	   String outPut = "error";
	            String signature = request.getParameter("signature");// 微信加密签名
	            String timestamp = request.getParameter("timestamp");// 时间戳
	            String nonce = request.getParameter("nonce");// 随机数
	            String echostr = request.getParameter("echostr");//
	            // 验证
	            if (WeiXinUtils.checkSignature(weixintoken, signature, timestamp, nonce)) {
	                outPut = echostr;
	            }
	           response.getWriter().write(outPut);
	}
	
	@RequestMapping(value = "/menu/upate/pre",method=RequestMethod.GET)
	public String updatemenupre(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return "";
		
	}
	@RequestMapping(value = "/menu/upate",method=RequestMethod.POST)
	public void updatemenu(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
	}
	
	
	
	
}
