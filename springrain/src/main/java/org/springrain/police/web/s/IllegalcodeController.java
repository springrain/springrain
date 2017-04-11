package  org.springrain.police.web.s;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.web.s.SiteBaseController;
import org.springrain.frame.util.Enumerations;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.police.entity.Illegalcode;
import org.springrain.police.entity.Queryname;
import org.springrain.police.service.IIllegalcodeService;


/**
 * TODO 违法代码列表
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-06 10:57:53
 * @see org.springrain.cms.base.web.Illegalcode
 */
@Controller
@RequestMapping(value="/s/{siteId}/{businessId}/illegalcode")
public class IllegalcodeController  extends SiteBaseController {
	@Resource
	private IIllegalcodeService illegalcodeService;
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request, Model model,@PathVariable String siteId,@PathVariable String businessId,Illegalcode illegalcode) throws Exception{
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		illegalcode.setSiteId(siteId);
		Page page = newPage(request);
		returnDatas.setPage(page);
		returnDatas.setQueryBean(illegalcode);
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		return jump(siteId, businessId,Enumerations.CmsLinkModeType.站长后台列表.getType(), request, model);
	}
	
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable String siteId,@PathVariable String businessId,Illegalcode illegalcode) throws Exception{
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return jump(siteId,businessId,Enumerations.CmsLinkModeType.站长后台更新.getType(),request,model);
	}
	/**
	 * 导入
	 * @param uploadExcelfile
	 * @param request
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/import")
	public void upload(@RequestParam MultipartFile uploadExcelfile,
			HttpServletRequest request, Model model,
			HttpServletResponse response,@PathVariable String siteId) throws Exception {
		String uploadDirPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		MultipartFile image = uploadExcelfile;
		File destFile = new File(uploadDirPath + "/"
				+ image.getOriginalFilename());
		try {
			FileCopyUtils.copy(image.getBytes(), destFile);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
		}
		String info = "";
		try {
			info = illegalcodeService.saveImportExcelFile(destFile,
					Illegalcode.class,siteId,null,false);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
			info = e.getMessage();
		}
		if (StringUtils.isBlank(info)) {
			info = "数据导入成功....";
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		response.getWriter().println("{'msg':'" + info + "'}");

	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody 
	public ReturnDatas saveorupdate(Model model,Illegalcode illegalcode,HttpServletRequest request,HttpServletResponse response,@PathVariable String siteId,@PathVariable String businessId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		illegalcode.setSiteId(siteId);
		try {
			java.lang.String id =illegalcode.getId();
			if(StringUtils.isBlank(id)){
				illegalcode.setId(null);
			}
			illegalcodeService.saveorupdate(illegalcode);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody 
	public  ReturnDatas delete(HttpServletRequest request, @PathVariable String siteId) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			illegalcodeService.deleteById(id,Illegalcode.class);
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
	@ResponseBody 
	public ReturnDatas deleteMore(HttpServletRequest request, Model model,@PathVariable String siteId) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
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
			illegalcodeService.deleteByIds(ids,Illegalcode.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
	}
	
}
