package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springrain.demo.entity.User;
import org.springrain.demo.service.IBaseDemoService;
import org.springrain.frame.util.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest  {
	
	@Resource
	private IBaseDemoService basedemoService;
	
	@Test
	public void testSelectUser() throws Exception{
		Finder finder=new Finder("SELECT * FROM t_user WHERE  id=:id order by id desc ");
		finder.setParam("id", "admin");
		User user = basedemoService.queryForObject(finder,User.class);
		System.out.println(user.getName());
	}

}
