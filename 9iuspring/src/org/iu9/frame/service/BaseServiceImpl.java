/**
 * 
 */
package org.iu9.frame.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import jxl.Cell;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.iu9.frame.common.BaseLogger;
import org.iu9.frame.dao.IBaseJdbcDao;
import org.iu9.frame.entity.IBaseEntity;
import org.iu9.frame.util.ClassUtils;
import org.iu9.frame.util.DateUtils;
import org.iu9.frame.util.EntityInfo;
import org.iu9.frame.util.ExcelUtils;
import org.iu9.frame.util.Finder;
import org.iu9.frame.util.GlobalStatic;
import org.iu9.frame.util.Page;
import org.iu9.frame.util.SpringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
/**
 * 基础的Service父类,所有的Service都必须继承此类,每个数据库都需要一个实现.</br> 
 * 例如 testdb1数据的实现类是org.iu9.testdb1.service.BaseTestdb1ServiceImpl,testdb2数据的实现类是org.iu9.testdb2.service.Basetestdb2ServiceImpl</br>
 * 
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.frame.service.BaseServiceImpl
 */
public abstract class BaseServiceImpl extends BaseLogger implements
		IBaseService {

	@Resource
	private SpringUtils springUtils;
	@Resource
	FreeMarkerConfigurer freeMarkerConfigurer;

	public abstract IBaseJdbcDao getBaseDao();

	/**
	 * 获取spring Bean
	 */
	@Override
	public Object getBean(String beanName) throws Exception {
		if (beanName == null)
			return null;
		return getSpringUtils().getBean(beanName);
	}

	/**
	 * @return the springUtils
	 */
	public SpringUtils getSpringUtils() {
		return springUtils;
	}

	@Override
	public <T> List<T> queryForList(Finder finder, Class<T> clazz)
			throws Exception {
		return getBaseDao().queryForList(finder, clazz);
	}

	@Override
	public List<Map<String, Object>> queryForList(Finder finder)
			throws Exception {
		return getBaseDao().queryForList(finder);
	}

	/**
	 * 执行函数 返回执行结果为Map
	 * 
	 * @param finder
	 * @return
	 */
	@Override
	public Object queryObjectByFunction(Finder finder) {
		return getBaseDao().queryObjectByFunction(finder);
	}

	@Override
	public <T> T findById(Object id, Class<T> clazz, String tableExt)
			throws Exception {
		return getBaseDao().findByID(id, clazz, tableExt);
	}

	/**
	 * 执行存储过程 返回执行结果为
	 * 
	 * @param finder
	 * @return
	 */
	@Override
	public Map<String, Object> queryObjectByProc(Finder finder) {
		return getBaseDao().queryObjectByProc(finder);
	}

	@Override
	public <T> T queryForObject(Finder finder, Class<T> clazz) throws Exception {

		return getBaseDao().queryForObject(finder, clazz);
	}

	@Override
	public Map<String, Object> queryForObject(Finder finder) throws Exception {
		return getBaseDao().queryForObject(finder);
	}

	@Override
	public Integer update(Finder finder) throws Exception {

		return getBaseDao().update(finder);
	}

	@Override
	public void deleteById(Object id, Class clazz) throws Exception {
		getBaseDao().deleteById(id, clazz);
	}

	@Override
	public void deleteByIds(List ids, Class clazz) throws Exception {
		getBaseDao().deleteByIds(ids, clazz);
	}

	@Override
	public void deleteByEntity(IBaseEntity entity) throws Exception {
		EntityInfo entityInfoByEntity = ClassUtils
				.getEntityInfoByEntity(entity);
		String tableName = entityInfoByEntity.getTableName();
		String tableExt = entityInfoByEntity.getTableExt();
		if (StringUtils.isNotBlank(tableExt)) {
			tableName = tableName + tableExt;
		}

		Finder finder = new Finder("DELETE FROM ");
		finder.append(tableName).append(" WHERE 1=1 ");
		getFinderWhereByQueryBean(finder, entity);
		getBaseDao().update(finder);
	}

	@Override
	public <T> List<T> queryForList(Finder finder, Class<T> clazz, Page page)
			throws Exception {
		return getBaseDao().queryForList(finder, clazz, page);
	}

	@Override
	public List<Map<String, Object>> queryForList(Finder finder, Page page)
			throws Exception {
		return getBaseDao().queryForList(finder, page);
	}

	/**
	 * Entity作为查询的query bean,并返回Entity
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> T queryForObject(T entity) throws Exception {
		return getBaseDao().queryForObject(entity);

	}

	/**
	 * Entity作为查询的query bean,并返回Entity
	 * 
	 * @param entity
	 * @param page
	 *            分页对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> queryForListByEntity(T entity, Page page)
			throws Exception {
		return getBaseDao().queryForListByEntity(entity, page);

	}

	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		return getBaseDao()
				.findListDataByFinder(finder, page, clazz, queryBean);
	}

	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl,
			Page page, Class<T> clazz, Object queryBean) throws Exception {
		Map map = new HashMap();
		map.put(GlobalStatic.exportexcel, true);// 设置导出excel变量
		Template template = freeMarkerConfigurer.getConfiguration()
				.getTemplate(ftlurl + GlobalStatic.suffix);
		page.setPageSize(GlobalStatic.excelPageSize);
		page.setPageIndex(1);
		List datas = findListDataByFinder(finder, page, clazz, queryBean);
		map.put("datas", datas);
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
		int pageCount = page.getPageCount();
		if (pageCount < 2) {
			pageCount = 1;
			end = true;
		}
		CreateExceFile(template, ffile, excelFile, first, end, map);
		first = false;
		for (int i = 2; i <= pageCount; i++) {
			if (i == pageCount) {
				end = true;
			}
			page.setPageIndex(i);
			datas = findListDataByFinder(finder, page, clazz, queryBean);
			map.put("datas", datas);
			CreateExceFile(template, ffile, excelFile, first, end, map);
		}
		if (ffile.exists()) {
			ffile.delete();
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
	private File CreateExceFile(Template template, File ffile, File excelFile,
			boolean first, boolean end, Map map) throws Exception {
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(ffile), "UTF-8"));
			template.process(map, out);
		} catch (Exception e) {
			logger.error(e);
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
			logger.error(e);
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
	public Object save(Object entity) throws Exception {
		return getBaseDao().save(entity);
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		return getBaseDao().update(entity);
	}

	@Override
	public <T> T findById(Object id, Class<T> clazz) throws Exception {
		return getBaseDao().findByID(id, clazz);
	}

	@Override
	public Object saveorupdate(Object entity) throws Exception {
		return getBaseDao().saveorupdate(entity);
	}

	public <T> T queryForObjectByProc(Finder finder, Class<T> clazz)
			throws Exception {
		return getBaseDao().queryForObjectByProc(finder, clazz);
	}

	public <T> T queryForObjectByByFunction(Finder finder, Class<T> clazz)
			throws Exception {
		return getBaseDao().queryForObjectByByFunction(finder, clazz);
	}

	public <T> List<T> queryForListByFunction(Finder finder, Class<T> clazz)
			throws Exception {
		return getBaseDao().queryForListByFunction(finder, clazz);
	}

	/**
	 * 根据查询的queryBean,拼接Finder 的 Where条件,只包含 and 条件,用于普通查询
	 * 
	 * @param finder
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public Finder getFinderWhereByQueryBean(Finder finder, Object o)
			throws Exception {
		return getBaseDao().getFinderWhereByQueryBean(finder, o);
	}
	@Override
	public Finder getFinderOrderBy(Finder finder,Page page)	throws Exception{
		return getBaseDao().getFinderOrderBy(finder, page);
	}
	
	
	
	@Override
	public <T> String saveImportExcelFile(File excelFile, Class<T> clazz,
			boolean istest) throws Exception {
		StringBuffer message = new StringBuffer();
		List<Cell[]> excel = ExcelUtils.getExcle(excelFile);
		if (CollectionUtils.isEmpty(excel)) {
			return "Excel文件没有sheet!";
		}
		Map<Integer, String> map = new HashMap<Integer, String>();
		Cell[] title = excel.get(0);
		if (title == null || title.length < 1) {
			return "表头没有数据!";
		}
		List<String> listTitle=new ArrayList<String>();
		// 封装字段
		for (int i = 0; i < title.length; i++) {
			map.put(i, title[i].getContents());
			listTitle.add(title[i].getContents());
		}

		for (int j = 2; j < excel.size(); j++) {
			Cell[] cells = excel.get(j);
			T r = clazz.newInstance();
			for (int m = 0; m < cells.length; m++) {
				Object o = r;
				Cell cell = cells[m];
				String name = map.get(m);
				try {
					if (name.contains(".")) {
						String[] strs = name.split("\\.");
						// 获取最后的属性名称
						name = strs[strs.length - 1];
						// 循环获取实体对象
						for (int i = 0; i < strs.length - 1; i++) {
							o = ClassUtils.getPropertieValue(strs[i], o);
						}
					}
				} catch (Exception e) {
					String s = "第" + (m + 1) + "列列名有错误," + name + " 类型错误!";
					if (istest) {
						message.append(s).append("</br>");
					} else {
						throw new Exception(s);
					}
				}
				String value = cell.getContents().trim();
				String className = ClassUtils.getReturnType(name, o)
						.toLowerCase();
				if (className.contains(".string")) {
					try {
						ClassUtils.setPropertieValue(name, o, value);
					} catch (Exception e) {
						String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:"+ name + " 类型错误!";
						if (istest) {
							message.append(s).append("</br>");
						} else {
							throw new Exception(s);
						}
					}
				} else if (className.contains(".date")) {
					try {
						value = value.replace("/", "-");
						Date d = DateUtils.convertString2Date(value);

						ClassUtils.setPropertieValue(name, o, d);
					} catch (Exception e) {
						String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:"+ name + " 类型错误!";
						if (istest) {
							message.append(s).append("</br>");
						} else {
							throw new Exception(s);
						}
					}

				} else if (className.contains(".double")) {
					try {
						Double db = null;
						if (StringUtils.isNotBlank(value)) {
							db = Double.valueOf(value);
						}
						ClassUtils.setPropertieValue(name, o, db);
					} catch (Exception e) {
						String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:"+ name + " 类型错误!";
						if (istest) {
							message.append(s).append("</br>");
						} else {
							throw new Exception(s);
						}
					}
				} else if (className.contains(".float")) {
					try {
						Float f = null;
						if (StringUtils.isNotBlank(value)) {
							f = Float.valueOf(value);
						}
						ClassUtils.setPropertieValue(name, o, f);
					} catch (Exception e) {
						String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:"+ name + " 类型错误!";
						if (istest) {
							message.append(s).append("</br>");
						} else {
							throw new Exception(s);
						}
					}

				} else if (className.contains(".integer")) {
					try {
						Integer _i = null;

						if (StringUtils.isNotBlank(value)) {
							_i = Integer.valueOf(value);
						}

						ClassUtils.setPropertieValue(name, o, _i);
					} catch (Exception e) {
						String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:"+ name + " 类型错误!";
						if (istest) {
							message.append(s).append("</br>");
						} else {
							throw new Exception(s);
						}
					}
				}

				else if (className.contains(".bigdecimal")) {
					try {
						BigDecimal bd = null;
						if (StringUtils.isNotBlank(value)) {
							bd = new BigDecimal(value);
						}
						ClassUtils.setPropertieValue(name, o, bd);
					} catch (Exception e) {
						String s = "第" + (j + 1) + "行,第" + (m + 1) + "列:"+ name + " 类型错误!";
						if (istest) {
							message.append(s).append("</br>");
						} else {
							throw new Exception(s);
						}
					}
				}

			}
			try {
				String s=saveFromExcel(r, (j + 1), istest, listTitle);
				if(istest&&StringUtils.isNotBlank(s)){
					message.append(s).append("</br>");
				}
			} catch (Exception e) {
				throw new Exception("第" + (j + 1) + "行,保存失败");
			}
		}
		if (excelFile.exists()&&(istest==false)) {
			excelFile.delete();
		}
		if(StringUtils.isBlank(message.toString())){
			return null;
		}
		if (excelFile.exists()&&istest&&StringUtils.isNotBlank(message.toString())) {
			excelFile.delete();
		}
		
		
		return message.toString();

	}

	@Override
	public <T> String saveImportExcelFile(File excelFile, Class<T> clazz)
			throws Exception {
		String message = saveImportExcelFile(excelFile, clazz, true);
		if (StringUtils.isNotBlank(message)) {
			return message;
		}
		return saveImportExcelFile(excelFile, clazz, false);

	}

	@Override
	public String saveFromExcel(Object entity, int index, boolean istest,
			List<String> listTitle) throws Exception {
		if(istest){
			return null;
		}
		return save(entity).toString();
	}

}
