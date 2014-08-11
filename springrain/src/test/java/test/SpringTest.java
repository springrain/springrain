package test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springrain.frame.util.Finder;
import org.springrain.system.entity.User;
import org.springrain.system.service.IBaseSpringrainService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest  {
	
	@Resource
	private IBaseSpringrainService baseSpringrainService;
	
	
	@Test
	public void testSelectUser() throws Exception{
		//Finder finder=new Finder("SELECT * FROM t_user WHERE  id=:userId order by id desc ");
		Finder finder=Finder.getSelectFinder(User.class).append("WHERE  id=:userId order by id desc ");
		
		finder.setParam("userId", "admin");
		User user = baseSpringrainService.queryForObject(finder,User.class);
		System.out.println(user.getName());
	}
	
	
	//@Test
	public void testCallProc() throws Exception{
        Finder finder=new Finder("");
		finder.setProcName("read_all_user");
		List<Map<String, Object>> list = baseSpringrainService.queryForListByProc(finder);
		for (Map m:list) {
			System.out.println(m.get("name"));
		}
	
	}

}
