package org.springrain.frame.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatPoint;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * lucene 工具类
 * 
 * @author caomei
 *
 */
public class LuceneUtils {

	private LuceneUtils(){
		throw new IllegalAccessError("工具类不能实例化");
	}
	
	// 分词器
	private static Analyzer analyzer = new SmartChineseAnalyzer();

	
	/**
	 * 根据实体类查询结果
	 * 
	 * @param clazz
	 * @param page
	 * @param searchkeyword
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List searchDocument(String rootdir,Class clazz, Page page,
			String searchkeyword) throws Exception {
		List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(clazz);
		if (CollectionUtils.isEmpty(luceneFields)) {
			return null;
		}
		
		String[] fields=new String[luceneFields.size()];
		
		for(int i=0;i<luceneFields.size();i++){
		    fields[i]=luceneFields.get(i).getFieldName();
		}
		return searchDocument( rootdir,clazz, page, fields, searchkeyword);
	}

	
	/**
	 * 根据某个字段类查询结果
	 * 
	 * @param clazz
	 * @param page
	 * @param searchkeyword
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List searchDocument(String rootdir,Class clazz, Page page, String field,
			String searchkeyword) throws Exception {
		if (clazz==null||StringUtils.isBlank(field)) {
			return null;
		}
		String[] fields = new String[] { field };
		return searchDocument( rootdir,clazz, page, fields, searchkeyword);
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
	public static <T> List<T> searchDocument(String rootdir,Class<T> clazz, Page page,
			String[] fields, String searchkeyword) throws Exception {

		if (clazz==null||fields == null || fields.length < 1) {
			return null;
		}

		// 查询指定字段的转换器
		QueryParser parser = new MultiFieldQueryParser(fields, analyzer);
		// 需要查询的关键字
		Query query = parser.parse(searchkeyword);
		
		return searchDocument(rootdir, clazz, page, query);
	
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
    public static <T> List<T> searchDocumentByTerm(String rootdir,Class<T> clazz, Page page,
            String key,String value) throws Exception {

        if (clazz==null||StringUtils.isBlank(key)||StringUtils.isBlank(value)) {
            return null;
        }
        
        Term term = new Term(key, value);
        TermQuery termQuery = new TermQuery(term);
        return searchDocument(rootdir, clazz, page, termQuery);
    }
    
    
  /**
   * 根据精确值查询一个对象
   * @param rootdir
   * @param clazz
   * @param key
   * @param value
   * @return
   * @throws Exception
   */
    public static <T> T searchDocumentByTerm(String rootdir,Class<T> clazz,
            String key,String value) throws Exception {

        if (StringUtils.isBlank(key)||StringUtils.isBlank(value)) {
            return null;
        }
        
        Page page=new Page(1);
        page.setPageSize(1);
        
      List<T> list=  searchDocumentByTerm(rootdir, clazz, page,   key, value);
      if(CollectionUtils.isEmpty(list)){
          return null;
      }
        
        return list.get(0);
    }
    
    
    /**
     * 根据entityId 查询一个对象
     * @param rootdir
     * @param clazz
     * @param value
     * @return
     * @throws Exception
     */
    public static <T> T searchDocumentById(String rootdir,Class<T> clazz,String value) throws Exception {

        if (clazz==null||StringUtils.isBlank(value)) {
            return null;
        }
        
        EntityInfo info=ClassUtils.getEntityInfoByClass(clazz);
        if(info==null){
            return null;
        }
      return searchDocumentByTerm(rootdir, clazz,  info.getPkName(), value);
    }
    
 	
	
	public static <T> List<T> searchDocument(String rootdir,Class<T> clazz, Page page,
           Query query) throws Exception {
        // 获取索引目录文件
        Directory directory = getDirectory( rootdir,clazz);
        if (directory == null) {
            return null;
        }

        // 获取读取的索引
        IndexReader indexReader = DirectoryReader.open(directory);
        // 获取索引的查询器
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

      
        TopDocs topDocs = null;
        int totalCount = indexSearcher.count(query);
        if (totalCount == 0) {
            return null;
        }
        if (page == null) {
            topDocs = indexSearcher.search(query, totalCount);
        } else {
            // 查询出的结果文档
            int _size = 20;
            if (page != null && page.getPageSize() > 0) {
                _size = page.getPageSize();
            }
            // 总条数
            page.setTotalCount(totalCount);
            int _max = page.getPageIndex() * (page.getPageIndex() - 1);
            if (_max - totalCount >= 0) {
                return null;
            }

            // 先获取上一页的最后一个元素
            ScoreDoc lastscoreDoc = getLastScoreDoc(page.getPageIndex(), _size,query, indexSearcher);
            topDocs = indexSearcher.searchAfter(lastscoreDoc, query, _size);
        }
        // 通过最后一个元素搜索下页的pageSize个元素

        // 查询出的结果文档
        ScoreDoc[] hits = topDocs.scoreDocs;

        if (hits == null || hits.length < 1) {
            return null;
        }

        List<T> list = new ArrayList<>(hits.length);
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = indexSearcher.doc(hits[i].doc);
            T t = clazz.newInstance();
            document2Bean(hitDoc, t);
            list.add(t);
        }
        indexReader.close();
        directory.close();

