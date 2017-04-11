package org.springrain.police.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.property.PropertyFile;

/**
 * 122适配地址
 * 
 * @author 跨时代
 *
 */
public class HostURL122Utils {
	// public static Map<String, String> hosts=null;
	private static  Logger logger = LoggerFactory.getLogger(HostURL122Utils.class);
	private HostURL122Utils(){
		throw new IllegalAccessError("工具类不能实例化");
	}
	
	//处理配置文件默认值
   private static PropertyFile host122Property=null;
    static{
    	try{
    		host122Property=new PropertyFile("hosturl");
    	}catch (Exception e) {
    		logger.error("配置文件读取错误",e);
		}
    	
    }
	
	
	/**
	 * 二维码（登录用的）
	 * 
	 * @param host
	 * @return
	 */
	public static String postCaptcha1(String host)  {
		return get122URL(host,"/captcha1");
	}
	/**
	 * 二维码（不登录用的）
	 * 
	 * @param host
	 * @return
	 */
	public static String postCaptcha(String host)  {
		return get122URL(host,"/captcha");
	}

	/**
	 * 登录页面
	 * 
	 * @param host
	 * @return
	 */
	public static String postLogin(String host)  {
		return get122URL(host,"/m/login");
	}

	/**
	 * 提交登录
	 * 
	 * @param host
	 * @return
	 * @
	 */
	public static String postloginSubmit(String host)  {
		
		return get122URL(host,"/user/m/login");
	}
	
	/**
	 * 获取车辆信息
	 * @param host
	 * ?page=1&size=1&status=null
	 * @return
	 */
	public static String postCarInfro(String host)  {
		
		return get122URL(host,"/user/m/userinfo/allvehsurveils?vioSize=1");
		
	}
	
	/**
	 * 机动车电子监控
	 * 传参：hpzl=02 hphm=粤RWD316 page=0 size: 10
	 * @param host
	 * @return
	 */
	public static String postVehssurisList(String host)  {
		return get122URL(host,"/user/m/uservio/vehssuris");
	}
	/**
	 * 机动车违法处理记录
	 * 传参：hpzl=02 hphm=粤RWD316 page=0 size: 10
	 * @param host
	 * @return
	 */
	public static String postVehsviosList(String host)  {
		return get122URL(host,"/user/m/uservio/vehsvios");
	}
	
	/**
	 * 车辆报废查询
	 * 传参：hpzl=02&hphm2a=粤&hphm2b=RWD316&hphm=粤RWD316&captcha=h6jg&qm=wf&page=1
	 * @param host
	 * @return
	 */
	public static String postDiscardCar(String host)  {
		return get122URL(host,"/m/publicquery/discard");
	}
	
	
	/**
	 * 驾驶证信息
	 * 传参：drvSize=1&vioSize=1&forcSize=1
	 * @param host
	 * @return
	 */
	public static String postDrvvio(String host)  {
		return get122URL(host,"/user/m/userinfo/drvvio?drvSize=1&vioSize=1&forcSize=1");
	}
	
	/**
	 * 驾驶证扣分记录
	 * 传参：page=1&size=10
	 * @param host
	 * @return
	 */
	public static String postDrvendorse(String host)  {
		return get122URL(host,"/user/m/userinfo/drvendorse");
	}
	/**
	 * 获取城市简称
	 * @param host
	 * @return
	 */
	public static String postHpt(String host)  {
		return get122URL(host,"/m/syscode/gethpt");
	}
	/**
	 * 绑定机动车
	 * 参数：hpzl=02&hphm=粤RWD316&fdjh=
	 * @param host
	 * @return
	 */
	public static String postVeh(String host)  {
		return get122URL(host,"/user/m/userinfo/bind/veh");
	}
	/**
	 * 绑定驾驶证
	 * 参数：dabh=123154654&yzm=
	 * @param host
	 * @return {"message":"没有查询到您的机动车驾驶证信息","code":412}
	 */
	public static String postDrv(String host)  {
		return get122URL(host,"/user/m/userinfo/bind/drv");
	}
	
	
////////////////////////////////////////////////////////////注册
	///////////////1.填写用户信息
	/**
	 * 提示信息
	 * @param host
	 * @return{"message":"操作成功","data":"因广东省...","code":200}
	 */
	public static String postSmsnotice(String host){
		return get122URL(host,"/user/m/user/smsnotice");
	}
	
