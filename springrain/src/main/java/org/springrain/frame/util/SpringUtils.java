
package org.springrain.frame.util;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springrain.frame.entity.BaseEntity;

/**
* Spring 工具类
*
* @author 9iuorg@gmail.com  weicms.net
* @date 2011-10-13
*/

@Component("springUtils")
public class SpringUtils  implements ApplicationContextAware {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static ApplicationContext applicationContext;

	public SpringUtils() {
		
	}
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext=context;
		
		try {
				initEntityInfo();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		System.out.println("----------------------started----------------------");
		
	}
	/**
	 * 根据beanName 获取 spring bean
	 * @param beanName
	 * @return Object
	 */
	public static  Object getBean(String beanName){
		if(beanName==null)return null;
	    return 	applicationContext.getBean(beanName);
	}
	/**
	 * 根据bean type 获取springBean
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getBeanByType(Class clazz){
		return applicationContext.getBean(clazz);
	}
	
	/**
	 * 获取 Spring applicationContext
	 * @return
	 */
	public static ApplicationContext getContext() {
		return applicationContext;
	}
	
	
	private void initEntityInfo() throws Exception{

		String basePathName= BaseEntity.class.getName();
		String[] basePaths=basePathName.split("\\.");
		
		String classPath="**/*.class";
		
		if(basePaths==null||basePaths.length<2){
			return;
		}
		
	    String packagePath=basePaths[0]+"/"+basePaths[1]+"/";
	    classPath=packagePath+classPath;
		
		PathMatchingResourcePatternResolver pmrpr=new PathMatchingResourcePatternResolver();
		Resource[] resources = pmrpr.getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+classPath);
		
		
		for (Resource resource:resources) {
			
			URI uri = resource.getURI();
			String entityClassName=uri.toString();
			
			entityClassName=entityClassName.substring(entityClassName.lastIndexOf(packagePath), entityClassName.lastIndexOf(".class"));
			entityClassName=entityClassName.replaceAll("/", ".");
			
			Class<?> clazz=Class.forName(entityClassName);
			
			
			EntityInfo entityInfo = ClassUtils.getEntityInfoByClass(clazz);
			
			if(entityInfo==null){
				continue;
			}
			
			ClassUtils.getAllFieldNames(clazz);
			ClassUtils.getAllDBFields(clazz);
			ClassUtils.getWhereSQLInfo(clazz);
			ClassUtils.isLuceneSearch(clazz);
			ClassUtils.getLuceneFields(clazz);
			
		}
		
		
		
	}
	

}
