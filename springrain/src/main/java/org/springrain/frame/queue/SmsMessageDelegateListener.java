package org.springrain.frame.queue;

import java.io.Serializable;

public class SmsMessageDelegateListener {
	
	public void handleMessage(Serializable message){
        System.out.println(message);
    }

}
