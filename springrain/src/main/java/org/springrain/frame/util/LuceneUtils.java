package org.springrain.frame.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.shiro.util.CollectionUtils;

/**
 * lucene 工具类
 * 
 * @author caomei
 *
 */
public class LuceneUtils {

	// 分词器
	public static Analyzer analyzer = new SmartChineseAnalyzer();


	// 根索引路径
	public static final String rootdir = "lucene/index";
	/**
	 * 根据实体类查询结果
	 * @param clazz
	 * @param page
	 * @param searchkeyword
	 * @return
	 * @throws Exception
	 */
	public static List search(Class clazz, Page page, String searchkeyword) throws Exception {
		List<String> luceneFields = ClassUtils.getLuceneFields(clazz);
		if(CollectionUtils.isEmpty(luceneFields)){
			return null;
		}
		String[] fields = (String[])luceneFields.toArray(new String[luceneFields.size()]);
		return search(clazz, page,fields, searchkeyword);
	}
	
	/**
	 * 根据某个字段类查询结果
	 * @param clazz
	 * @param page
	 * @param searchkeyword
	 * @return
	 * @throws Exception
	 */
	public static List search(Class clazz, Page page,String field, String searchkeyword) throws Exception {
		if(StringUtils.isBlank(field)){
			return null;
		}
		String[] fields = new String[]{field};
		return search(clazz, page,fields, searchkeyword);
	}
	
	
/**
 * 
 * @param clazz
 * @param page
 * @param fields
 * @param searchkeyword
 * @return
 * @throws Exception
 */
	public static <T> List<T> search(Class<T> clazz, Page page, String[] fields ,String searchkeyword) throws Exception {
		
		if(fields==null||fields.length<1){
			return null;
		}
		

		// 获取索引目录文件
		Directory directory = getDirectory(clazz);
		if (directory == null) {
			return null;
		}

		// 获取读取的索引
		IndexReader indexReader = DirectoryReader.open(directory);
		// 获取索引的查询器
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 查询指定字段的转换器
	//	QueryParser parser = new QueryParser(field, analyzer);
		QueryParser parser = new MultiFieldQueryParser(fields, analyzer);
		// 需要查询的关键字
		Query query = parser.parse(searchkeyword);
		
		// 查询出的结果文档
	    int _size=20;
		if(page!=null&&page.getPageSize()>0){
			_size=page.getPageSize();
		}
		// 查询出的结果文档
		ScoreDoc[] hits = indexSearcher.search(query, _size).scoreDocs;
		
		if(hits==null||hits.length<1){
			return null;
		}
		
		List <T> list=new ArrayList<T>(hits.length);
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = indexSearcher.doc(hits[i].doc);
			T t=clazz.newInstance();
			for(String fieldName:fields){
				String fieldValue = hitDoc.get(fieldName);
				ClassUtils.setPropertieValue(fieldName,t, fieldValue);
			}
			list.add(t);
		}
		indexReader.close();
		directory.close();
		
		return list;
	}

	/**
	 * 测试 保存 主键
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static String saveDocument(Object entity) throws Exception {
		//获取索引的字段,为null则不进行保存
		List<String> luceneFields = ClassUtils.getLuceneFields(entity.getClass());
		if(CollectionUtils.isEmpty(luceneFields)){
			return "error";
		}
		
		// 索引写入配置
	   IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		// 获取索引目录文件
		Directory directory = getDirectory(entity.getClass());
		if (directory == null) {
			return null;
		}
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		Document doc = new Document();
		
		for(String fieldName:luceneFields){
			String _value = ClassUtils.getPropertieValue(fieldName, entity).toString();
			doc.add(new Field(fieldName, _value, TextField.TYPE_STORED));
		}
		indexWriter.addDocument(doc);
		indexWriter.close();
		directory.close();

		return null;
	}

	/**
	 * 获取索引的目录
	 * 
	 * @param clazz
	 * @return
	 */
	public static File getIndexDirFile(Class clazz) {
		if (clazz == null) {
			return null;
		}
		File file = new File(rootdir + "/" + clazz.getName());
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;

	}

	/**
	 * 获取实体类的索引文档
	 * 
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static Directory getDirectory(Class clazz) throws IOException {
		File indexDirFile = getIndexDirFile(clazz);
		if (indexDirFile == null) {
			return null;
		}
		Path indexDirPath = indexDirFile.toPath();
		// 索引不可读
		if (!Files.isReadable(indexDirPath)) {
			return null;
		}
		// 获取索引目录文件
		Directory directory = FSDirectory.open(indexDirPath);
		return directory;

	}

}
