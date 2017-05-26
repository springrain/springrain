package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.IntPoint;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.shiro.util.CollectionUtils;
import org.junit.Test;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.LuceneSearchClause;
import org.springrain.frame.util.LuceneUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.entity.User;

import test.dto.LuceneDto;

public class LuceneTest {
	
	//@Test
	public void  testSave() throws Exception{
		String rootdir=GlobalStatic.rootDir+"/lucene/index";
		
		LuceneUtils.deleteDocumentAll(rootdir, LuceneDto.class);
		
		System.out.println(rootdir);
		
		File f=new File(rootdir);
		if(!f.exists()){
			f.mkdirs();
		}
		for (int i = 0; i < 50; i++) {
		    LuceneDto u=new LuceneDto();
			u.setId(SecUtils.getUUID());
			u.setName("我是中国人，我会说中文"+i);
			LuceneUtils.saveDocument(rootdir,u);
		}
	
	}
	
	
	//@Test
	public void  testSaveList() throws Exception{
		String rootdir=GlobalStatic.rootDir+"/lucene/index";
		File f=new File(rootdir);
		if(!f.exists()){
			f.mkdirs();
		}
		List<User> list=new ArrayList<User>();
		for (int i = 0; i < 50; i++) {
			User u=new User();
			u.setId(i+"起来"+i);
			u.setName(i+"我是中国人"+i);
			list.add(u);
		}
		LuceneUtils.saveListDocument(rootdir,list);
	}
	
	
	
	@SuppressWarnings("unchecked")
	//@Test
	public void testSearch() throws Exception{
		String rootdir=GlobalStatic.rootDir+"/lucene/index";
		File f=new File(rootdir);
		if(!f.exists()){
			f.mkdirs();
		}
		Page page=new Page(1);
		page.setPageSize(50);
		List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class, page,"abd人");
		//List<LuceneDto> list = LuceneUtils.searchDocumentByTerm(rootdir,LuceneDto.class, page,"name", "我是中国人，我会说中文49");
		
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		for (LuceneDto u:list) {
			System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
			
		}
	}
	
	
	   @SuppressWarnings("unchecked")
	    //@Test
	    public void testSearchObject() throws Exception{
	        String rootdir=GlobalStatic.rootDir+"/lucene/index";
	        File f=new File(rootdir);
	        if(!f.exists()){
	            f.mkdirs();
	        }
	       
	        //List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class, page,"name","我是中国人，我会说中文");
	        LuceneDto u = LuceneUtils.searchDocumentByTerm(rootdir,LuceneDto.class,"id", "c2767ca7d2144d2a9ffffe934912ee0f");
	    
	            System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
	           
	            
	            u.setName("我是测试修改");
	            
	            LuceneUtils.updateDocument(rootdir, u);
	            
	            u = LuceneUtils.searchDocumentByTerm(rootdir,LuceneDto.class,"id", "c2767ca7d2144d2a9ffffe934912ee0f");
	            
                System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
               
	            
	        
	    }
	   
	   
	  // @Test
	   public void testIntPoint() throws Exception{
	       String rootdir=GlobalStatic.rootDir+"/lucene/index";
	       //IntPoint ip=IntPoint.c
	       
	       Query newRangeQuery = IntPoint.newRangeQuery("int2", 0, 20);
	       List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class,null,newRangeQuery);
	       for (LuceneDto u:list) {
	            System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
	            
	        }
	       
	       
	   }
	   
	  // @Test
       public void testStringTerm() throws Exception{
           String rootdir=GlobalStatic.rootDir+"/lucene/index";
           //IntPoint ip=IntPoint.c
           
           Query newRangeQuery = TermRangeQuery.newStringRange("date", "2017-01-01 00:00:00", "2017-02-01 00:00:00", true, true);
           List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class,null,newRangeQuery);
           for (LuceneDto u:list) {
                System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
                
            }
           
           
       }
	   
	   
	   
       @Test
       public void testStringClause() throws Exception{
           String rootdir=GlobalStatic.rootDir+"/lucene/index";
           //IntPoint ip=IntPoint.c
           
           LuceneSearchClause lsc=new LuceneSearchClause("中国 人");
           lsc.addSearchClause("int2", Integer.class, 20);
           lsc.addSearchClause("int2", Integer.class, 20);
        
           List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class,null,lsc);
           for (LuceneDto u:list) {
                System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
                
            }
           
           
       }
       
       
	   
    
	   
	   
	
	
	
	
	
}
