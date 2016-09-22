package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springrain.frame.queue.SendMessage;
import org.springrain.frame.queue.SmsMessageDelegateListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class QueueTest  {
	
	
	@Resource
	 SendMessage sendMessage;
	
	@Resource
	SmsMessageDelegateListener smsMessageDelegateListener;
	
	@Test
	public void sendMessage() throws Exception{
		
		
		sendMessage.sendMessage("springrain", "hello");
		
		Thread.sleep(3000);
		
	
		
		System.out.println("----------------------");
	
	}
	
	
}
