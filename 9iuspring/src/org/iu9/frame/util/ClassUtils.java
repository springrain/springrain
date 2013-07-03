
/**
 * 
 */
package org.iu9.frame.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.iu9.frame.annotation.PKSequence;
import org.iu9.frame.annotation.TableGroup;
import org.iu9.frame.annotation.WhereSQL;


/**
* 处理类的工具类. 例如反射
*
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.frame.util.ClassUtils
*/

public class ClassUtils {
	
	
	//缓存 entity的字段信息
	public static Map<String,EntityInfo> staticEntitymap=new  ConcurrentHashMap<String,EntityInfo>();
	//缓存 所有的WhereSql注解
	public static Map<String, List<WhereSQLInfo>> staticWhereSQLmap=new  ConcurrentHashMap<String, List<WhereSQLInfo>>();
	//缓存 所有的字段
	public static Map<String, Set<String>> allFieldmap=new  ConcurrentHashMap<String, Set<String>>();
	//缓存 所有的数据库字段
	public static Map<String, List<String>> allDBFieldmap=new  ConcurrentHashMap<String, List<String>>();

	
	/**
	 * 添加一个EntityInfo 信息,用于缓存.
	 * @param info
	 * @return
	 */
	public static  Map<String,EntityInfo> addEntityInfo(EntityInfo info){
		if(info==null||info.getClassName()==null){
			 return null;
		}
		staticEntitymap.put(info.getClassName(), info);
		return staticEntitymap;
	}
/**
 * 根据ClassName获取 EntityInfo
 * @param className
 * @return
 * @throws Exception
 */
	public static EntityInfo getEntityInfoByClass(Class clazz) throws Exception{
		if(clazz==null)
			return null;
		String className=clazz.getName();
		if(className==null)
			return null;
		boolean iskey=staticEntitymap.containsKey(className);
		if(iskey){
			return staticEntitymap.get(className);
		}

		
		 if((clazz.isAnnotationPresent(Table.class)==false)){
			 return null;
		 }
		
		String tableName = ClassUtils.getTableName(clazz);
		if(tableName==null)
			return null;
		EntityInfo info=new EntityInfo();
		info.setTableName(tableName);
		info.setClassName(clazz.getName());
		List<String> fields = ClassUtils.getAllDBFields(clazz);
    	if(fields==null)
		return null;
    	 for(String fdName:fields){
 			boolean ispk= isAnnotation(clazz,fdName,Id.class);
 			if(ispk==true){
 				info.setPkName(fdName);
 			  boolean isSequence=	 isAnnotation(clazz,fdName,PKSequence.class);
 			  if(isSequence){
 					PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);
 					Method getMethod = pd.getReadMethod();// 获得get方法
 					PKSequence sequenceAnnotation = getMethod.getAnnotation(PKSequence.class);
 					info.setPksequence(sequenceAnnotation.name());
 			  }
 				break;
 			}
 		 }
    	
    	
   	 if(clazz.isAnnotationPresent(TableGroup.class)){
   		info.setGroup(true);
	 }
    	
