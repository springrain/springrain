package org.springrain.frame.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
/**
 * lucene 工具类
 * @author caomei
 *
 */
public class LuceneUtils {
	
	//分词器
	public static Analyzer analyzer = new SmartChineseAnalyzer();  
	//索引写入配置
	public static IndexWriterConfig indexWriterConfig= new IndexWriterConfig(analyzer);  
	
	//根索引路径
	public static final String rootdir="lucene/index";
	
	public static List searchByEntity(Class clazz,Page page,String field,String searchkeyword) throws Exception{
		File indexDirFile=getIndexDir(clazz);
		if(indexDirFile==null){
			return null;
		}
		
		Path indexDirPath = indexDirFile.toPath();
		//索引不可读
		if(!Files.isReadable(indexDirPath)){
			return null;
		}
		
		//获取索引目录文件
		 Directory indexDir = FSDirectory.open(indexDirPath);
		 //获取读取的索引
		 IndexReader indexReader = DirectoryReader.open(indexDir);
		 //获取索引的查询器
		 IndexSearcher searcher = new IndexSearcher(indexReader);
		 //查询转换器
		 QueryParser parser = new QueryParser(field, analyzer);
		 //转换查询关键字
		 Query query =  parser.parse(searchkeyword);
		 Sort sort=new Sort(new SortField("id",Type.STRING));
		 TopFieldCollector c = TopFieldCollector.create(sort, 20, false, false, false);
		 searcher.search(query, c);
		 ScoreDoc[] hits = c.topDocs(1, 21).scoreDocs;
		 if (hits == null || hits.length < 1){
		     return null;
		 }
     
		 for(ScoreDoc doc:hits){//获取查找的文档的属性数据  
	            int docID=doc.doc;  
	            Document document =searcher.doc(docID);  
	            String str="ID:"+document.get("id")+",姓名："+document.get("name")+"，性别："+document.get("sex");  
	            System.out.println("人员信息:"+str);  
	        }  
		 
		 
		 
		 
		 
		return null;
		
	}
	
	/**
	 * 获取索引的目录
	 * @param clazz
	 * @return
	 */
	public static File getIndexDir(Class clazz){
		if(clazz==null){
			return null;
		}
		File file=new File(rootdir+"/"+clazz.getClass().getName());
		if(!file.exists()){
			file.mkdirs();
		}
		return file;
		
	}
	
	
	public static void doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query, 
			                                    int hitsPerPage, boolean raw, boolean interactive) throws IOException {}
	

}
