package org.springrain.frame.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springrain.frame.entity.BaseEntity;

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

	public static List searchByEntity(Class clazz, Page page, String field, String searchkeyword) throws Exception {

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
		QueryParser parser = new QueryParser(field, analyzer);
		// 需要查询的关键字
		Query query = parser.parse(searchkeyword);
		// 查询出的结果文档
		ScoreDoc[] hits = indexSearcher.search(query, page.getPageSize()).scoreDocs;

		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = indexSearcher.doc(hits[i].doc);
			System.out.println(hitDoc.get(field));
		}

		/*
		 * Sort sort=new Sort(new SortField("id",Type.STRING));
		 * TopFieldCollector c = TopFieldCollector.create(sort, 20, false,
		 * false, false); searcher.search(query, c); ScoreDoc[] hits =
		 * c.topDocs(1, 21).scoreDocs; if (hits == null || hits.length < 1){
		 * return null; }
		 * 
		 * for(ScoreDoc doc:hits){//获取查找的文档的属性数据 int docID=doc.doc; Document
		 * document =searcher.doc(docID); String
		 * str="ID:"+document.get("id")+",姓名："+document.get("name")+"，性别："+
		 * document.get("sex"); System.out.println("人员信息:"+str); }
		 */

		indexReader.close();

		directory.close();

		return null;

	}

	/**
	 * 测试 保存 主键
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static String saveDocumentByEntity(BaseEntity entity) throws Exception {
		// 索引写入配置
	   IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		// 获取索引目录文件
		Directory directory = getDirectory(entity.getClass());
		if (directory == null) {
			return null;
		}
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		Document doc = new Document();
		String key = ClassUtils.getEntityInfoByClass(entity.getClass()).getPkName();
		String _value = ClassUtils.getPKValue(entity).toString();
		doc.add(new Field(key, _value, TextField.TYPE_STORED));
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
