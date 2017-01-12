package org.springrain.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springrain.frame.util.JsonUtils;

import org.springrain.ueditor.define.ActionMap;

/**
 * 配置管理器
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager {

	private final String rootPath;
	private final String originalPath;
	private static final String configFileName = "config.json";
	private String parentPath = null;
	private Map<String,Object> jsonConfig = null;
	// 涂鸦上传filename定义
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private final static String REMOTE_FILE_NAME = "remote";
	
	/*
	 * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
	 */
	private ConfigManager ( String rootPath, String contextPath, String uri ) throws FileNotFoundException, IOException {
		
		rootPath = rootPath.replace( "\\", "/" );
		this.rootPath = rootPath;
		if ( contextPath.length() > 0 ) {
			this.originalPath = this.rootPath + uri.substring( contextPath.length() );
		} else {
			this.originalPath = this.rootPath + uri;
		}
		
		this.initEnv();
		
	}
	
	/**
	 * 配置管理器构造工厂
	 * @param rootPath 服务器根路径
	 * @param contextPath 服务器所在项目路径
	 * @param uri 当前访问的uri
	 * @return 配置管理器实例或者null
	 */
	public static ConfigManager getInstance ( String rootPath, String contextPath, String uri ) {
		
		try {
			return new ConfigManager(rootPath, contextPath, uri);
		} catch ( Exception e ) {
			return null;
		}
		
	}
	
	// 验证配置文件加载是否正确
	public boolean valid () {
		return jsonConfig != null;
	}
	
	public Map<String,Object> getAllConfig () {
		
		return jsonConfig;
		
	}
	
	public Map<String, Object> getConfig ( int type ) {
		
		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;
		
		switch ( type ) {
		
			case ActionMap.UPLOAD_FILE:
				conf.put( "isBase64", "false" );
				//conf.put( "maxSize", jsonConfig.getLong( "fileMaxSize" ) );
				conf.put( "maxSize", Long.valueOf(jsonConfig.get("fileMaxSize").toString()) );
				
				conf.put( "allowFiles", getArray( "fileAllowFiles" ) );
				
				//conf.put( "fieldName", jsonConfig.getString( "fileFieldName" ) );
				//savePath = jsonConfig.getString( "filePathFormat" );
				
				conf.put( "fieldName", jsonConfig.get( "fileFieldName" ).toString() );
				savePath = jsonConfig.get( "filePathFormat" ).toString();
				
				
				
				break;
				
			case ActionMap.UPLOAD_IMAGE:
				conf.put( "isBase64", "false" );
				
				//conf.put( "maxSize", jsonConfig.getLong( "imageMaxSize" ) );
				
				conf.put( "maxSize", Long.valueOf(jsonConfig.get("imageMaxSize").toString())  );
				conf.put( "allowFiles", getArray( "imageAllowFiles" ) );
				conf.put( "fieldName", jsonConfig.get( "imageFieldName" ).toString() );
				savePath = jsonConfig.get( "imagePathFormat" ).toString();
				break;
				
			case ActionMap.UPLOAD_VIDEO:
				//conf.put( "maxSize", jsonConfig.getLong( "videoMaxSize" ) );
				
				conf.put( "maxSize",  Long.valueOf(jsonConfig.get("videoMaxSize").toString())  );
				conf.put( "allowFiles", getArray( "videoAllowFiles" ) );
				
				conf.put( "fieldName", jsonConfig.get( "videoFieldName" ).toString() );
				savePath = jsonConfig.get( "videoPathFormat" ).toString();
				break;
				
			case ActionMap.UPLOAD_SCRAWL:
				conf.put( "filename", ConfigManager.SCRAWL_FILE_NAME );
				//conf.put( "maxSize", jsonConfig.getLong( "scrawlMaxSize" ) );
				
				conf.put( "maxSize", Long.valueOf(jsonConfig.get("scrawlMaxSize").toString()));
				conf.put( "fieldName", jsonConfig.get( "scrawlFieldName" ).toString() );
				conf.put( "isBase64", "true" );
				savePath = jsonConfig.get( "scrawlPathFormat" ).toString();
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf.put( "filename", ConfigManager.REMOTE_FILE_NAME );
				conf.put( "filter",getArray( "catcherLocalDomain" ) );
				//conf.put( "maxSize", jsonConfig.getLong( "catcherMaxSize" ) );
				
				conf.put( "maxSize",  Long.valueOf(jsonConfig.get("catcherMaxSize").toString()) );
				
				
				
				conf.put( "allowFiles", getArray( "catcherAllowFiles" ) );
				conf.put( "fieldName", jsonConfig.get( "catcherFieldName" ).toString() + "[]" );
				savePath = jsonConfig.get( "catcherPathFormat" ).toString();
				break;
				
			case ActionMap.LIST_IMAGE:
				conf.put( "allowFiles", getArray( "imageManagerAllowFiles" ) );
				conf.put( "dir", jsonConfig.get( "imageManagerListPath" ).toString() );
				//conf.put( "count", jsonConfig.getInt( "imageManagerListSize" ) );
				
				
				conf.put( "count", Integer.valueOf(jsonConfig.get("imageManagerListSize").toString())  );
				
				break;
				
			case ActionMap.LIST_FILE:
				conf.put( "allowFiles", getArray( "fileManagerAllowFiles" ) );
				conf.put( "dir", jsonConfig.get( "fileManagerListPath" ).toString() );
				//conf.put( "count", jsonConfig.getInt( "fileManagerListSize" ) );
				
				conf.put( "count", Integer.valueOf(jsonConfig.get("fileManagerListSize").toString())  );
				
				
				break;
				
		}
		
		conf.put( "savePath", savePath );
		conf.put( "rootPath", rootPath );
		
		return conf;
		
	}
	
	private void initEnv () throws FileNotFoundException, IOException {
		
		File file = new File( originalPath );
		
		if ( !file.isAbsolute() ) {
			file = new File( file.getAbsolutePath() );
		}
		
		parentPath = file.getParent();
		
		String configContent = readFile( getConfigPath() );
		
		try{
			jsonConfig = JsonUtils.readValue(configContent, HashMap.class);
		} catch ( Exception e ) {
			jsonConfig = null;
		}
		
	}
	
	private String getConfigPath () {
		return parentPath + File.separator + ConfigManager.configFileName;
	}

	private String[] getArray ( String key ) {
		
		
		String content=jsonConfig.get(key).toString();
		 List<String> readValues = (List<String>) JsonUtils.readValues(content, ArrayList.class, String.class);
		 String[] array = (String[]) readValues.toArray();
		
		return array;
		
	}
	
	private String readFile ( String path ) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		
		try {
			
			InputStreamReader reader = new InputStreamReader( new FileInputStream( path ), "UTF-8" );
			BufferedReader bfReader = new BufferedReader( reader );
			
			String tmpContent = null;
			
			while ( ( tmpContent = bfReader.readLine() ) != null ) {
				builder.append( tmpContent );
			}
			
			bfReader.close();
			
		} catch ( UnsupportedEncodingException e ) {
			// 忽略
		}
		
		return filter( builder.toString() );
		
	}
	
	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter ( String input ) {
		
		return input.replaceAll( "/\\*[\\s\\S]*?\\*/", "" );
		
	}
	
}
