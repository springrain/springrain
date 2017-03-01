package org.springrain.s.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;

@Controller
@RequestMapping(value="/s/{siteId}/{businessId}/file")
public class FileUploadController {
	/**
	 * 上传logo
	 * @throws IOException 
	 * */
	@RequestMapping("/logoupload")
	public @ResponseBody ReturnDatas logoUpload(HttpServletRequest request,@PathVariable String siteId,@PathVariable String businessId) throws IOException{
		ReturnDatas returnDatas=ReturnDatas.getSuccessReturnDatas();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	    List<Map<String, String>> fileList = uploadFile(multiRequest,siteId,businessId);
		returnDatas.setData(fileList);
		return returnDatas;
	}

	private List<Map<String, String>> uploadFile(
			MultipartHttpServletRequest multiRequest, String siteId,String businessId) throws IOException {
		Iterator<String> iter = multiRequest.getFileNames();
		List<Map<String, String>> fileList = new ArrayList<>(); 
		while(iter.hasNext()){
			MultipartFile tempFile = multiRequest.getFile(iter.next());
    		String[] fullFileName = StringUtils.split(tempFile.getOriginalFilename(), ".");
    		String fileName = fullFileName[0];
    		String prefix = fullFileName[1];
			
			String path = "/upload/"+siteId+"/"+businessId+"/"+SecUtils.getUUID()+tempFile.getOriginalFilename();
    		File file = new File(GlobalStatic.rootdir+path);
    		if(!file.getParentFile().exists())
    			file.getParentFile().mkdirs();
    		if(!file.exists())
    			file.createNewFile();
    		tempFile.transferTo(file);
    		Map<String, String> uploadFileMap = new HashMap<>();
    		uploadFileMap.put("name", fileName);
    		uploadFileMap.put("path", path);
    		uploadFileMap.put("size", file.getTotalSpace()+"");
    		uploadFileMap.put("prefix", prefix);
    		
    		fileList.add(uploadFileMap);
		}
		
		return fileList;
	}
}
