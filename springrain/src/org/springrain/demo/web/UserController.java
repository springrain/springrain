package  org.springrain.demo.web;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.demo.entity.User;
import org.springrain.demo.service.IUserService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.CFReturnObject;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:47
 * @see org.springrain.demo.web.User
 */
@Controller
@RequestMapping(value="/user")
public class UserController  extends BaseController {
	@Resource
	private IUserService userService;
	
	private String listurl="/demo/user/userList";
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	          super.initBinder(binder);
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute("")
	public void init(Model model) {
		model.addAttribute("now", new Date());
	}
	
	/**
	* 进入user Web页面后直接展现第一页数据
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-29 11:36:47
	*/
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,User user) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		Finder finder=new Finder("select tu.*,tg.name as gradeName from t_user tu,t_grade tg where tu.gradeId=tg.id");
		user.setFrameTableAlias("tu");
		List<User> datas=userService.findListDataByFinder(finder,page,User.class,user);
		model.addAttribute("page", page);
		model.addAttribute("datas",datas);
		model.addAttribute("user",user);
		return listurl;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,User user) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = userService.findDataExportExcel(null,listurl, page,User.class,user);
		String fileName="user"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	/**
	 * 查看操作
	 */
	@RequestMapping(value="/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		
		  User user = userService.findUserById(id);
		   model.addAttribute("user",user);
		}
		return "/demo/user/userLook";
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param user
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-29 11:36:47
	*/
	@RequestMapping("/update")
	public String saveorupdate(Model model,User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pwd=user.getPassword();
		String roleId=user.getRoleIds();
		roleId=roleId.substring(0,roleId.lastIndexOf(","));
		if(StringUtils.isBlank(pwd)||StringUtils.isBlank(roleId)){
			model.addAttribute(message, MessageUtils.EDIT_WARING);
			return messageurl;
		}
		//对密码加密 开始
		user.setPassword(SecUtils.encoderByMd5With32Bit(pwd));
		//对密码加密  结束
		if(StringUtils.isBlank(user.getId())){// 新增
				user.setId(SecUtils.getUUID());
			try {
				userService.save(user);
				//进行t_user_role中间表的操作 开始
				userService.updateRoleUser(user.getId(),roleId);
				//进行t_user_role中间表的操作  结束
				model.addAttribute(message, MessageUtils.ADD_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e);
			}
			     model.addAttribute(message, MessageUtils.ADD_FAIL);
			     return messageurl;
		} else {// 修改
			try {
				user.setId(user.getId());
				userService.update(user);
				//进行t_user_role中间表的操作 开始
				userService.updateRoleUser(user.getId(),roleId);
				//进行t_user_role中间表的操作  结束
				model.addAttribute(message, MessageUtils.EDIT_SUCCESS);
				return messageurl;
			} catch (Exception e) {
				logger.error(e);
			}
			model.addAttribute(message, MessageUtils.EDIT_WARING);
			return messageurl;
		}
		
	}
	
	/**
	 * 进入修改页面
	 */
	@RequestMapping(value="/update/pre")
	public String edit(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		   //查roleNames
		   Finder finder=new Finder("select r.id as id,r.name as name from t_user_role tr,t_role r where tr.userId=:userId and tr.roleId=r.id");
		   finder.setParam("userId", id);
		   List<Map<String, Object>> lists=userService.queryForList(finder);
		   String roleIdNames="";
		   String roleIds="";
		   for(Map<String, Object> map:lists){
			   roleIdNames+=map.get("name")+",";
			   roleIds+=map.get("id")+",";
		   }
		   User user = userService.findUserById(id);
		   if(user!=null){
			   user.setRoleIdNames(roleIdNames);
			   user.setRoleIds(roleIds);
		   }
		   model.addAttribute("user",user);
		}
		return "/demo/user/userCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody CFReturnObject destroy(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			userService.deleteById(id,User.class);
			return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
			}else{
			    return new CFReturnObject(CFReturnObject.WARNING, MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
				logger.error(e);
		}
		return new CFReturnObject(CFReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	* 删除多条记录
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2013-07-29 11:36:47
	*/
	@RequestMapping("/delMulti")
	public @ResponseBody
	CFReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		String[] rs = records.split(",");
		if(rs==null||rs.length<1){
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			userService.deleteByIds(ids, User.class);
			}
			catch (Exception e) {
				return new CFReturnObject(CFReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
			}
		return new CFReturnObject(CFReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}
	
	
	private Page newPage(HttpServletRequest request){
		//==获取分页信息
		int pageIndex = NumberUtils.toInt(request.getParameter("pageIndex"), 1);
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		if(StringUtils.isBlank(order)){
		  order="id";
		}
		if(StringUtils.isBlank(sort)){
		  sort="desc";
		}
		
		Page page=new Page(pageIndex);
		page.setOrder(order);
		page.setSort(sort);
		return page;
	}

}
