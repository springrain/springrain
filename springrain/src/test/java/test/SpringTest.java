package test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.User;
import org.springrain.system.service.IMenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest  {
	
	@Resource
	private IMenuService menuService;
	
	
	//@Test
	public void updateUser() throws Exception{
		
		User u=new User();
		u.setId(null);
		u.setName("test123");
		//menuService.update(u);
		//testSelectUser();
	
	}
	
	@Test
	public void testSelectUser() throws Exception{
		//Finder finder=new Finder("SELECT * FROM t_user WHERE  id=:userId order by id desc ");
		Finder finder=Finder.getSelectFinder(User.class).append("WHERE  id=:userId ");
		
		finder.setParam("userId", "admin");
		Page page=new Page(1);
		page.setOrder("name dsf");
		page.setSort("sdfsd");
		 List<User> list = menuService.queryForList(finder,User.class,page);
		System.out.println(list);
	}
	
	
	//@Test
	public void testCallProc() throws Exception{
        Finder finder=new Finder("");
		finder.setProcName("read_all_user");
		List<Map<String, Object>> list = menuService.queryForListByProc(finder);
		for (Map m:list) {
			System.out.println(m.get("name"));
		}
	
	}

}
