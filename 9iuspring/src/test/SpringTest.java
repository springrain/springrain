package test;

import javax.annotation.Resource;

import org.iu9.frame.util.Finder;
import org.iu9.testdb1.entity.User;
import org.iu9.testdb1.service.IBaseTestdb1Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest  {
	
	@Resource
	private IBaseTestdb1Service baseTestdb1Service;
	
	@Test
	public void testRfyMonthSave() throws Exception{
		Finder finder=new Finder("SELECT * FROM t_user WHERE  id=:id order by id desc ");
		finder.setParam("id", "admin");
		User user = baseTestdb1Service.queryForObject(finder,User.class);
		System.out.println(user.getName());
	}

}
