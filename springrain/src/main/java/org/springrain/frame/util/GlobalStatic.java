package org.springrain.frame.util;
/**
 * 全局的静态变量,用于全局变量的存放
 * @copyright {@link weicms.net}
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
	
	
	//page对象的缓存后缀key
	public static final String pageCacheExtKey="_springrain_page_key";
	
	//主业务缓存
	public static final String cacheKey="springraincache";
	//权限缓存
	public static final String qxCacheKey="springrainqxcache";
	//页面静态化缓存
	public static final String staticHtmlCacheKey="statichtmlcache";
	//登录次数校验缓存
	public static final String springrainloginCacheKey="springrainlogincache";
	//缓存用户最后有效的登陆sessionId
	public static final String springrainkeeponeCacheKey="springrainkeeponecache";
	//防火墙缓存
    public static final String springrainfirewallCacheKey="springrainfriewallcache";
	//微信缓存
    public static final String springrainweixinCacheKey="springrainweixincache";
    //cms 缓存
    public static final String springraincmsCacheKey="springraincmscache";
    
    
    public static final String tokenKey="springraintoken";
	public static final String defaultCharset="UTF-8";
	
	public static final String tableSuffix="_history_";
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
	
	
	//默认验证码参数名称
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	
	

	//密码连续错误10次,锁定不再进行登录查询,锁定 ERROR_LOGIN_LOCK_MINUTE  分钟
	public static final int ERROR_LOGIN_COUNT = 10;
	//错误登录后的,锁定分钟数
	public static final int ERROR_LOGIN_LOCK_MINUTE = 30;
	
	
	//同一IP防火墙阀值
	public static final Integer FRIEWALL_LOCK_COUNT = 10000;
	//同一IP阀值时间,单位是 秒
	public static final Integer FRIEWALL_LOCK_SECOND = 60;
	//锁定分钟数
	public static final Integer FRIEWALL_LOCKED_MINUTE = 10;
	
	static{
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
		path = path.replace("\\", "/");
		
		
		if(path.startsWith("file:/")){
			path=path.substring(6, path.length());
		}
		
		
		int _info=path.indexOf("/WEB-INF/classes");
		if(_info>0){
			path=path.substring(0, _info);
		}
		
		rootdir=path;
		
	}
	
	
	

}
