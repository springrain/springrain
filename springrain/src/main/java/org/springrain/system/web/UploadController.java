package org.springrain.system.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.ReturnDatas;

@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

	@RequestMapping(value = "/img")
	public @ResponseBody ReturnDatas upload(
			@RequestParam("uploadimgfile") MultipartFile uploadimgfile,
			HttpServletRequest request, Model model,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String userId = SessionUser.getUserId();
		File destFile = null;
		String fn = uploadimgfile.getOriginalFilename();
		long filesize = uploadimgfile.getSize();
		// 判断大小
		if ((filesize - (1024 * 1024 * 10)) > 0) {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("图片大小不得超过10M!");
			return returnObject;
		}
		String houzhui = fn.substring(fn.lastIndexOf(".") + 1);
		// 判断格式
		/*
		 * String geshistr =
		 * "png,jpg,jpeg,gif,bmp,docx,doc,xls,xlsx,ppt,pptx,pdf"; if
		 * (geshistr.indexOf(houzhui.toLowerCase()) < 0) {
		 * returnObject.setStatus(ReturnDatas.ERROR);
		 * returnObject.setMessage("图片仅支持" + geshistr + "格式!"); return
		 * returnObject; }
		 */
		String oldname = fn.substring(0, fn.lastIndexOf("."));
		String url = request.getParameter("url");
		String leixing = request.getParameter("leixing");
		if (StringUtils.isBlank(url)) {
			url = "/upload/picture/";
		}
		if ("zjz".equals(leixing)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String datestr = sdf.format(date);
			url = url + datestr + "/" + userId + "/";
		}
		if ((!url.startsWith("/"))) {
			return ReturnDatas.getErrorReturnDatas();
		}

		try {
			// String uploadDirPath =System.getProperty("user.dir");
			String uploadDirPath = request.getServletContext().getRealPath("/");

			File dir = new File(uploadDirPath + url);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			try {
				String ext = fn.substring(fn.lastIndexOf("."));
				fn = new Date().getTime() + "_" + new Random().nextInt(120000)
						+ ext;
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}
			String filePath = uploadDirPath + url + fn;
			destFile = new File(filePath);
			FileCopyUtils.copy(uploadimgfile.getBytes(), destFile);
			Map map = new HashMap<String, String>();
			map.put("oldname", oldname);
			map.put("fileurl", url + fn);
			map.put("suffix", houzhui);
			map.put("filesize", String.valueOf(filesize));
			returnObject.setData("/upload/getimg?url=" + url + fn);
			returnObject.setMap(map);
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
		}
		return returnObject;
	}

	@RequestMapping(value = "/img/restext")
	// public @ResponseBody ReturnDatas uploadText(
	public void uploadText(
			@RequestParam("uploadimgfile") MultipartFile uploadimgfile,
			HttpServletRequest request, Model model,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String userId = SessionUser.getUserId();
		File destFile = null;
		String fn = uploadimgfile.getOriginalFilename();
		long filesize = uploadimgfile.getSize();
		// 判断大小
		if ((filesize - (1024 * 1024 * 10)) > 0) {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("图片大小不得超过10M!");
			String str = JsonUtils.writeValueAsString(returnObject);
			response.getWriter().write(str);
			return;
			// return returnObject;
		}
		String houzhui = fn.substring(fn.lastIndexOf(".") + 1);
		// 判断格式
		/*
		 * String geshistr =
		 * "png,jpg,jpeg,gif,bmp,docx,doc,xls,xlsx,ppt,pptx,pdf"; if
		 * (geshistr.indexOf(houzhui) < 0) {
		 * returnObject.setStatus(ReturnDatas.ERROR);
		 * returnObject.setMessage("图片仅支持" + geshistr + "格式!"); return
		 * returnObject; }
		 */
		String oldname = fn.substring(0, fn.lastIndexOf("."));
		String url = request.getParameter("url");
		String leixing = request.getParameter("leixing");
		if (StringUtils.isBlank(url)) {
			url = "/upload/picture/";
		}
		if ("zjz".equals(leixing)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String datestr = sdf.format(date);
			url = url + datestr + "/" + userId + "/";
		}
		if ((!url.startsWith("/"))) {
			// return ReturnDatas.getErrorReturnDatas();
			String str = JsonUtils.writeValueAsString(ReturnDatas
					.getErrorReturnDatas());
			response.getWriter().write(str);
			return;
		}

		try {
			// String uploadDirPath = System.getProperty("user.dir");
			String uploadDirPath = request.getServletContext().getRealPath("/");
			File dir = new File(uploadDirPath + url);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			try {
				String ext = fn.substring(fn.lastIndexOf("."));
				fn = new Date().getTime() + "_" + new Random().nextInt(120000)
						+ ext;
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}
			String filePath = uploadDirPath + url + fn;
			destFile = new File(filePath);
			FileCopyUtils.copy(uploadimgfile.getBytes(), destFile);
			Map map = new HashMap<String, String>();
			map.put("oldname", oldname);
			map.put("fileurl", url + fn);
			map.put("suffix", houzhui);
			map.put("filesize", String.valueOf(filesize));
			returnObject.setData("/upload/getimg?url=" + url + fn);
			returnObject.setMap(map);
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
		}
		String str = JsonUtils.writeValueAsString(returnObject);
		response.getWriter().write(str);
		// return returnObject;
	}

	@RequestMapping(value = "/getimg")
	public void getimg(@RequestParam("url") String url,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		if (StringUtils.isBlank(url) || (!url.startsWith("/"))) {
			return;
		}
		response.setContentType("image/*");
		// String uploadDirPath = System.getProperty("user.dir");
		String uploadDirPath = request.getServletContext().getRealPath("/");
		File file = new File(uploadDirPath + url);
		OutputStream output = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file));
		BufferedOutputStream bos = new BufferedOutputStream(output);
		byte data[] = new byte[2048];// 缓冲字节数
		int size = 0;
		size = bis.read(data);
		while (size != -1) {
			bos.write(data, 0, size);
			size = bis.read(data);
		}
		bis.close();
		bos.flush();
		bos.close();
		output.close();
	}

	/**
	 * 下载文件
	 */
	@RequestMapping(value = "/download")
	public void donwnfile(@RequestParam("url") String url,
			@RequestParam("filename") String filename,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		if (StringUtils.isBlank(url) || (!url.startsWith("/"))) {
			return;
		}
		filename = filename + url.substring(url.lastIndexOf("."));
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/*");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ filename);
		// String uploadDirPath = System.getProperty("user.dir");
		String uploadDirPath = request.getServletContext().getRealPath("/");
		// String uploadDirPath = FileUtils.getProjectPath();
		File file = new File(uploadDirPath + url);
		if (!file.isFile()) {
			return;
		}
		OutputStream output = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file));
		BufferedOutputStream bos = new BufferedOutputStream(output);
		byte data[] = new byte[2048];// 缓冲字节数
		int size = 0;
		size = bis.read(data);
		while (size != -1) {
			bos.write(data, 0, size);
			size = bis.read(data);
		}
		bis.close();
		bos.flush();
		bos.close();
		output.close();
	}

	/**
	 * 删除文件
	 */
	@RequestMapping(value = "/delfile")
	public void delfile(@RequestParam("url") String url,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		if (StringUtils.isBlank(url) || (!url.startsWith("/"))) {
			return;
		}
		String userId = SessionUser.getUserId();
		if (StringUtils.isBlank(userId)) {
			return;
		}
		String uploadDirPath = request.getServletContext().getRealPath("/");
		// String uploadDirPath = FileUtils.getProjectPath();
		File file = new File(uploadDirPath + url);
		if (!file.isFile()) {
			return;
		}
		file.delete();
	}

}
