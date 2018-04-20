package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springrain.SpringrainApplication;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfigService;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.mp.api.IWxMpService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringrainApplication.class)
public class TestWeiXinSpring {

	@Resource
	IWxMpService wxMpService;
	
	@Resource
	IWxMpConfigService wxMpConfigService;
	
	
	@Test
	public void testAccessToken(){
		
		
		//数据库查询
	    //WxMpConfig  wxmpconfig=wxMpConfigService.findWxMpConfigById("");
		
	      //测试
		IWxMpConfig  wxmpconfig=new WxMpConfig();
		wxmpconfig.setId("testId");		
		wxmpconfig.setAppId("");
		wxmpconfig.setSecret("");

		
		try {
			String accessToken = wxMpService.getAccessToken(wxmpconfig);
			System.out.println("accessToken:"+accessToken);
			
			
			wxmpconfig=wxMpConfigService.findWxMpConfigById("testId");
			
			String jsapiTicket = wxMpService.getJsApiTicket(wxmpconfig);
			System.out.println("jsapiTicket:"+jsapiTicket);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
