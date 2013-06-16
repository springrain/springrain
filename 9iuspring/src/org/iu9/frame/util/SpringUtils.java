
package org.iu9.frame.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
* Spring 工具类
*
* @author 9iuorg@gmail.com  9iu.org
* @date 2011-10-13
*/

@Component("springUtils")
public class SpringUtils  implements ApplicationContextAware {
	
	
	private static ApplicationContext context;

	/**
	 * 
	 */
	public SpringUtils() {
		
	}
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context=context;
	}
	/**
	 * 根据beanName 获取 spring bean
	 * @param beanName
	 * @return Object
	 */
	public static  Object getBean(String beanName){
		if(beanName==null)return null;
	    return 	context.getBean(beanName);
	}
	

}
