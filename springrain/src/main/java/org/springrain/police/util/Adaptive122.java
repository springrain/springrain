package org.springrain.police.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springrain.frame.util.JsonUtils;
import org.springrain.police.dto.CarDriverData;
import org.springrain.police.dto.CarDriverInfro;
import org.springrain.police.dto.DiscardCarInfro;
import org.springrain.police.dto.DriverInfro;
import org.springrain.police.dto.RegisterInfro;

/**
 * 122适配页面数据处理
 * 
 * @author 跨时代
 */
public class Adaptive122 {

	public static final String LOGINSUCCESS122 = "loginsuccess122";// 122登录状态：成功true
	public static final String COOKIES122 = "cookies122";// 122cookies
	private static final String CODE = "code";// 获取返回json的code
	private static final String MESSAGE = "message";// 获取返回json的message
	private static final String ERROR122 = "登录异常，请刷新页面重新登录！";

	private Adaptive122() {
		throw new IllegalAccessError("工具类不能实例化");
	}

	/**
	 * 登录获取cookie
	 * 
	 * @param request
	 * @param host
	 * @throws Exception
	 */
	public static void getPostCaptcha(HttpServletRequest request, String host)
			throws Exception {
		Response response = getResponse(request, host,
				HostURL122Utils.postLogin(host));
		if (response.cookies() == null) {
			throw new Exception(ERROR122);
		}
		request.getSession().setAttribute(COOKIES122, response.cookies());
		return;
	}

