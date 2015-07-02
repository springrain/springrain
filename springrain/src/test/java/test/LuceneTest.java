package test;

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
	public void testSearch() throws Exception{
		Page page=new Page(1);
		page.setPageSize(50);
		List<User> list = LuceneUtils.search(User.class, page, "主键 放的地方官方的发给");
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		for (User u:list) {
			System.out.println(u.getId()+"----"+u.getName());
		}
	}
	
	
	
	
	
}
