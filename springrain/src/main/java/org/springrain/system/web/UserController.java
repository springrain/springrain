package org.springrain.system.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.Role;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserService;

/**
 * 用户管理Controller,PC和手机浏览器用ACE自适应,APP提供JSON格式的数据接口
 * 
 * @copyright {@link 9iu.org}
 * @author 9iu.org<Auto generate>
 * @version 2014-06-26 11:36:47
 * @see org.springrain.springrain.web.User
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {
	@Resource
	private IUserService userService;

	private String listurl = "/system/user/userList";

	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, User user)
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, user);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model, User user)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		List<User> datas = userService.findListDataByFinder(null, page,
				User.class, user);
		returnObject.setQueryBean(user);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	/**
	 * 导出Excle格式的数据
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,
			HttpServletResponse response, Model model, User user)
			throws Exception {
		// ==构造分页请求
		Page page = newPage(request);
		File file = userService.findDataExportExcel(null, listurl, page,
				User.class, user);
		String fileName = "user" + GlobalStatic.excelext;
		downFile(response, file, fileName, true);
		return;
	}

	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/user/userLook";
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			User user = userService.findUserById(id);
			returnObject.setData(user);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}

		return returnObject;

	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(User user, HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String id = user.getId();
			String password = user.getPassword();

			if (StringUtils.isBlank(password)) {
				user.setPassword(null);
			} else {
				user.setPassword(SecUtils.encoderByMd5With32Bit(password));
			}
			
			String str_orgIds=request.getParameter("orgIds");
			String[] orgIds=null;
			List<Org> listOrg=null;
			
			if(StringUtils.isNotBlank(str_orgIds)){
			 orgIds = str_orgIds.split(",");
			}
			if(orgIds!=null&&orgIds.length>0){
				Set<String> set=new HashSet<String>();
				for(String s:orgIds){
					if(StringUtils.isBlank(s)){
						continue;
					}
					set.add(s);
				}
				listOrg=new ArrayList<Org>();
				for(String s2:set){
					Org org=new Org();
					org.setId(s2);
					listOrg.add(org);
				}
				
			}
			
		
			user.setUserOrgs(listOrg);
			
			
			String[] roleIds=request.getParameterValues("roleIds");
			List<Role> listRole=null;
			if(roleIds!=null&&roleIds.length>0){
				Set<String> set=new HashSet<String>();
				for(String s:roleIds){
					if(StringUtils.isBlank(s)){
						continue;
					}
					set.add(s);
				}
				listRole=new ArrayList<Role>();
				for(String s2:set){
					Role role=new Role();
					role.setId(s2);
					listRole.add(role);
				}
				
			}
			
		
			user.setUserRoles(listRole);
			
			
			
			
			
			

			if (StringUtils.isBlank(id)) {
				user.setId(null);
				userService.saveUser(user);

			} else {
				userService.updateUser(user);
			}

		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}

	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String edit(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/user/userCru";
	}

	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody
	ReturnDatas destroy(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
			java.lang.String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				userService.deleteById(id, User.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,
						MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}

	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping("/delete/more")
	public @ResponseBody
	ReturnDatas delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if (StringUtils.isBlank(records)) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			userService.deleteByIds(ids, User.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
	}

}
