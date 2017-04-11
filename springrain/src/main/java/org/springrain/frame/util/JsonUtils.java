package org.springrain.frame.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils  {
	private JsonUtils(){
		throw new IllegalAccessError("工具类不能实例化");
	}
	
	 private final static 	ObjectMapper mapper = new FrameObjectMapper();
	 public static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	  
	 public static <T> T readValue(String content,Class<T> clazz) {
		 T t=null;
		try {
			t=  mapper.readValue(content, clazz);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(),e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return t;
	 }
	 
	 public static String writeValueAsString(Object o){
		 String str=null;
		try {
			str= mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(),e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return str;
	 }
	 
	 @SuppressWarnings("rawtypes")
	public static Object readValues(String content,Class collectionClass,Class clazz){
		 Object o=null;
		 
		try {
			o=  mapper.readValue(content, getCollectionType(collectionClass, clazz));
		} catch (JsonParseException e) {
			logger.error(e.getMessage(),e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		
		return o;
	 }
	 
	 public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
	         return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	    }  
	 
}
