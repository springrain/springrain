package org.springrain.frame.util;
/**
 * 全局的静态变量,用于全局变量的存放
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.frame.util.GlobalStatic
 */
public class GlobalStatic {
	public static  String rootdir=null;
	public static  String webinfodir=null;
	public static final String tempRootpath = System.getProperty("user.dir") + "/temp/";
	public static final int excelPageSize=1000;
	public static final  String suffix=".html";
	public static final String excelext=".xls";
	public static final String exportexcel="exportexcel";//是否是导出操作的key
	public static final String dataUpdate="更新";
	public static final String dataSave="保存";
	public static final String dataDelete="删除";
	public static final String cacheKey="springraincache";
	public static final String qxCacheKey="springrainqxcache";
	public static final String staticHtmlCacheKey="statichtmlcache";
	public static final String springrainloginCacheKey="springrainlogincache";
	//缓存用户最后有效的登陆sessionId
		public static final String springrainkeeponeCacheKey="springrainkeeponecache";
	
	
	public static final String defaultCharset="UTF-8";
	
	public static final String tableExt="_history_";
	public static final String frameTableAlias="frameTableAlias";
	public static final String pageurlName="pageurlName";
	public static final String returnDatas="returnDatas";
	

	//认证
	//public static final String reloginsession="shiro-reloginsession";
	//认证
	public static final String authenticationCacheName="shiro-authenticationCacheName";
	//授权
	public static final String authorizationCacheName="shiro-authorizationCacheName";
	//realm名称
	public static final String authorizingRealmName="shiroDbAuthorizingRealmName";
	
	
	/**
	 * 默认验证码参数名称
	 */
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	
	static{
		String path= GlobalStatic.class.getClassLoader().getResource("").getPath();
		path = path.replace("\\", "/");
		int _info=path.indexOf("/WEB-INF/classes");
		if(_info>0){
			path=path.substring(0, _info);
		}
		webinfodir=path+"/WEB-INF";
		rootdir=path;
		
	}
	
	
	

}