	/**
	 * 获取二维码
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static byte[] getCaptcha(HttpServletRequest request, String host)
			throws Exception {
		String loginCaptcha = request.getParameter("loginCaptcha");
		String postUrl = "true".equals(loginCaptcha) ? HostURL122Utils
				.postCaptcha1(host) : HostURL122Utils.postCaptcha(host);
		Response response = getResponse(request, host, postUrl);
		return response.bodyAsBytes();
	}

	/**
	 * 提交登录
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> returnLogin(HttpServletRequest request,
			String host) throws Exception {
		Map<String, String> cookies122 = (Map<String, String>) request
				.getSession().getAttribute(COOKIES122);
		Response response = getResponse(request, host,
				HostURL122Utils.postloginSubmit(host));
		Document doc = Jsoup.parse(response.body());
		Element element = doc.getElementById("hello");
		if (element == null) {
			element = doc.select("#grdl .login-msg").first();
			if (element != null && !"".equals(element.text().trim())) {
				String string = URLDecoder.decode(element.text(), "UTF-8");
				throw new Exception(string);
			}
			throw new Exception("Login failed！");
		}
		Map<String, String> cookies = response.cookies();
		cookies.putAll(cookies122);
		request.getSession().setAttribute(COOKIES122, cookies);
		request.getSession().setAttribute(LOGINSUCCESS122, "true");
		return cookies;
	}

	/**
	 * 获取车辆信息
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static CarDriverInfro getCarInfro(HttpServletRequest request,
			String host) throws Exception {
		String response = getResponse(request, host,
				HostURL122Utils.postCarInfro(host)).body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		if (datamap == null) {
			request.getSession().removeAttribute(COOKIES122);
			request.getSession().removeAttribute(LOGINSUCCESS122);
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		if (StringUtils.isBlank(datamap.get("data").toString())) {
			return null;
		}
		List<Map> maps = (List<Map>) datamap.get("data");
		if (CollectionUtils.isEmpty(maps)) {
			return null;
		}
		Map map = maps.get(0);
		CarDriverInfro info = new CarDriverInfro();
		BeanUtils.populate(info, map);
		return info;
	}

	/**
	 * 机动车违法记录list
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static CarDriverData getVehssuris(HttpServletRequest request,
			String host) throws Exception {
		String recordType = request.getParameter("recordType");
		String url = "wfcl".equals(recordType) ? HostURL122Utils
				.postVehsviosList(host) : HostURL122Utils
				.postVehssurisList(host);
		String response = getResponse(request, host, url).body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		if (datamap == null) {
			request.getSession().removeAttribute(COOKIES122);
			request.getSession().removeAttribute(LOGINSUCCESS122);
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		if (StringUtils.isBlank(datamap.get("data").toString())) {
			return null;
		}
		CarDriverData carDto = new CarDriverData();
		Map map = (Map) datamap.get("data");
		BeanUtils.populate(carDto, map);
		return carDto;

	}

	/**
	 * 获取驾驶证信息
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static DriverInfro getDrvvio(HttpServletRequest request, String host)
			throws Exception {
		String response = getResponse(request, host,
				HostURL122Utils.postDrvvio(host)).body();
		Map<String, Object> datamap = JsonUtils.readValue(response, Map.class);
		if (datamap == null) {
			request.getSession().removeAttribute(COOKIES122);
			request.getSession().removeAttribute(LOGINSUCCESS122);
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		Map maps = (Map) datamap.get("data");
		if (maps==null) {
			return null;
		}
		if (StringUtils.isBlank(datamap.get("data").toString())) {
			return null;
		}
		List<Map> drvsmap=(List<Map>) maps.get("drvs");
		if (CollectionUtils.isEmpty(drvsmap)) {
			return null;
		}
		DriverInfro driverInfro = new DriverInfro();
		BeanUtils.populate(driverInfro, drvsmap.get(0));
		return driverInfro;
	}

	/**
	 * 获取驾驶证扣分记录
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static CarDriverData getDrvendorse(HttpServletRequest request,
			String host) throws Exception {
		String response = getResponse(request, host,
				HostURL122Utils.postDrvendorse(host)).body();
//				HostURL122Utils.postVehsviosList(host)+"?hpzl=02&hphm=粤RWD316").body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		if (datamap == null) {
			request.getSession().removeAttribute(COOKIES122);
			request.getSession().removeAttribute(LOGINSUCCESS122);
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		if (StringUtils.isBlank(datamap.get("data").toString())) {
			return null;
		}
		CarDriverData carDto = new CarDriverData();
		Map map = (Map) datamap.get("data");
		BeanUtils.populate(carDto, map);
		return carDto;
	}
	
	/**
	 * 获取报废车辆信息
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static DiscardCarInfro getDiscardCar(HttpServletRequest request,
			String host) throws Exception {
		String response = getResponse(request, host,
				HostURL122Utils.postDiscardCar(host)).body();
		Map<String, Object> datamap = JsonUtils.readValue(response, Map.class);
		if (datamap == null) {
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		if (StringUtils.isBlank(datamap.get("data").toString())) {
			return null;
		}
		Map map = (Map) datamap.get("data");
		DiscardCarInfro discardCarInfro=new DiscardCarInfro();
		BeanUtils.populate(discardCarInfro, map);
		return discardCarInfro;
	}

	/**
	 * 根据host获取省的简称
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static String getHtp(HttpServletRequest request, String host)
			throws Exception {
		String response = getResponse(request, host,
				HostURL122Utils.postHpt(host)).body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		if (datamap == null) {
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		
		return datamap.get(MESSAGE).toString();
	}
	
	
	/**
	 * 绑定 1.机动车绑定 2.驾驶证绑定
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static void getBang122(HttpServletRequest request, String host)
			throws Exception {
		String ByewuId=request.getParameter("ByewuId");
		String postUrl="";
		if ("car".equals(ByewuId)) {
			postUrl=HostURL122Utils.postVeh(host);
		}else if ("driver".equals(ByewuId)) {
			postUrl=HostURL122Utils.postDrv(host);
		}
		String response = getResponse(request, host,postUrl).body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		if (datamap == null) {
			request.getSession().removeAttribute(COOKIES122);
			request.getSession().removeAttribute(LOGINSUCCESS122);
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
	}
	
	
	
	
//////////////////////////////////////////////////////注册
////////////////////////////1、填写用户信息
	/**
	 * 注册获取提示信息
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static String getSmsnotice(HttpServletRequest request, String host)
			throws Exception {
		String response = getResponse(request, host,
				HostURL122Utils.postSmsnotice(host)).body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		if (datamap == null) {
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		return datamap.get("data").toString();
	}
	
	/**
	 * 注册获取省内城市Map
	 * 
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static List<RegisterInfro> getGxfzjgkt(HttpServletRequest request, String host)
			throws Exception {
		String response = getResponse(request, host,
				HostURL122Utils.postGxfzjgkt(host)).body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		if (datamap == null) {
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			throw new Exception(datamap.get(MESSAGE).toString());
		}
		List<Map> maps=(List<Map>) datamap.get("data");
		List<RegisterInfro> dataList=new ArrayList<RegisterInfro>();
		for (Map map : maps) {
			RegisterInfro reInfro=new RegisterInfro();
			BeanUtils.populate(reInfro, map);
			dataList.add(reInfro);
		}
		return dataList;
	}
	
	/**
	 * 注册提交:1.填写用户信息提交、2.车主信息——填写车牌号信息提交、3.驾驶人用户按钮、  4.发送验证码、5.手机验证码验证
	 * @param request
	 * @param host
	 * @return
	 * @throws Exception
	 */
	public static RegisterInfro getTiJiao(HttpServletRequest request, String host)
			throws Exception {
		String submitType=request.getParameter("submitType");
		String postUrl="";
		if ("1".equals(submitType)) {
			postUrl=HostURL122Utils.postChecksfzmhm(host);
		}else if ("2".equals(submitType)) {
			postUrl=HostURL122Utils.postCheckuserveh(host);
		}else if ("3".equals(submitType)) {
			postUrl=HostURL122Utils.postCheckuserdrv(host);
		}else if ("4".equals(submitType)) {
			postUrl=HostURL122Utils.postGetIdentifyingcode(host);
		}else if ("5".equals(submitType)) {
			postUrl=HostURL122Utils.postCheckcodesave(host);
		}
		String response = getResponse(request, host,postUrl).body();
		Map<String, Object> datamap = JsonUtils.readValue(response,
				HashMap.class);
		RegisterInfro reInfro=new RegisterInfro();
		if (datamap == null) {
			throw new Exception(ERROR122);
		} else if (!"200".equals(datamap.get(CODE).toString())) {
			if (datamap.get("data")==null) {
				throw new Exception(datamap.get(MESSAGE).toString());
			}
			Map map	=(Map) datamap.get("data");
			BeanUtils.populate(reInfro, map);
			reInfro.setStatus("error");
			return reInfro;
		}
		reInfro.setStatus("success");
		//接收发送验证码成功提示
		reInfro.setReturnMessage122(datamap.get(MESSAGE).toString());
		return reInfro;
	}
////////////////////////////2、选择用户类型
	
	
	/**
	 * 获取Response
	 * 
	 * @param request
	 * @param host
	 * @param returnUrl
	 *            访问的url
	 * @return
	 * @throws Exception
	 */
	private static Response getResponse(HttpServletRequest request, String host,
			String returnUrl) throws Exception {
		// 请求是否是登录
		boolean login = HostURL122Utils.postLogin(host).equals(returnUrl);
		Map<String, String> cookies122 = (Map<String, String>) request
				.getSession().getAttribute(COOKIES122);
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key1 = (String) keys.nextElement();
			//tomcat下对url转码
			//params.put(key1, new String(request.getParameter(key1).getBytes("ISO-8859-1"),"utf-8"));
			params.put(key1,request.getParameter(key1));
			
			
		}
		Connection connect = Jsoup.connect(returnUrl);
		connect.ignoreContentType(true);
		Connection data = connect.data();
		if (!login && cookies122 != null && cookies122.size() > 0) {
			data.cookies(cookies122);
		}
 		if (params.size() > 0) {
			data.data(params);
		}
		data.header("Referer", HostURL122Utils.postLogin(host));
		data.userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		data.ignoreHttpErrors(true);
		data.followRedirects(true);
		Document doc = login ? data.get() : data.post();
		Response response = data.response();
		return response;
	}
}