        return list;
    }
	
	
	
	
	private static Object  document2Bean(Document document, Object t) throws Exception{
	    
	    if(document==null||t==null){
	        return null;
	    }
	    
	    List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(t.getClass());
	    
	    if(CollectionUtils.isEmpty(luceneFields)){
	        return null;
	    }
	    for (FieldInfo finfo : luceneFields) {
	        String fieldName=finfo.getFieldName();
	        
            String fieldValue = document.get(fieldName);
            
            String typeName =finfo.getFieldType().getSimpleName().toLowerCase();
            if(typeName.equals("integer")||typeName.equals("int")){//数字
                ClassUtils.setPropertieValue(fieldName, t, Integer.valueOf(fieldValue));
            }else if(typeName.equals("biginteger")){//数字
                ClassUtils.setPropertieValue(fieldName, t, new BigInteger(fieldValue));
            }else if(typeName.equals("long")){//数字
                ClassUtils.setPropertieValue(fieldName, t, Long.valueOf(fieldValue));
            }else if(typeName.equals("float")){//数字
                ClassUtils.setPropertieValue(fieldName, t, Float.valueOf(fieldValue));
            }else if(typeName.equals("double")){//数字
                ClassUtils.setPropertieValue(fieldName, t, Double.valueOf(fieldValue));
            }else if(typeName.equals("bigdecimal")){//数字
                ClassUtils.setPropertieValue(fieldName, t, new BigDecimal(fieldValue));
            }else if(typeName.equals("date")){//日期
              //  ClassUtils.setPropertieValue(fieldName, t, DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, fieldValue.toString()));
                ClassUtils.setPropertieValue(fieldName, t, new Date(Long.valueOf(fieldValue)));
            }else{
                ClassUtils.setPropertieValue(fieldName, t, fieldValue);
            }
        }
	    
	    return t;
	    
	}
	
	
	
	
	private static Document bean2Document(Object entity) throws Exception{
	 // 获取索引的字段,为null则不进行保存
        List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(entity.getClass());
        if (CollectionUtils.isEmpty(luceneFields)) {
            return null;
        }

	    Document doc = new Document();
        for (FieldInfo finfo : luceneFields) {
            
            String fieldName=finfo.getFieldName();
            Object _obj = ClassUtils.getPropertieValue(fieldName, entity);
            if (_obj == null||StringUtils.isBlank(_obj.toString())) {
                continue;
            }
            String _value = _obj.toString();
            
            Field _field = null;
            
            String typeName =finfo.getFieldType().getSimpleName().toLowerCase();
            if(typeName.equals("int")||typeName.equals("integer")){//数字进行存储和索引,不进行分词
                _field=new StoredField(fieldName, Integer.valueOf(_value));
                doc.add(new IntPoint(fieldName,  Integer.valueOf(_value)));
            }else if(typeName.equals("long")){//数字进行存储和索引,不进行分词
                _field=new StoredField(fieldName, Long.valueOf(_value));
                doc.add(new LongPoint(fieldName,  Long.valueOf(_value)));
            }else if(typeName.equals("float")){//数字进行存储和索引,不进行分词
                _field=new StoredField(fieldName, Float.valueOf(_value));
                doc.add(new FloatPoint(fieldName,  Float.valueOf(_value)));
            }else if(typeName.equals("double")){//数字进行存储和索引,不进行分词
             _field=new StoredField(fieldName, Double.valueOf(_value));
             doc.add(new DoublePoint(fieldName,  Double.valueOf(_value)));
            }else if(typeName.equals("date")){//数字进行存储和索引,不进行分词
            // _field=new StringField(fieldName, DateUtils.convertDate2String(DateUtils.DEFAILT_DATE_TIME_PATTERN,(Date)_obj), Store.YES);
             _field=new StoredField(fieldName, ((Date)_obj).getTime());
             doc.add(new LongPoint(fieldName,  ((Date)_obj).getTime()));
            }else if(typeName.equals("biginteger")){//数字
                _field=new StringField(fieldName, _value, Store.YES);
            }else if(typeName.equals("bigdecimal")){//进行存储和索引,不进行分词引
                _field=new StringField(fieldName, _value, Store.YES);
            }else if(finfo.getPk()){//如果是主键,进行存储和索引,不进行分词引
                _field=new StringField(fieldName, _value, Store.YES);
            }else{
             _field = new TextField(fieldName, _value, Store.YES);
         }
                //_field = new Field(fieldName, _value, TextField.TYPE_STORED);
            
            doc.add(_field);
        }
        
        return doc;
	    
	    
	}
	
	
	

	/**
	 * 根据实体类保存到索引,使用 LuceneSearch和LuceneField
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public  static String saveDocument(String rootdir,Object entity)
			throws Exception {
		
		// 索引写入配置
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		// 获取索引目录文件
		Directory directory = getDirectory( rootdir,entity.getClass());
		if (directory == null) {
			return null;
		}
		
		
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		
	    Document doc=bean2Document(entity);
		
		indexWriter.addDocument(doc);
		indexWriter.commit();
		indexWriter.close();
		directory.close();
		return null;
	}

	/**
	 * 根据实体类批量保存到索引,使用 LuceneSearch和LuceneField
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static <T> String saveListDocument(String rootdir,List<T> list) throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return "error";
		}
		for (T t : list) {
			saveDocument( rootdir,t);
		}

		return null;
	}

	/**
	 * 删除文档
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public  static String deleteDocument(String rootdir,Object id, Class clazz)
			throws Exception {
		List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(clazz);
		if (CollectionUtils.isEmpty(luceneFields)) {
			return "error";
		}

		String pkName = ClassUtils.getEntityInfoByClass(clazz).getPkName();
		// 索引写入配置
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		// 获取索引目录文件
		Directory directory = getDirectory( rootdir,clazz);
		if (directory == null) {
			return null;
		}
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		
		// 需要查询的关键字
		Term term = new Term(pkName,id.toString());
		TermQuery luceneQuery = new TermQuery(term);
		indexWriter.deleteDocuments(luceneQuery);
		indexWriter.commit();
		indexWriter.close(); // 记得关闭,否则删除不会被同步到索引文件中
		directory.close(); // 关闭目录
		
		return null;
	}

	/**
	 * 批量删除文档
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String deleteListDocument(String rootdir,List<String> ids, Class clazz)
			throws Exception {
		List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(clazz);
		if (CollectionUtils.isEmpty(luceneFields)) {
			return "error";
		}
		if (CollectionUtils.isEmpty(ids)) {
			return "error";
		}
		String pkName = ClassUtils.getEntityInfoByClass(clazz).getPkName();
		// 索引写入配置
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		// 获取索引目录文件
		Directory directory = getDirectory( rootdir,clazz);
		if (directory == null) {
			return null;
		}
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		
		TermQuery[] listTermQuery=new TermQuery[ids.size()];
		
		for (int i=0;i<ids.size();i++) {
			// 需要查询的关键字
			Term term = new Term(pkName,ids.get(i));
			TermQuery luceneQuery = new TermQuery(term);
			listTermQuery[i]=luceneQuery;
		}
		indexWriter.deleteDocuments(listTermQuery);
		indexWriter.commit();
		indexWriter.close(); // 记得关闭,否则删除不会被同步到索引文件中
		directory.close(); // 关闭目录
		return null;
	}
	
	/**
	 * 删除所有索引
	 * @param ids
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String deleteDocumentAll(String rootdir,Class clazz)
			throws Exception {
		// 索引写入配置
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		// 获取索引目录文件
		Directory directory = getDirectory( rootdir,clazz);
		if (directory == null) {
			return null;
		}
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		indexWriter.deleteAll();
		indexWriter.commit();
		indexWriter.close(); // 记得关闭,否则删除不会被同步到索引文件中
		directory.close(); // 关闭目录
		return null;
	}


	/**
	 * 修改文档
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static String updateDocument(String rootdir,Object entity) throws Exception {
		
	    String pkName = ClassUtils.getEntityInfoByClass(entity.getClass()).getPkName();
        Object pkValue_o = ClassUtils.getPKValue(entity);
        
        if(pkValue_o==null){
            return null;
        }
        
        String pkValue=pkValue_o.toString();
		
		// 索引写入配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        // 获取索引目录文件
        Directory directory = getDirectory( rootdir,entity.getClass());
        if (directory == null) {
            return null;
        }
        
        Term term = new Term(pkName,pkValue);
     
        Document doc=bean2Document(entity);
                
        
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriter.updateDocument(term, doc);
        indexWriter.commit();
        indexWriter.close(); // 记得关闭,否则删除不会被同步到索引文件中
        directory.close(); // 关闭目录
        
		return null;
	}

	/**
	 * 批量修改文档
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static <T> String updateListDocument(String rootdir,List<T> list) throws Exception {

		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<String> ids = new ArrayList<>();
		Class clazz = list.get(0).getClass();
		for (T t : list) {
			String id = ClassUtils.getPKValue(t).toString();
			ids.add(id);
		}
		deleteListDocument( rootdir,ids, clazz);
		saveListDocument( rootdir,list);
		return null;
	}

	/**
	 * 获取索引的目录
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static File getIndexDirFile(String rootdir,Class clazz) {
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
	@SuppressWarnings("rawtypes")
	public static Directory getDirectory(String rootdir,Class clazz) throws IOException {
		File indexDirFile = getIndexDirFile(rootdir,clazz);
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

	/**
	 * 根据页码和分页大小获取上一次的最后一个ScoreDoc
	 */
	private static ScoreDoc getLastScoreDoc(int pageIndex, int pageSize,Query query, IndexSearcher searcher) throws IOException {
		if (pageIndex <= 1)
			return null;// 如果是第一页就返回空
		int num = pageSize * (pageIndex - 1);// 获取上一页的数量
		TopDocs tds = searcher.search(query, num);
		return tds.scoreDocs[num - 1];
	}

}