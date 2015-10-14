package org.springrain.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//前端自定义文件目录
	private static final String filepathdir = "filepathdir";
	
	//保存文件的文件夹名称
	private static final String uploadDirName="upload";
	//http的绝对路径
	private static final String httpfilepath="/"+uploadDirName;
	//callback的url的key
	private static final String callbackurlName="callbackurl";
	

	// 1. 创建工厂类
	DiskFileItemFactory factory = new DiskFileItemFactory();
	// 2. 创建FileUpload对象
	ServletFileUpload upload = new ServletFileUpload(factory);

	public FileUpload() {
		super();
		
		 //设置上传文件的最大值
		upload. setFileSizeMax(1024000);
		
		// 上传进度
		upload.setProgressListener(new ProgressListener() {
			long num = 0;
			public void update(long bytesRead, long contentLength, int items) {
				long progress = bytesRead * 100 / contentLength;
				if (progress == num)
					return;
				num = progress;
				System.out.println("上传进度:" + progress + "%");
				// request.getSession().setAttribute("progress", progress);
			}
		});

	}

	/**
	 * 上传文件必须为POST方法
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response. setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 3. 判断是否是上传表单
		boolean b = upload.isMultipartContent(request);
		if (!b) { // 不是文件上传
			return;
		}

		// 4. 解析request，获得FileItem项
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		if (fileitems == null) {
			return;
		}
		// 创建文件
		ServletContext context = getServletContext();
		String dir = context.getRealPath("/")+"/"+uploadDirName;
		String dirpath = null;
	    String httppath=httpfilepath;
	    String callbackurl=null;

		// 5. 遍历集合,获取项目路径
		for (FileItem item : fileitems) {
			if(!item.isFormField()){
				continue;
			}
			if (callbackurlName.equals(item.getFieldName())) {//需要跳转的url
				callbackurl=item.getString();
			}else if (filepathdir.equals(item.getFieldName())) {
				if (item.getString().contains("\\.")) {// 包含路径.
					continue;
				}
				dirpath = item.getString();
			}
		}
		
		if(callbackurl==null){
			return;
		}
		
		
		String allhttpfile="";

		if (dirpath != null) {
			dir = dir + "/" + dirpath;
			httppath=httppath+"/"+dirpath;
		}

		// 5. 遍历集合
		for (FileItem item : fileitems) {
			if (item.isFormField()) {// 普通字段
				continue;
			}
			// 获得文件名
			String filename = item.getName();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			
			File f_dir=new File(dir);
			if(!f_dir.exists()){
				f_dir.mkdirs();
			}
			
			
			File file = new File(dir+"/"+ filename);
			if(!file.exists()){
				file.createNewFile();
			}
		
			allhttpfile=allhttpfile+httppath+"/"+filename+",";
			// 获得流，读取数据写入文件
			InputStream in = item.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);

			int len;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) > 0){
				fos.write(buffer, 0, len);
			}
			
			fos.close();
			in.close();
			item.delete(); // 删除临时文件
		}
		
		if(allhttpfile.endsWith(",")){
			allhttpfile=allhttpfile.substring(0, allhttpfile.length()-1);
		}
		allhttpfile=URLEncoder.encode(allhttpfile);
		
		if(callbackurl.contains("?")){
			callbackurl=callbackurl+"&filename="+allhttpfile;
		}else{
			callbackurl=callbackurl+"?filename="+allhttpfile;
		}
		//跳转到第三方路径
		response.sendRedirect(callbackurl);
	}

}
