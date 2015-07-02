package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.junit.Test;
import org.springrain.frame.util.LuceneUtils;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.User;

public class LuceneTest {

	//@Test
	public void  testSave() throws Exception{
		for (int i = 0; i < 50; i++) {
			User u=new User();
			u.setId("主键"+i);
			u.setName(i+"我是中国人，我会说中文"+i);
			LuceneUtils.saveDocument(u);
		}
	
	}
	
	
	@Test
	public void  testSaveList() throws Exception{
		List<User> list=new ArrayList<User>();
		for (int i = 0; i < 50; i++) {
			User u=new User();
			u.setId(i+"起来"+i);
			u.setName(i+"不愿做奴隶的人们"+i);
			list.add(u);
		}
		LuceneUtils.saveDocument(list);
	}
	
	
	
	@Test
	public void testSearch() throws Exception{
		Page page=new Page(1);
		page.setPageSize(50);
		List<User> list = LuceneUtils.searchDocument(User.class, page, "起来");
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		for (User u:list) {
			System.out.println(u.getId()+"----"+u.getName());
		}
	}
	
	
	
	
	
}