    	staticEntitymap.put(className,info);
		return staticEntitymap.get(className);
	}
	/**
	 * 根据对象获取Entity信息,主要是为了获取分表的信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static EntityInfo getEntityInfoByEntity(Object o) throws Exception{
		if(o==null)
			return null;
		Class clazz=o.getClass();
		EntityInfo info=getEntityInfoByClass(clazz);
		if(info==null){
			return null;
		}
		String tableExt=getTableExt(o);
		info.setTableExt(tableExt);
		return info;
	}

	
	
	/**
	 * 根据ClassName获取wheresql 注解的类信息
	 * @param className
	 * @return
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	public static List<WhereSQLInfo> getWhereSQLInfo(Class clazz) throws  Exception{
		if(clazz==null)
			return null;
		String className=clazz.getName();
		if(StringUtils.isBlank(className)){
			return null;
		}
		
		boolean iskey=staticWhereSQLmap.containsKey(className);
		if(iskey){
			return staticWhereSQLmap.get(className);
		}
		Set<String> names = getAllFieldNames(clazz);
		if(CollectionUtils.isEmpty(names))
			return null;
		
		 List<WhereSQLInfo>  wheresql=new ArrayList<WhereSQLInfo> ();
		for(String name:names){
			boolean isWhereSQL= isAnnotation(clazz,name,WhereSQL.class);
			if(isWhereSQL==false){
				continue;
			}
			
			PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			
			WhereSQL ws= (WhereSQL) getMethod.getAnnotation(WhereSQL.class);
			WhereSQLInfo info=new WhereSQLInfo();
			info.setName(name);
			info.setWheresql(ws.sql());
			wheresql.add(info);
		}
		
		return wheresql;
		
	}
	
	
	
	

	/**
	 * 获取一个类的所有属性名称,包括继承的父类
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Set<String> getAllFieldNames(Class clazz) throws Exception{
		if(clazz==null){
			return null;
		}
		String className=clazz.getName();
		boolean iskey=allFieldmap.containsKey(className);
		if(iskey){
		 return  allFieldmap.get(className);
		}
		Set<String>	allSet=new HashSet<String>();
		allSet=	recursionFiled(clazz,allSet);
		allFieldmap.put(className, allSet);
		return allSet;
	}
	
	
	
	
	/**
	 * 获取所有数据库的类字段对应的属性
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllDBFields(Class clazz) throws Exception{
		
		if(clazz==null){
			return null;
		}
		String className=clazz.getName();
		boolean iskey=allDBFieldmap.containsKey(className);
		if(iskey){
			return allDBFieldmap.get(className);
		}
		
		Set<String> allNames = getAllFieldNames(clazz);
     if(CollectionUtils.isEmpty(allNames))
    	 return null;
    
     List<String>   dbList=new ArrayList<String>();
	 for(String fdName:allNames){
		boolean isDB= isAnnotation(clazz,fdName,Transient.class);
		if(isDB==false){
			dbList.add(fdName);
		}
	 }
	 allDBFieldmap.put(className, dbList);
		return dbList;
	}
	
	
	
	
	
	
	/**
	 * clazz 属性 fd 的 getReadMethod() 是否包含 注解 annotationName
	 * @param clazz
	 * @param fdName
	 * @param annotationClass
	 * @return
	 * @throws Exception
	 */
	public static boolean isAnnotation(Class clazz,String fdName,Class annotationClass) throws Exception{
		
		if(clazz==null||fdName==null||annotationClass==null)
			return false;
		PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);
		Method getMethod = pd.getReadMethod();// 获得get方法
		return getMethod.isAnnotationPresent(annotationClass);
		
	}
	
	/**
	 * 获取 Class 的@Table注解 name 属性,没有属性则返回 类名
	 * @param clazz
	 * @return
	 */
	public static String  getTableName(Class clazz){
		
		if(clazz==null)
			return null;
		
		 if((clazz.isAnnotationPresent(Table.class)==false))
			 return clazz.getSimpleName();
		 
		Table table= (Table) clazz.getAnnotation(Table.class);
		
		String tableName=table.name();
		if(tableName==null)
			return clazz.getSimpleName();
			return tableName;
		
	}
	
	/**
	 * 获取数据库分表的后缀
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String getTableExt(Object o) throws Exception{
		Class clazz=o.getClass();
		if(clazz.isAnnotationPresent(TableGroup.class)==false)
			return "";
		
		TableGroup group =	(TableGroup)clazz.getAnnotation(TableGroup.class);
		String p=group.name();
		String  tableExt= (String) getPropertieValue(p, o);
		return tableExt;
		
	}
	
	/**
	 * 获取数据库分表的属性名称 例如 ext 实际是调用 getExt获取后缀
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String getGroupName(Object o) throws Exception{
		Class clazz=o.getClass();
		if(clazz.isAnnotationPresent(TableGroup.class)==false){
			return "";
		}
		TableGroup group =	(TableGroup)clazz.getAnnotation(TableGroup.class);
		String p=group.name();
		return p;
		
	}
	
	/**
	 * 递归查询父类的所有属性,set 去掉重复的属性
	 * @param clazz
	 * @param fdNameSet
	 * @return
	 * @throws Exception
	 */
	private static  Set<String> recursionFiled(Class clazz,Set<String> fdNameSet) throws Exception {
		Field[] fds = clazz.getDeclaredFields();
		for (int i = 0; i < fds.length; i++) {
			Field fd = fds[i];
			fdNameSet.add(fd.getName());
		}
		Class superClass = clazz.getSuperclass();
		if (superClass != Object.class) {
			recursionFiled(superClass,fdNameSet);
		}
		return fdNameSet;
	}
	
	
	/**
	 * 获得主键的值
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object getPKValue(Object o) throws Exception{
		Class clazz=o.getClass();
		 Set<String> allNames=getAllFieldNames(clazz);
	     if(CollectionUtils.isEmpty(allNames))
	    	 return null;
	     
	     String id="id";
	    
		 List<String> list=new ArrayList<String>();	
	     
		 for(String fdName:allNames){
			boolean ispk= isAnnotation(clazz,fdName,Id.class);
			if(ispk){
				id=fdName;
				break;
			}
		 }
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
	 * 是否是java的基本类型
	 * @param clazz
	 * @return
	 */
	public static  boolean isBaseType(Class clazz){
		if(clazz==null){
			return false;
		}
		String className=clazz.getName().toLowerCase().trim();
		if(className.startsWith("java.")){
			return true;
		}else{
			return false;
		}
	}
	
	
}
