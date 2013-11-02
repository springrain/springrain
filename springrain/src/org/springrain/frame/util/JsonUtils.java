package org.springrain.frame.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JsonUtils {
   static 	ObjectMapper mapper = new ObjectMapper();
 
 public static <T> T readValue(String content,Class<T> clazz) {
	 T t=null;
	try {
		t=  mapper.readValue(content, clazz);
	} catch (JsonParseException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return t;
 }
 
 public static Object readValues(String content,Class CollectionType,Class clazz){
	 Object o=null;
	 
	try {
		o=  mapper.readValue(content, getCollectionType(CollectionType, clazz));
	} catch (JsonParseException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return o;
 }
 
 public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
         return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }  
 
}
