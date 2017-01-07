package test;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.springrain.frame.util.DateUtils;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.mp.api.WxMpDataCubeService;
import org.springrain.weixin.base.mp.api.impl.WxMpDataCubeServiceImpl;
import org.springrain.weixin.base.mp.api.impl.WxMpServiceImpl;
import org.springrain.weixin.base.mp.bean.datacube.WxDataCubeArticleResult;
import org.springrain.weixin.entity.WxMpConfig;

public class TestWeiXin {

	//@Test
	public void testAccessToken(){
		
		WxMpServiceImpl wx=new WxMpServiceImpl();
		WxMpConfig  wxmpconfig=new WxMpConfig();
		try {
			String accessToken = wx.getAccessToken(wxmpconfig);
			
			String jsapiTicket = wx.getJsapiTicket(wxmpconfig);
			
			System.out.println(jsapiTicket);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testData(){
		WxMpDataCubeService data=new WxMpDataCubeServiceImpl();
		WxMpConfig  wxmpconfig=new WxMpConfig();
		
		try {
			List<WxDataCubeArticleResult> articleSummary = data.getArticleSummary(wxmpconfig, DateUtils.convertString2Date("2016-12-01"), DateUtils.convertString2Date("2016-12-30"));
		System.out.println(articleSummary);
		
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
