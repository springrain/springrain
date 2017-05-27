package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.junit.Test;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.LuceneFinder;
import org.springrain.frame.util.LuceneUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.entity.User;

import test.dto.LuceneDto;

public class LuceneTest {
	
	//@Test
	public void  testSave() throws Exception{
		String rootdir=GlobalStatic.rootDir+"/lucene/index";
		
		LuceneUtils.deleteAllDocument(rootdir, LuceneDto.class);
		
		System.out.println(rootdir);
		
		File f=new File(rootdir);
		if(!f.exists()){
			f.mkdirs();
		}
		for (int i = 0; i < 50; i++) {
		    LuceneDto u=new LuceneDto();
			u.setId(SecUtils.getUUID());
			u.setName("我是中国人，我会说中文"+i);
			u.setInt1(u.getInt1()+i);
			u.setInt2(u.getInt2()+i);
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
		Page page=new Page(3);
		page.setPageSize(5);
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
	   // @Test
	    public void testSearchObject() throws Exception{
	        String rootdir=GlobalStatic.rootDir+"/lucene/index";
	        File f=new File(rootdir);
	        if(!f.exists()){
	            f.mkdirs();
	        }
	       
	        //List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class, page,"name","我是中国人，我会说中文");
	        LuceneDto u = LuceneUtils.searchDocumentById(rootdir,LuceneDto.class, "c069190562334a17b5fa256937788356");
	    
	            System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
	           
	            
	            u.setName("我是测试修改");
	            
	            LuceneUtils.updateDocument(rootdir, u);
	            
	            u = LuceneUtils.searchDocumentById(rootdir,LuceneDto.class, "c069190562334a17b5fa256937788356");
	            
                System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
               
	            
	        
	    }
	   
	   
	 
	   
	   
	   
       @Test
       public void testStringClause() throws Exception{
           String rootdir=GlobalStatic.rootDir+"/lucene/index";
           //IntPoint ip=IntPoint.c
           
           LuceneFinder lsc=new LuceneFinder("中国 人");
           //lsc.addSearchClause("int2", Integer.class, 20,22);
           //lsc.addSearchClause("int1", Integer.class, 10,15);
           
           lsc.addSortField("int1", Integer.class, true);
        
           List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class,null,lsc);
           for (LuceneDto u:list) {
                System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
                
            }
           
           
       }
       
       
	   
    
	   
	   
	
	
	
	
	
}
