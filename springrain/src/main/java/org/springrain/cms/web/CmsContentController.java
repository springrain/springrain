package  org.springrain.cms.web;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.cms.entity.CmsChannel;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.service.ICmsChannelService;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.cms.utils.SiteUtils;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.InputSafeUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-01-11 16:29:07
 * @see org.springrain.cms.entity.CmsContent
 */
@Controller
@RequestMapping(value="/system/cms/content")
public class CmsContentController  extends BaseController {
	@Resource
	private ICmsContentService cmsContentService;
	@Resource
	private ICmsSiteService cmsSiteService;
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private ICmsLinkService cmsLinkService;
	private String listurl="/cms/content/contentList";
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param cmsContent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,CmsContent cmsContent) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, cmsContent);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param cmsContent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,CmsContent cmsContent) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String siteId = cmsContent.getSiteId();
		String channelId = cmsContent.getChannelId();
		List<CmsContent> datas = null;
		if(StringUtils.isBlank(siteId) && StringUtils.isBlank(channelId)){//页面初始化加载所有数据
			datas = cmsContentService.findListDataByFinder(null,page,CmsContent.class,cmsContent);
		}else if(StringUtils.isBlank(channelId)){//站点点击，获取站点下的数据
			datas = cmsContentService.findListBySiteId(siteId, page);
		}else if(StringUtils.isNotBlank(channelId)){//栏目点击，获取栏目下的数据
			datas = cmsContentService.findContentByChannelId(siteId, channelId, page);
		}

		returnObject.setQueryBean(cmsContent);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,CmsContent cmsContent) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		
		File file = cmsContentService.findDataExportExcel(null,listurl, page,CmsContent.class,cmsContent);
		String fileName="cmsContent"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/cms/content/contentLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			CmsContent cmsContent = cmsContentService.findCmsContentById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("siteList", cmsSiteService.findSiteByUserId(SessionUser.getUserId()));
			returnObject.setMap(map);
			returnObject.setData(cmsContent);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("siteList", cmsSiteService.findSiteByUserId(SessionUser.getUserId()));
			returnObject.setMap(map);
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
	ReturnDatas saveorupdate(Model model,CmsContent cmsContent,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		
		
		String content=cmsContent.getContent();
		cmsContent.setContent(InputSafeUtils.filterRichTextContent(content, SiteUtils.getBaseURL(request)));
		try {
			java.lang.String id =cmsContent.getId();
			if(StringUtils.isBlank(id)){
			  cmsContent.setId(null);
			  cmsContentService.saveContent(cmsContent);
			}else{
				cmsContentService.updateCmsContent(cmsContent);
			}
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		String siteId = request.getParameter("siteId");
		String channelId = request.getParameter("channelId");
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		model.addAttribute("siteId", siteId);
		model.addAttribute("channelId", channelId);
		return "/cms/content/contentCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				cmsContentService.deleteById(id,CmsContent.class);
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
	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
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
			cmsContentService.deleteByIds(ids,CmsContent.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
	}
	
	/**
	 * 获取栏目列表
	 * */
	@RequestMapping("/channelList")
	public @ResponseBody ReturnDatas channelList(HttpServletRequest request){
		ReturnDatas returnDatas=ReturnDatas.getSuccessReturnDatas();
		String siteId = request.getParameter("siteId");
		try {
			List<CmsChannel> channelList = cmsChannelService.findTreeChannel(siteId);
			returnDatas.setData(channelList);
			return returnDatas;
		} catch (Exception e) {
			returnDatas.setMessage("系统异常");
			returnDatas.setStatus(ReturnDatas.ERROR);
			returnDatas.setStatusCode("500");
			return returnDatas;
		}
	}
	
}
