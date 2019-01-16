package org.springrain.system.cms.web.s;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.base.SessionUser;
import org.springrain.system.core.entity.Menu;
import org.springrain.system.core.service.IUserRoleMenuService;

@Controller
@RequestMapping(value = "/s/{siteId}/menu")
public class SiteMenuController extends BaseController {
	@Resource
	private IUserRoleMenuService userRoleMenuService;

	/**
	 * 
	 * @Title: menu @Description: 菜单列表 @return @return Map @throws
	 */
	@RequestMapping("/leftMenu")
	@ResponseBody
	public ReturnDatas leftMenu() {
		// 获取当前登录人
		String userId = SessionUser.getUserId();
		if (StringUtils.isBlank(userId)) {
			return null;
		}

		try {
			List<Menu> listMenu = userRoleMenuService.findMenuAndLeafByUserId(userId);
			return new ReturnDatas(ReturnDatas.SUCCESS, null, listMenu);

		} catch (Exception e) {
			logger.error("获取导航菜单异常", e);
			return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.SELECT_WARING);
		}

	}
}
