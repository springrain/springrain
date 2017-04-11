package org.springrain.police.web.f;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.cms.web.f.FrontBaseController;
import org.springrain.frame.util.Enumerations.OrgType;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.police.dto.CarDriverData;
import org.springrain.police.dto.CarDriverInfro;
import org.springrain.police.dto.DiscardCarInfro;
import org.springrain.police.dto.DriverInfro;
import org.springrain.police.dto.RegisterInfro;
import org.springrain.police.util.Adaptive122;

/**
 * 122页面适配
 * 
 * @author 跨时代
 */
@Controller
@RequestMapping("/f/mp/122/{host}/{siteId}")
public class AdaptiveController extends FrontBaseController {
	private static final String YEWUID = "yewuId"; // 业务id 机动车、驾驶证
	private static final String LOGIN = "login";
	private static final String NOTBANG = "notbang";//没有绑定
	private static final String ERRORAPP = "系统繁忙，请稍后再试！";
	
	
	
	@ModelAttribute
	public void init(Model model,@PathVariable String host) {
		 model.addAttribute("host", host);
	}
	
	

	// ---------------------------------------------------------------------------------------------------登录
	/**
	 * 登录页面(访问122获取cookie)
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String list(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		String loginsuccess = (String) request.getSession().getAttribute(
				Adaptive122.LOGINSUCCESS122);
		if ("true".equals(loginsuccess)) {
			return forward + path122Replace(host, siteId) + "index";
		}
		Adaptive122.getPostCaptcha(request, host);
		String yewuId = request.getParameter(YEWUID);
		model.addAttribute(YEWUID, yewuId);
		String link = path122Replace(host, siteId) + LOGIN;
		return jumpByLink(siteId, link, OrgType.mp.getType(), request,
				model);
	}

	/**
	 * ajax提交登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login/submit")
	@ResponseBody
	public ReturnDatas login(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setStatus(ReturnDatas.ERROR);
		returnObject.setMessage(ERRORAPP);
		try {
			Map<String, String> cookies122 = Adaptive122
					.returnLogin(request, host);
			if (cookies122 != null && cookies122.size() > 0) {
				// 处理业务Id
				String yewuId = request.getParameter(YEWUID);
				model.addAttribute(YEWUID, yewuId);

				returnObject.setMessage("登陆成功,正在跳转...");
				returnObject.setStatus(ReturnDatas.SUCCESS);
				return returnObject;
			}
		} catch (Exception e) {
			String query = request.getQueryString();
			// 查询参数放到 前台参数,提交的时候拼接到/login/submit?后面
			model.addAttribute("query", query);
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(e.getMessage());
			return returnObject;
		}
		return returnObject;
	}

	// ---------------------------------------------------------------------------------------------------个人中心
	/**
	 * index
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		//测试获取省内城市list
		Adaptive122.getGxfzjgkt(request, host);
		
		String link = path122Replace(host, siteId) + "index";
		
		String path= jumpByLink(siteId, link, OrgType.mp.getType(), request,model);
		
		return path;
	}

	// ---------------------------------------------------------------------------------------------------二维码
	/**
	 * 刷新二维码
	 * 
	 * @param request
	 * @param model
	 * @param host
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCaptcha122")
	public void getCaptcha(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId,
			HttpServletResponse response) throws Exception {
		Map<String, String> cookies122 = (Map<String, String>) request
				.getSession().getAttribute(Adaptive122.COOKIES122);
		if (cookies122 == null || cookies122.size() == 0) {
			return;
		}
		byte[] qoCode = Adaptive122.getCaptcha(request, host);
		ByteArrayInputStream os = new ByteArrayInputStream(qoCode);
		BufferedImage image = ImageIO.read(os);
		response.setHeader("Content-Type", "image/jpeg");
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return;
	}

	// ---------------------------------------------------------------------------------------------------违法机动车
	/**
	 * 机动车违法查询(需先登录)
	 * 
	 * @param request
	 * @param model
	 * @param host
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vehssurisQuery")
	public String jdcar(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		String loginsuccess = (String) request.getSession().getAttribute(
				Adaptive122.LOGINSUCCESS122);
		String query = request.getQueryString();
		if (!"true".equals(loginsuccess)) {
			return forward + path122Replace(host, siteId) + LOGIN;
		}
		model.addAttribute("query", query);
		String link = path122Replace(host, siteId) + "vehssurisQuery";
		return jumpByLink(siteId, link, OrgType.mp.getType(), request,
				model);
	}

	/**
	 * ajax 分页查询机动车违法列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/vehssuris")
	@ResponseBody
	public ReturnDatas getVehssuris(HttpServletRequest request,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setStatus(ReturnDatas.ERROR);
		returnObject.setMessage(ERRORAPP);
		try {
			CarDriverData datas = Adaptive122.getVehssuris(request, host);
			if (CollectionUtils.isEmpty(datas.getContent())) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("您的机动车没有违法处理");
				return returnObject;
			}
			returnObject.setData(datas);
			returnObject.setStatus(ReturnDatas.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(e.getMessage());
			return returnObject;
		}
		return returnObject;
	}

	/**
	 * ajax 获取绑定机动车信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/bangCar")
	@ResponseBody
	public ReturnDatas bangCar(HttpServletRequest request,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setStatus(ReturnDatas.ERROR);
		returnObject.setMessage(ERRORAPP);
		try {
			// 获取车辆信息
			CarDriverInfro cInfro = Adaptive122.getCarInfro(request, host);
			if (cInfro == null) {
				returnObject.setStatus(NOTBANG);
				returnObject.setMessage("您的账号下还没有绑定机动车信息！");
				return returnObject;
			}
			returnObject.setData(cInfro);
			returnObject.setStatus(ReturnDatas.SUCCESS);
			returnObject.setMessage("操作成功,正在跳转...");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(e.getMessage());
			return returnObject;
		}
		return returnObject;
	}

	

	// ---------------------------------------------------------------------------------------------------驾驶证信息
	/**
	 * 查询驾驶证信息(需先登录)
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/drvvioQuery")
	public String drvvio(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		String loginsuccess = (String) request.getSession().getAttribute(
				Adaptive122.LOGINSUCCESS122);
		if (!"true".equals(loginsuccess)) {
			return forward + path122Replace(host, siteId) + LOGIN;
		}
		ReturnDatas returnObject = bangDriver(request, host, siteId);
		model.addAttribute("driverInfro", returnObject);

		String link = path122Replace(host, siteId) + "drvvioQuery";

		return jumpByLink(siteId, link, OrgType.mp.getType(), request,
				model);
	}

	/**
	 * ajax 获取绑定驾驶证信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/bangDriver")
	@ResponseBody
	public ReturnDatas bangDriver(HttpServletRequest request,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setStatus(ReturnDatas.ERROR);
		returnObject.setMessage(ERRORAPP);
		try {
			// 获取车辆信息
			DriverInfro datas = Adaptive122.getDrvvio(request, host);
			if (datas == null) {
				returnObject.setStatus(NOTBANG);
				returnObject.setMessage("您的账号下还没有绑定驾驶证信息！");
				return returnObject;
			}
			returnObject.setData(datas);
			returnObject.setStatus(ReturnDatas.SUCCESS);
			returnObject.setMessage("操作成功,正在跳转...");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(e.getMessage());
			return returnObject;
		}
		return returnObject;
	}

	/**
	 * 查询驾驶证积分记录(需先登录)
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/drvendorseQuery")
	public String drvendorse(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		String loginsuccess = (String) request.getSession().getAttribute(
				Adaptive122.LOGINSUCCESS122);
		if (!"true".equals(loginsuccess)) {
			return forward + path122Replace(host, siteId) + LOGIN;
		}

		String link = path122Replace(host, siteId) + "drvendorseQuery";

		return jumpByLink(siteId, link, OrgType.mp.getType(), request,
				model);
	}

	/**
	 * ajax 分页获取扣驾驶证扣分记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/drvendorse")
	@ResponseBody
	public ReturnDatas drvendorsequery(HttpServletRequest request,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setStatus(ReturnDatas.ERROR);
		returnObject.setMessage(ERRORAPP);
		try {
			CarDriverData datas = Adaptive122.getDrvendorse(request, host);
			if (CollectionUtils.isEmpty(datas.getContent())) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("驾驶证无记分处理");
				return returnObject;
			}
			returnObject.setData(datas);
			returnObject.setStatus(ReturnDatas.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(e.getMessage());
			return returnObject;
		}
		return returnObject;
	}
	// ---------------------------------------------------------------------------------------------------报废汽车
		/**
		 * 报废汽车(访问122获取cookie)
		 * 
		 * @param request
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/discardQuery")
		public String discard(HttpServletRequest request, Model model,
				@PathVariable String host, @PathVariable String siteId)
				throws Exception {
			Map<String, String> cookies122 = (Map<String, String>) request
					.getSession().getAttribute(Adaptive122.COOKIES122);
			if (cookies122 == null || cookies122.size() == 0) {
				Adaptive122.getPostCaptcha(request, host);
			}
			// 获取城市简称
			String abbreviation = Adaptive122.getHtp(request, host);
			model.addAttribute("abbreviation", abbreviation);

			String link = path122Replace(host, siteId) + "discardQuery";

			return jumpByLink(siteId, link, OrgType.mp.getType(), request,
					model);
		}

		/**
		 * ajax 查询报废汽车详情
		 * 
		 * @param request
		 * @return
		 */
		@RequestMapping("/ajax/discard")
		@ResponseBody
		public ReturnDatas ajaxDiscard(HttpServletRequest request,
				@PathVariable String host, @PathVariable String siteId)
				throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(ERRORAPP);
			try {
				DiscardCarInfro datas = Adaptive122.getDiscardCar(request, host);
				returnObject.setData(datas);
				returnObject.setStatus(ReturnDatas.SUCCESS);
				returnObject.setMessage("操作成功！");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(e.getMessage());
				return returnObject;
			}
			return returnObject;
		}
	
	// ---------------------------------------------------------------------------------------------------绑定驾驶证和机动车
	/**
	 * 绑定页面
	 * @param request
	 * @param model
	 * @param host
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bang122")
	public String bang(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		String loginsuccess = (String) request.getSession().getAttribute(
				Adaptive122.LOGINSUCCESS122);
		String ByewuId = request.getParameter("ByewuId");
		if (!"true".equals(loginsuccess)) {
			return forward + path122Replace(host, siteId) + LOGIN;
		}
		// 获取城市简称
		String abbreviation = Adaptive122.getHtp(request, host);
		model.addAttribute("abbreviation", abbreviation);
		model.addAttribute("ByewuId", ByewuId);
		String link = path122Replace(host, siteId) + "bang122";
		return jumpByLink(siteId, link, OrgType.mp.getType(), request,
				model);
	}
	
	/**
	 * 绑定 1.机动车绑定 2.驾驶证绑定
	 * @param request
	 * @param host
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajax/bang122")
	@ResponseBody
	public ReturnDatas bang12(HttpServletRequest request,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setStatus(ReturnDatas.ERROR);
		returnObject.setMessage(ERRORAPP);
		try {
			Adaptive122.getBang122(request, host);
			returnObject.setStatus(ReturnDatas.SUCCESS);
			returnObject.setMessage("机动车绑定成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(e.getMessage());
			return returnObject;
		}
		return returnObject;
	}

	
	
///////////////////////////////////////////////////////////////////////注册
	/**
	 * 注册
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest request, Model model,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		Adaptive122.getPostCaptcha(request, host);
		//获取注册城市列表
		List<RegisterInfro> gxfzjgkt=Adaptive122.getGxfzjgkt(request, host);
		//获取提示信息
		String titlemessage122=Adaptive122.getSmsnotice(request, host);
		model.addAttribute("titlemessage122", titlemessage122);
		model.addAttribute("gxfzjgkt", gxfzjgkt);
		String link = path122Replace(host, siteId) + "register";
		return jumpByLink(siteId, link, OrgType.mp.getType(), request,
				model);
	}
	/**
	 * 注册提交:1.填写用户信息提交、2.车主信息——填写车牌号信息提交、3.驾驶人用户按钮、  4.发送验证码、5.手机验证码验证
	 * @param request
	 * @param host
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajax/registerSubmit")
	@ResponseBody
	public ReturnDatas registerInfro(HttpServletRequest request,
			@PathVariable String host, @PathVariable String siteId)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setStatus(ReturnDatas.ERROR);
		returnObject.setMessage(ERRORAPP);
		try {
			RegisterInfro reInfro=Adaptive122.getTiJiao(request, host);
			if ("success".equals(reInfro.getStatus())) {
				returnObject.setStatus(ReturnDatas.SUCCESS);
				returnObject.setMessage("成功！");
			}
			returnObject.setData(reInfro);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(e.getMessage());
			return returnObject;
		}
		return returnObject;
	}
	
	
	/**
	 * 获取访问路径
	 * 
	 * @param host
	 * @param siteId
	 * @return
	 */
	private static String path122Replace(String host, String siteId) {
		return "/f/mp/122/"+host+"/"+siteId+"/";
	}
}