	/**
	 * 获取省内城市
	 * @param host
	 * @return{"message":"操作成功！","data":[{"xtlb":"00","dmlb":"0034","dmz":"粤A","dmsm1":"广东省广州市公安局交通警察支队车辆管理所","dmsm2":"440100","dmsm3":"广州","dmsm4":"广东省广州市公安局交通警察支队","dmsx":null,"sxh":null,"ywdx":null,"zt":null,"csbj":null,"dmsxStr":null,"ztStr":null}],"code":200}
	 */
	public static String postGxfzjgkt(String host){
		return get122URL(host,"/m/syscode/gxfzjgkt");
	}
	
	/**
	 * 提交1：填写用户信息
	 * 传参：sfzmmc=A&sfzmhm=63242119820810033412222&xm=姓名&sjhm=13290963250&city=粤A&password1=a123456&password2=a123456&agree=on
	 * @param host
	 * @return {"message":"操作成功","code":200}
	 */
	public static String postChecksfzmhm(String host){
		return get122URL(host,"/user/m/user/checksfzmhm");
	}
	
	///////////////2.选择用户类型
	/**
	 * 选择:车主用户 
	 * 传参：hpzl=02&hphm=粤RWD316
	 * @param host
	 * @return {"message":"验证失败","data":{"hphm":"注册城市和机动车登记地不一致，机动车登记地是：清远"},"code":412}
	 */
	public static String postCheckuserveh(String host){
		return get122URL(host,"/user/m/user/checkuserveh");
	}
	
	/**
	 * 选择:驾驶人用户
	 * 传参：{"message":"验证失败","data":{"dabh":"获取不到该驾驶人信息"},"code":412}
	 * @param host
	 * @return
	 */
	public static String postCheckuserdrv(String host){
		return get122URL(host,"/user/m/user/checkuserdrv");
	}
	
	/**
	 * 选择:初次申领机动车驾驶证学员
	 * 传参：{"message":"验证失败","data":{"tsnr":"您尚未到车管所办理初次申领驾驶证业务，无法注册此类用户。请先到车管所办理初次申领驾驶证业务！"},"code":412}
	 * @param host
	 * @return
	 */
	public static String postCheckuserxy(String host){
		return get122URL(host,"/user/m/user/checkuserxy");
	}
	
	/**
	 * 选择:新车车主用户
	 * 传参：
	 * @param host
	 * @return{"message":"13290963250","code":200}
	 */
	public static String postcheckuserxh(String host){
		return get122URL(host,"/user/m/user/checkuserxh");
	}

	///////////////3.验证
	
	/**
	 * 新车车主用户————验证————发送短信验证码(不属于本市区的手机号收不到验证码)
	 * @param host
	 * @return{"message":"短信已发送至132****3250，请及时输入！","code":200}
	 */
	public static String postGetIdentifyingcode(String host){
		return get122URL(host,"/user/m/user/getIdentifyingcode");
	}
	
	/**
	 * 新车车主用户————验证————验证短信验证码
	 * 传参：yzm=123131&sjhm=13290963250
	 * @param host
	 * @return{"message":"手机验证码错误","data":{"yzm":"手机验证码错误","msg":"手机验证码错误"},"code":412}
	 */
	public static String postCheckcodesave(String host){
		return get122URL(host,"/user/m/user/checkcodesave");
	}
	
	
	

	private static String get122URL(String host,String url)  {
		if (StringUtils.isEmpty(host)) {
			return null;
		}
		String hostUrl=getHostUrl(host);
		if(StringUtils.isNotBlank(hostUrl)){
			hostUrl=hostUrl+url;
			return hostUrl;
		}
		
		hostUrl="https://"+host+".122.gov.cn"+url;
		return hostUrl;
	}
	
	
	private static String getHostUrl(String name){
		
		if(host122Property==null){
			return null;
		}
		return host122Property.getString(name);
	}
	
}
