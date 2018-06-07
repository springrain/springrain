package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springrain.SpringrainApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringrainApplication.class)
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	    System.out.println(" hello world test ! ");
	}

}
