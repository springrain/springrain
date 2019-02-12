package org.springrain.system.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.OpenOfficeKit;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.SpringUtils;
import org.springrain.system.manager.service.IBaseSpringrainService;

import freemarker.template.Template;

public class ExportUtils {

	private ExportUtils() {
		throw new IllegalAccessError("工具类不能实例化");
	}

	private static final Logger logger = LoggerFactory.getLogger(ExportUtils.class);

	@Resource
	public SpringUtils springUtils;

	// @Resource
	public static FreeMarkerConfigurer freeMarkerConfigurer = null;

	@SuppressWarnings("unchecked")
	public static <T> File findDataExportFile(Finder finder, String ftlurl, Page page, Class<T> clazz, Object queryBean,
			String fileType, IBaseSpringrainService serviceBean) throws Exception {

		if (freeMarkerConfigurer == null) {
			freeMarkerConfigurer = (FreeMarkerConfigurer) SpringUtils.getBean("freeMarkerConfigurer");
		}

		if (freeMarkerConfigurer == null) {
			return null;
		}

		ReturnDatas returnDatas = new ReturnDatas();
		Map map = new HashMap();
		map.put(GlobalStatic.exportexcel, true);// 设置导出excel变量

		// 获取freeMarker模板
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate(ftlurl + GlobalStatic.suffix);
		page.setPageSize(GlobalStatic.excelPageSize);
		page.setPageIndex(1);
		
		List datas = serviceBean.findListDataByFinder(finder, page, clazz, queryBean);
		returnDatas.setData(datas);
		returnDatas.setPage(page);
		returnDatas.setQueryBean(queryBean);
		returnDatas.setMessage("export");

		map.put(GlobalStatic.returnDatas, returnDatas);
		String fileName = UUID.randomUUID().toString();
		String tempFFilepath = GlobalStatic.tempRootpath + "/" + fileName + "/freemarker.html";
		String tempExcelpath = GlobalStatic.tempRootpath + "/" + fileName + "/" + fileName + fileType;
		File tempfdir = new File(GlobalStatic.tempRootpath + "/" + fileName);
		if (tempfdir.exists() == false) {
			tempfdir.mkdirs();
		}

		File ffile = new File(tempFFilepath);

		File excelFile = new File(tempExcelpath);
		boolean first = true;
		if (ftlurl.contains("Xml"))
			first = false;
		boolean end = false;
		int pageCount = page.getPageCount();
		if (pageCount < 2) {
			pageCount = 1;
			end = true;
		}
		createExceFile(template, ffile, excelFile, first, end, map);
		first = false;
		for (int i = 2; i <= pageCount; i++) {
			if (i == pageCount) {
				end = true;
			}
			page.setPageIndex(i);
			datas = serviceBean.findListDataByFinder(finder, page, clazz, queryBean);
			returnDatas.setData(datas);
			map.put(GlobalStatic.returnDatas, returnDatas);
			createExceFile(template, ffile, excelFile, first, end, map);
		}
		if (ffile.exists()) {
			ffile.delete();
		}

		if (excelFile.exists()) {
			excelFile.setReadOnly();
		}
		// excel转化
		try {
			File excelnew = new File(
					GlobalStatic.tempRootpath + "/" + fileName + "/" + SecUtils.getUUID() + GlobalStatic.excelext);
			OpenOfficeKit.cvtXls(excelFile, excelnew);
			return excelnew;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return excelFile;
	}

	/**
	 * 创建一个 excel 文件
	 * 
	 * @param template
	 * @param ffile
	 * @param excelFile
	 * @param first
	 * @param end
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private static File createExceFile(Template template, File ffile, File excelFile, boolean first, boolean end,
			Map map)
			throws Exception {
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ffile), "UTF-8"));
			template.process(map, out);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("生成freemarker页面错误");
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

		FileInputStream in = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			in = new FileInputStream(ffile);
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			fos = new FileOutputStream(excelFile, true);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
			if (first) {// 如果是第一次,输出编码格式,防止 office 乱码
				bw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			}
			String line = null;

			boolean iswrite = false;
			while ((line = br.readLine()) != null) {
				if (StringUtils.isBlank(line))
					continue;

				line = line.trim();
				if (line.startsWith("<!--first_") && first == false) {// 第一次输出,用于表头列的声明
					iswrite = false;
					continue;
				}
				if (line.startsWith("<!--last_") && end == false) {// 最后的输出,用于结束
					iswrite = false;
					continue;
				}

				if ("<!--first_start_export-->".equals(line)) {// 表头输出里包含的不输出内容开始.
					iswrite = first;
					continue;

				} else if ("<!--last_start_export-->".equals(line)) {// 最后输出的内容开始.
					iswrite = end;
					continue;

				} else if ("<!--first_start_no_export-->".equals(line)) {// 第一次输出内好办的不输出内容开始
					iswrite = false;
					continue;

				} else if ("<!--first_end_no_export-->".equals(line)) {// 第一次输出内好办的不输出内容结束
					iswrite = true;
					continue;

				} else if ("<!--start_no_export-->".equals(line)) {// 开始不输出
					iswrite = false;
					continue;
				} else if ("<!--start_export-->".equals(line)) {// 开始输出
					iswrite = true;
					continue;
				} else if ("<!--last_end_export-->".equals(line)) {// 最后的不输出内容
					iswrite = false;
					continue;
				} else if (line.startsWith("<!--end_")) {// 不包含需要输出的内容
					if ("<!--end_no_export-->".equals(line)) {// 特殊标签,不输出内容
						iswrite = true;
					} else {
						iswrite = false;
					}
					continue;
				}

				if (iswrite) {// 如果是写入标签
					bw.write(line);
					bw.flush();
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("追加xlsx内容错误");
		} finally {
			if (bw != null)
				bw.close();
			if (osw != null)
				osw.close();
			if (fos != null)
				fos.close();
			if (br != null)
				br.close();
			if (in != null)
				in.close();
		}

		return excelFile;
	}

}
