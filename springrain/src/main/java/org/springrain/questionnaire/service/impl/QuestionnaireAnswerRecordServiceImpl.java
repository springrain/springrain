package org.springrain.questionnaire.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springrain.cms.entity.CmsContent;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.OpenOfficeKit;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.SpringUtils;
import org.springrain.questionnaire.entity.QuestionnaireAnswer;
import org.springrain.questionnaire.entity.QuestionnaireAnswerRecord;
import org.springrain.questionnaire.entity.QuestionnaireDetails;
import org.springrain.questionnaire.service.IQuestionnaireAnswerRecordService;
import org.springrain.questionnaire.service.IQuestionnaireDetailsService;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.weixin.sdk.mp.bean.result.WxMpUser;

import freemarker.template.Template;

/**
 * 答题记录相关
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-04-07 11:27:46
 * @see org.springrain.cms.base.service.impl.QuestionnaireAnswerRecord
 */
@Service("questionnaireAnswerRecordService")
public class QuestionnaireAnswerRecordServiceImpl extends
		BaseSpringrainServiceImpl implements IQuestionnaireAnswerRecordService {

	@Resource
	private IQuestionnaireDetailsService questionnaireDetailsService;
	
	@Override
	public String save(Object entity) throws Exception {
		QuestionnaireAnswerRecord questionnaireAnswerRecord = (QuestionnaireAnswerRecord) entity;
		return super.save(questionnaireAnswerRecord).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		QuestionnaireAnswerRecord questionnaireAnswerRecord = (QuestionnaireAnswerRecord) entity;
		return super.saveorupdate(questionnaireAnswerRecord).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		QuestionnaireAnswerRecord questionnaireAnswerRecord = (QuestionnaireAnswerRecord) entity;
		return super.update(questionnaireAnswerRecord);
	}

	@Override
	public QuestionnaireAnswerRecord findQuestionnaireAnswerRecordById(Object id)
			throws Exception {
		return super.findById(id, QuestionnaireAnswerRecord.class);
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object o) throws Exception {
		return super.findListDataByFinder(finder, page, clazz, o);
	}

	/**
	 * 根据查询列表的宏,导出Excel
	 * 
	 * @param finder
	 *            为空则只查询 clazz表
	 * @param ftlurl
	 *            类表的模版宏
	 * @param page
	 *            分页对象
	 * @param clazz
	 *            要查询的对象
	 * @param o
	 *            querybean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl,
			Page page, Class<T> clazz, Object o) throws Exception {
		return super.findDataExportExcel(finder, ftlurl, page, clazz, o);
	}
	
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param ftlurl
	 * @param siteId
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	public <T> File findDataExportExcel(String ftlurl,String siteId,String businessId) throws Exception {
		if(freeMarkerConfigurer==null){
			freeMarkerConfigurer=(FreeMarkerConfigurer) SpringUtils.getBean("freeMarkerConfigurer");
		}
		if(freeMarkerConfigurer==null){
			return null;
		}
		Map map = new HashMap();
		ReturnDatas returnDatas=new ReturnDatas();
		map.put(GlobalStatic.exportexcel, true);// 设置导出excel变量
		Template template = freeMarkerConfigurer.getConfiguration()
				.getTemplate(ftlurl + GlobalStatic.suffix);
		List datas = null;
		// ====================== 获取导出的数据 START======================
		
		// 问题及答案
		Finder finder_qd = Finder.getSelectFinder(QuestionnaireDetails.class);
		finder_qd.append(" where siteId=:siteId and businessId=:businessId ")
			.setParam("siteId", siteId).setParam("businessId", businessId);
		List<QuestionnaireDetails> qdList = 
				questionnaireDetailsService.findListByQuestionnaireId(siteId,businessId);
		
		// 答题记录
		Finder finder_rec = Finder.getSelectFinder(QuestionnaireAnswerRecord.class);
		finder_rec.append(" where siteId = :siteId and businessId=:businessId ")
			.setParam("siteId", siteId).setParam("businessId", businessId);
		List<QuestionnaireAnswerRecord> qarList = super.queryForList(finder_rec, QuestionnaireAnswerRecord.class);
		
		// 统计答案数
		// 思路：可以通过统计答案ID在答题记录表中出现的次数来表明该答案选择的人数
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		String answerIds;
		for(QuestionnaireAnswerRecord qar : qarList){
			answerIds = qar.getAnswerIds();
			if(StringUtils.isEmpty(answerIds)){
				continue;
			}
			String[] answerIdArr = answerIds.split(",");
			for(String answerId : answerIdArr){
				if(countMap.containsKey(answerId)){
					countMap.put(answerId, countMap.get(answerId) + 1);
				}else{
					countMap.put(answerId, 1);
				}
			}
		}
		for(QuestionnaireDetails qd:qdList){
			List<QuestionnaireAnswer> qaList = qd.getQuestionnaireAnswerList();
			if(qaList == null || qaList.size() == 0){
				continue;
			}
			for(QuestionnaireAnswer qa:qaList){
				qa.setSelCount(countMap.get(qa.getId()));
			}
		}
		
		datas = qdList;
		// ====================== 获取导出的数据 END======================
		returnDatas.setData(datas);
		map.put(GlobalStatic.returnDatas, returnDatas);
		String fileName = UUID.randomUUID().toString();
		String tempFFilepath = GlobalStatic.tempRootpath + "/" + fileName
				+ "/freemarker.html";
		String tempExcelpath = GlobalStatic.tempRootpath + "/" + fileName + "/"
				+ fileName + GlobalStatic.excelext;
		File tempfdir = new File(GlobalStatic.tempRootpath + "/" + fileName);
		if (tempfdir.exists() == false) {
			tempfdir.mkdirs();
		}

		File ffile = new File(tempFFilepath);

		File excelFile = new File(tempExcelpath);
		boolean first = true;
		boolean end = false;
		createExceFile(template, ffile, excelFile, first, end, map);
		if (ffile.exists()) {
			ffile.delete();
		}
		if(excelFile.exists()){
			excelFile.setReadOnly();
		}
		// excel转化
		try {
			File excelnew = new File(GlobalStatic.tempRootpath + "/" + fileName
					+ "/" + SecUtils.getUUID() + GlobalStatic.excelext);
			OpenOfficeKit.cvtXls(excelFile, excelnew);
			return excelnew;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
	@SuppressWarnings("rawtypes")
	private File createExceFile(Template template, File ffile, File excelFile,
			boolean first, boolean end, Map map) throws Exception {
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(ffile), "UTF-8"));
			template.process(map, out);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
				if (line.startsWith("<!--first_") && first == false) {
					iswrite = false;
					continue;
				}
				if (line.startsWith("<!--last_") && end == false) {
					iswrite = false;
					continue;
				}

				if ("<!--first_start_export-->".equals(line)) {
					iswrite = first;
					continue;

				} else if ("<!--last_start_export-->".equals(line)) {
					iswrite = end;
					continue;

				} else if ("<!--first_start_no_export-->".equals(line)) {
					iswrite = false;
					continue;

				} else if ("<!--first_end_no_export-->".equals(line)) {
					iswrite = true;
					continue;

				} else if ("<!--start_no_export-->".equals(line)) {
					iswrite = false;
					continue;
				} else if ("<!--start_export-->".equals(line)) {
					iswrite = true;
					continue;
				} else if ("<!--last_start_export-->".equals(line)) {
					iswrite = true;
					continue;
				} else if ("<!--last_end_export-->".equals(line)) {
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
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
	
	@Override
	public String saveAnswerRecordFromWeixin(WxMpUser wxMpUser,
			QuestionnaireAnswerRecord questionnaireAnswerRecord)
			throws Exception {
		if(wxMpUser == null){
			return null;
		}
		questionnaireAnswerRecord.setUserId(wxMpUser.getOpenId());
		questionnaireAnswerRecord.setUserName(wxMpUser.getNickname());
		questionnaireAnswerRecord.setCreateDate(new Date());
		
		String id = String.valueOf(super.save(questionnaireAnswerRecord));
		return id;
	}
	
	

}
