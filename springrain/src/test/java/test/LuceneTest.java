package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.junit.Test;
import org.springrain.frame.util.GlobalStatic;
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
			u.setName(i+"我是中国人，我会说中文"+i);
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
	@Test
	public void testSearch() throws Exception{
		String rootdir=GlobalStatic.rootDir+"/lucene/index";
		File f=new File(rootdir);
		if(!f.exists()){
			f.mkdirs();
		}
		Page page=new Page(1);
		page.setPageSize(50);
		List<LuceneDto> list = LuceneUtils.searchDocument(rootdir,LuceneDto.class, page, "5be77b50c51840898199adee5e3a4f7b");
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		for (LuceneDto u:list) {
			System.out.println(u.getId()+","+u.getName()+","+u.getD1()+","+u.getF1()+","+u.getInt1()+","+u.getD2()+","+u.getF2()+","+u.getInt2()+","+u.getDate());
			
		}
	}
	
	
	
	
	
}
