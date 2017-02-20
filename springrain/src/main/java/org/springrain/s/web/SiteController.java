package  org.springrain.s.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springrain.cms.entity.CmsSite;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;


/**
 *  站点管理类
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-01-10 15:15:36
 * @see org.springrain.cms.entity.CmsSite
 */
@Controller
@RequestMapping(value="/s/{siteId}/{businessId}/site")
public class SiteController  extends SiteBaseController {
	@Resource
	private ICmsSiteService cmsSiteService;
	@Resource
	private ICmsLinkService cmsLinkService;
	//private String listurl="/cms/site/siteList";
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param cmsCmsSite
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,CmsSite cmsSite,@PathVariable String siteId,@PathVariable String businessId) 
			throws Exception {
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnDatas.setPage(page);
		returnDatas.setQueryBean(cmsSite);
		model.addAttribute(GlobalStatic.returnDatas, returnDatas);
		return jump(siteId, businessId, "/s/"+siteId+"/"+businessId+"/site/list", request, model);
	}
	
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response,CmsSite cmsSite,@PathVariable String siteId,@PathVariable String businessId)  throws Exception{
		return jump(siteId, businessId, "/s/"+siteId+"/"+businessId+"/site/update/pre", request, model);
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,CmsSite cmsSite,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =cmsSite.getId();
			if(StringUtils.isBlank(id)){
			  cmsSite.setId(null);
			}
		
			cmsSiteService.saveorupdate(cmsSite);
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	
	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response,CmsSite cmsSite,@PathVariable String siteId,@PathVariable String businessId)  throws Exception {
		return jump(siteId, businessId, "/s/"+siteId+"/"+businessId+"/content/look", request, model);
	}
	
	/**
	 * 上传logo
	 * */
	@RequestMapping("/logoupload")
	public @ResponseBody ReturnDatas logoUpload(HttpServletRequest request,String siteId){
		ReturnDatas returnDatas=ReturnDatas.getSuccessReturnDatas();
		
		List<String> logoIdList = new ArrayList<>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		//取得request中的所有文件名
		Iterator<String> iter = multiRequest.getFileNames();
		while(iter.hasNext()){
    		 MultipartFile tempFile = multiRequest.getFile(iter.next());
    		 String logoId;
			try {
				logoId = cmsSiteService.saveTmpLogo(tempFile,siteId);
				logoIdList.add(logoId);
			} catch (IOException e) {
				returnDatas.setMessage("系统异常");
				returnDatas.setStatus(ReturnDatas.ERROR);
				returnDatas.setStatusCode("500");
			}
    		 
    	 }
	    returnDatas.setData(logoIdList);
		return returnDatas;
	}
	
}
