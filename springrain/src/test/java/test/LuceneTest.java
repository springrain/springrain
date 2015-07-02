package test;

import org.junit.Test;
import org.springrain.frame.util.LuceneUtils;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.User;

public class LuceneTest {

	//@Test
	public void  testSave() throws Exception{
		for (int i = 0; i < 500; i++) {
			User u=new User();
			u.setId(i+"我是中国人，我会说中文"+i);
			LuceneUtils.saveDocumentByEntity(u);
		}
	
	}
	@Test
	public void testSearch() throws Exception{
		Page page=new Page(1);
		page.setPageSize(500);
		LuceneUtils.searchByEntity(User.class, page, "id", "000我123，文，44");
	}
	
	
	
	
	
}
