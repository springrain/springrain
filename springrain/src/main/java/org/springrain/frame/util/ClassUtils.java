
/**
 * 
 */
package org.springrain.frame.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.annotation.LuceneField;
import org.springrain.frame.annotation.LuceneSearch;
import org.springrain.frame.annotation.NotLog;
import org.springrain.frame.annotation.PKSequence;
import org.springrain.frame.annotation.TableSuffix;
import org.springrain.frame.annotation.WhereSQL;


/**
* 处理类的工具类. 例如反射
*
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.frame.util.ClassUtils
*/

public class ClassUtils {
	
	private static final  Logger logger = LoggerFactory.getLogger(ClassUtils.class);
	//缓存 entity的字段信息
	private static Map<String,EntityInfo> staticEntitymap=new  ConcurrentHashMap<>();
	
	
	
	
	
	private ClassUtils(){
		throw new IllegalAccessError("工具类不能实例化");
	}

	
	
/**
 * 根据ClassName获取 EntityInfo
 * @param className
 * @return
 * @throws Exception
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static EntityInfo getEntityInfoByClass(Class clazz) throws Exception{
		if(clazz==null){
		    return null;
		}
		String className=clazz.getName();
		
		boolean iskey=staticEntitymap.containsKey(className);
		if(iskey){
			return staticEntitymap.get(className);
		}

		 EntityInfo info=new EntityInfo();
		 
		 if((clazz.isAnnotationPresent(Table.class))){
		    info.setTableAnnotation(true);
		 }
	      
	     if(clazz.isAnnotationPresent(TableSuffix.class)){
	        info.setSharding(true);
	     }
	     if(clazz.isAnnotationPresent(NotLog.class)){
	            info.setNotLog(true);
	     }
	     
	     if(clazz.isAnnotationPresent(LuceneSearch.class)){
	         info.setLuceneSearchAnnotation(true);
	     }
	     
		String tableName = ClassUtils.getTableNameByClass(clazz);
		info.setTableName(tableName);
		info.setClassName(clazz.getName());
		
		List<FieldInfo> fields=new ArrayList<>();
		info.setFields(fields);
		recursionFiled(clazz,info,fields);
 	 
     	staticEntitymap.put(className,info);
     	
        return info;
	}
	/**
	 * 根据对象获取Entity信息,主要是为了获取分表的信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static EntityInfo getEntityInfoByEntity(Object o) throws Exception{
		if(o==null)
			return null;
		Class clazz=o.getClass();
		EntityInfo info=getEntityInfoByClass(clazz);
		if(info==null){
			return null;
		}
		String tableExt=getTableExt(o);
		info.setTableSuffix(tableExt);
		return info;
	}

	
	
	/**
	 * clazz 属性 fd 的 getReadMethod() 是否包含 注解 annotationName
	 * @param clazz
	 * @param fdName
	 * @param annotationClass
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isAnnotation(Class clazz,String fdName,Class annotationClass) throws Exception{
		
		if(clazz==null||fdName==null||annotationClass==null){
			return false;
		}
		
		PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);
		Method getMethod = pd.getReadMethod();// 获得get方法
		return getMethod.isAnnotationPresent(annotationClass);
		
	}
	
	/**
	 * 获取 Class 的@Table注解 name 属性,没有属性则返回 类名
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static String  getTableName(Object object) throws Exception{
		
		if(object==null){
			return null;
		}
			
	   String tableName=null;
	   
		if(object instanceof Class){
		EntityInfo entityInfo = getEntityInfoByClass((Class)object);
		tableName=entityInfo.getTableName();
		}else{
			EntityInfo entityInfoByEntity = ClassUtils.getEntityInfoByEntity(object);
			
			if(entityInfoByEntity==null){
				return null;
			}
			
			
			 tableName = entityInfoByEntity.getTableName();
			String tableExt = entityInfoByEntity.getTableSuffix();
			if (StringUtils.isNotBlank(tableExt)) {
				tableName = tableName + tableExt;
			}
		}
		

		if(tableName==null){
			return null;
		}
		
			return tableName;
		
	}
	
	
	/**
	 * 获取 Class 的@Table注解 name 属性,没有属性则返回 类名
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String  getTableNameByClass(Class clazz){
		
		if(clazz==null){
			return null;
		}
		
		 if((clazz.isAnnotationPresent(Table.class)==false)){
			 return null;
		 }
			 
		 
		Table table= (Table) clazz.getAnnotation(Table.class);
		
		String tableName=table.name();
		if(tableName==null){
			return null;
		}
			
			return tableName;
		
	}
	
	
	
	/**
	 * 获取数据库分表的后缀
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getTableExt(Object o) throws Exception{
		Class clazz=o.getClass();
		if(clazz.isAnnotationPresent(TableSuffix.class)==false)
			return "";
		
		TableSuffix suffix = (TableSuffix)clazz.getAnnotation(TableSuffix.class);
		String p=suffix.name();
		String  tableExt= (String) getPropertieValue(p, o);
		return tableExt;
		
	}
	
	
	
	/**
	 * 递归查询父类的所有属性,set 去掉重复的属性
	 * @param clazz
	 * @param fdNameSet
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private static  List<FieldInfo> recursionFiled(Class clazz,EntityInfo info,List<FieldInfo> fields) throws Exception {
		Field[] fds = clazz.getDeclaredFields();
		
		for (int i = 0; i < fds.length; i++) {
		    FieldInfo finfo=new FieldInfo();
			Field fd = fds[i];
			String fieldName=fd.getName();
			finfo.setFieldName(fieldName);//字段名称
			finfo.setFieldType(fd.getType());//字段类型
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
	        Method getMethod = pd.getReadMethod();// 获得get方法
			
	        if(getMethod.isAnnotationPresent(Id.class)){//如果是主键
                finfo.setPk(true);
                info.setPkName(fieldName);
                info.setPkReturnType(getMethod.getReturnType());
            }
	        
	        if(getMethod.isAnnotationPresent(LuceneField.class)){//如果有LuceneField注解
	            finfo.setLucene(true);
	        }
	        if(getMethod.isAnnotationPresent(Transient.class)){//如果不是数据库字段
                finfo.setDb(false);
            }
	        if(getMethod.isAnnotationPresent(WhereSQL.class)){//如果有WhereSQL注解
	            WhereSQL ws= (WhereSQL) getMethod.getAnnotation(WhereSQL.class);
	            finfo.setWhereSQL(ws.sql());
            }
	        if(getMethod.isAnnotationPresent(PKSequence.class)){//如果有PKSequence注解
	            PKSequence pkSequence= (PKSequence) getMethod.getAnnotation(PKSequence.class);
                finfo.setPkSequence(pkSequence.name());
                info.setPksequence(pkSequence.name());
            }
			
			fields.add(finfo);
		}
		Class superClass = clazz.getSuperclass();
		if (superClass != Object.class) {
			recursionFiled(superClass,info,fields);
		}
		return fields;
	}
	
	
	/**
	 * 获得主键的值
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object getPKValue(Object o) throws Exception{
		Class clazz=o.getClass();
	    String id=getEntityInfoByClass(clazz).getPkName();
		return getPropertieValue(id,o) ;
			
	}
	
	/**
	 * 获取一个实体类的属性值
	 * @param p
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Object getPropertieValue(String p,Object o) throws Exception{
		Object _obj=null;
		for(Class<?> clazz = o.getClass(); clazz != Object.class;  clazz = clazz.getSuperclass()) {
		
			 PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
				Method getMethod = pd.getReadMethod();// 获得get方法
				if(getMethod!=null){
					_obj= getMethod.invoke(o);
					break;
				}
			
			
		}
		
		return _obj;
		
	}
	/**
	 * 设置实体类的属性值
	 * @param p
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Object setPropertieValue(String p,Object o,Object value) throws Exception{
		Object _obj=null;
		for(Class<?> clazz = o.getClass(); clazz != Object.class;  clazz = clazz.getSuperclass()) {
			 PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
				Method setMethod = pd.getWriteMethod();// 获得set方法
				if(setMethod!=null){
					setMethod.invoke(o, value);  
					break;
				}
			
		}
		
		return _obj;
		
	}
	
	/**
	 * 获取字段的返回类型
	 * @param p
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Class getReturnType(String p,Class _clazz) throws Exception{
		
		Class  returnType=null;
		for(Class<?> clazz = _clazz; clazz != Object.class;  clazz = clazz.getSuperclass()) {
			 PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
				Method getMethod = pd.getReadMethod();// 获得get方法
				if(getMethod!=null){
					returnType= getMethod.getReturnType();
					break;
				}
			
		}
		
		return returnType;
	}
	
	/**
	 * 是否是java的基本类型
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static  boolean isBaseType(Class clazz){
		if(clazz==null){
			return false;
		}
		String className=clazz.getName().toLowerCase();
		if(className.startsWith("java.")){
			return true;
		}else{
			return false;
		}
	}
	

	
	private static Set<String> getAllFieldNames(Class clazz) throws Exception{
	    EntityInfo entityInfoByClass = getEntityInfoByClass(clazz);
	    if(entityInfoByClass==null){
	        return null;
	    }
	    
	    List<FieldInfo> fields = entityInfoByClass.getFields();
	    if(CollectionUtils.isEmpty(fields)){
	        return null;
	    }
	    
	    Set<String> set=new HashSet<>();
	    
	    for(FieldInfo finfo:fields){
	        set.add(finfo.getFieldName());
	    }
	    
	    
	    
        return set;
	}
	
	/**
	 * 获取所有的数据库字段
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	   public static List<String> getAllDBFields(Class clazz) throws Exception{
	       EntityInfo entityInfoByClass = getEntityInfoByClass(clazz);
	        if(entityInfoByClass==null){
	            return null;
	        }
	        
	        List<FieldInfo> fields = entityInfoByClass.getFields();
	        if(CollectionUtils.isEmpty(fields)){
	            return null;
	        }
	        
	        List<String> list=new ArrayList<>();
	        
	        for(FieldInfo finfo:fields){
	            if(finfo.getDb()){
	                list.add(finfo.getFieldName());
	            }
	           
	        }
	        
	        
	        
	        return list;
	       
	   }
	
	   
	    /**
	     * 获取所有的数据库字段
	     * @param clazz
	     * @return
	     * @throws Exception
	     */
	       public static List<FieldInfo> getLuceneFields(Class clazz) throws Exception{
	           EntityInfo entityInfoByClass = getEntityInfoByClass(clazz);
	            if(entityInfoByClass==null){
	                return null;
	            }
	            
	            List<FieldInfo> fields = entityInfoByClass.getFields();
	            if(CollectionUtils.isEmpty(fields)){
	                return null;
	            }
	            
	            List<FieldInfo> list=new ArrayList<>();
	            
	            for(FieldInfo finfo:fields){
	                if(finfo.getLucene()){
	                    list.add(finfo);
	                }
	               
	            }
	            
	            
	            
	            return list;
	           
	       }
	
	
	/**
	 * 复制
	 * @param bean
	 * @param properties
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	  public static void populate(final Object bean, final Map<String, ? extends Object> properties)
		        throws Exception {
		  
	        // Do nothing unless both arguments have been specified
	        if ((bean == null) || (properties == null)) {
	            return;
	        }
	        if (logger.isDebugEnabled()) {
	        	logger.debug("ClassUtils.populate(" + bean + ", " +
	                    properties + ")");
	        }
	        
	       
	        
	        Set<String> allFieldNames = getAllFieldNames(bean.getClass());

	        // Loop through the property name/value pairs to be set
	        for(final Map.Entry<String, ? extends Object> entry : properties.entrySet()) {
	            // Identify the property name and value(s) to be assigned
	            final String name = entry.getKey();
	            if (name == null) {
	                continue;
	            }
	            String _name=name;
	            if(!allFieldNames.contains(name)){
	            	
	            	if(!_name.contains(GlobalStatic.SQLCutSeparator)){
	            		continue;
	            	}
	            	
	            }
	            
	            
	            
	            
	         
	        	if (_name.contains(GlobalStatic.SQLCutSeparator)) {
	        		
	        		String[] strs = _name.split(GlobalStatic.SQLCutSeparator);
					// 获取最后的属性名称
					_name = strs[strs.length - 1];
					// 循环获取实体对象
					Object o=bean;
					for (int i = 0; i < strs.length - 1; i++) {
						o=getPropertieValue(strs[i], o);
					}
					 setPropertieValue(_name, o, entry.getValue());
	        		
	        	}else{
	        		 // Perform the assignment for this property
		            setPropertieValue(name, bean, entry.getValue());
	        	}

	        }


		    }
	
	
	
	
}
