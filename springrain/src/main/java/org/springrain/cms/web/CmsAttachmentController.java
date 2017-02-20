package org.springrain.cms.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springrain.cms.service.ICmsAttachmentService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.ReturnDatas;

@Controller
@RequestMapping(value="/cms/attachment/{siteId}/{businessId}")
public class CmsAttachmentController extends BaseController {
	
	@Resource
	private ICmsAttachmentService cmsAttachmentService;
	@Resource
	private ICmsSiteService cmsSiteService;

	@RequestMapping("/upload")
	public @ResponseBody ReturnDatas uploadFiles(HttpServletRequest request,@PathVariable String siteId,@PathVariable String businessId) throws Exception{
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		Iterator<String> fileNames = multiRequest.getFileNames();
		List<String> attachmentPathList = new ArrayList<>();
		while(fileNames.hasNext()){
			MultipartFile file = multiRequest.getFile(fileNames.next());
			String attachmentPathUrl = cmsAttachmentService.saveAttachment(file,siteId,businessId);
			attachmentPathList.add(attachmentPathUrl);
		}
		
		returnDatas.setData(attachmentPathList);
		return returnDatas;
	}
}
