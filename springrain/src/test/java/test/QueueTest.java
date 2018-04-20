package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springrain.SpringrainApplication;
import org.springrain.frame.queue.RedisMessageDelegateListener;
import org.springrain.frame.queue.SendMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringrainApplication.class)
public class QueueTest  {
	
	
	@Resource
	 SendMessage sendMessage;
	
	@Resource
	RedisMessageDelegateListener redisMessageDelegateListener;
	
	@Test
	public void sendMessage() throws Exception{
		
		
		sendMessage.sendMessage("springrainqueue", "hello1");
		sendMessage.sendMessage("springrainqueue", "hello2");
		
		Thread.sleep(100000);
		
	
		
		System.out.println("----------------------");
	
	}
	
	
}
