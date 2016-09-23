package org.springrain.frame.queue;

import java.io.Serializable;

public class RedisMessageDelegateListener {
	public Integer i=1;
	
	
	//也可以串行处理
	//public synchronized void handleMessage(Serializable message){
	
	public  void handleMessage(Serializable message){
		
        System.out.println(i+":"+message);
        i++;
        System.out.println("000000000000000000");
        
    }

}
