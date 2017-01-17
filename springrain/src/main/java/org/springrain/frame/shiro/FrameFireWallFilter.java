package org.springrain.frame.shiro;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springrain.cms.utils.SiteUtils;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
/**
 * 记录访问日志的过滤器
 * @author caomei
 *
 */


public class FrameFireWallFilter extends AdviceFilter {
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private CacheManager cacheManager;
	
	
	//同一IP防火墙阀值
	private Integer firewallLockCount=GlobalStatic.FRIEWALL_LOCK_COUNT;
	//同一IP阀值时间,单位是 秒
	private Integer firewallLockSecond=GlobalStatic.FRIEWALL_LOCK_SECOND;
	
	//锁定分钟数
	private Integer firewallLockedMinute=GlobalStatic.FRIEWALL_LOCKED_MINUTE;
	
	
	//白名单
	private List<String> whiteList=new ArrayList<String>();
	
	//黑名单
	private List<String> blackList=new ArrayList<String>();
	
	@Override
   protected boolean preHandle(ServletRequest req, ServletResponse res) throws Exception {

		
	    HttpServletRequest request=(HttpServletRequest) req;
	    //获取IP地址
	    String ip=IPUtils.getClientAddress(request);
	    //黑名单IP
	    if(blackList.contains(ip)){
	    	return false;
	    }
	    
	    //次数小于0,认为不限制
	    if(firewallLockCount<0){
	    	//chain.doFilter(req, res);
	    	return chainDoFilter(request, res);
	    	
	    }
	    
	    
	    
	    //白名单IP
	    if(whiteList.contains(ip)){
	    	//chain.doFilter(req, res);
	    	return chainDoFilter(request, res);
	    }
	    
	    
	    Cache cache = cacheManager.getCache(GlobalStatic.springrainfirewallCacheKey);
	    //访问记录
	    String fw=cache.get(ip,String.class);
	    
	    //当前时间
	    Long now=System.currentTimeMillis()/1000;
	    Long _end=now+firewallLockSecond;
	    if(fw==null){//第一次访问
	    	cache.put(ip, 1+"_"+_end+"_0");
	    	//chain.doFilter(req, res);
	    	return chainDoFilter(request, res);
	    }
	    String[] strs=fw.split("_");
	    if(strs==null||strs.length!=3){
	    	cache.put(ip, 1+"_"+_end+"_0");
	    	//chain.doFilter(req, res);
	    	return chainDoFilter(request, res);
	    }
	    //已请求次数
	    Integer _count=Integer.valueOf(strs[0]);
	    //阀值判断时间
	    Long endDateLong=Long.valueOf(strs[1]);
	    //是否在锁定期
	    Integer active=Integer.valueOf(strs[2]);
	    _count=_count+1;
	    if(_count<=firewallLockCount){//不到阀值
	    	cache.put(ip, _count+"_"+endDateLong+"_"+active);
	    	//chain.doFilter(req, res);
	    	return chainDoFilter(request, res);
	    	
	    }
	    
	  //访问超过阀值
	    
	    if(active==0){//未进入锁定
	    	endDateLong=endDateLong+firewallLockedMinute*60;
	    	active=1;
	    }
	    
	    
	    if(now>endDateLong){//已经过期
	    	cache.put(ip, 1+"_"+_end+"_0");
	    	//chain.doFilter(req, res);
	    	return chainDoFilter(request, res);
	    }
	    
	    
	    cache.put(ip, _count+"_"+endDateLong+"_"+active);
    	return false;
	

	    }
	
	
	

	/**
	 * 统一处理进入下一个过滤器
	 * @param chain
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
   private boolean chainDoFilter(HttpServletRequest request,ServletResponse res) throws IOException, ServletException{
	   
	  
	   String requestURI = request.getRequestURI();
	   
	   
	   
	   
	   
	   int s_index=requestURI.indexOf("/s_");
	   if(s_index<0){
		   return true;
	   }
	   String siteId=requestURI.substring(s_index+1, requestURI.indexOf("/", s_index+1));
	   
	   //重新编码siteId,避免注入
	   siteId=URLEncoder.encode(siteId,"UTF-8");
	   
	   //避免注入
	   if(StringUtils.isBlank(siteId)||siteId.length()>30){
		   return true;
	   }
	   
	   Map<String,String> map=new HashMap<String,String>();
	   
	   map.put("siteId", siteId);
	   int h_index=requestURI.indexOf("/h_");
	   if(h_index<0){
		   //把siteInfo放入ThreadLocal
		   SiteUtils.setSiteInfo(map);
		   return true;
	   }
	   String channelId=requestURI.substring(h_index+1, requestURI.indexOf("/", h_index+1));
	   //重新编码siteId,避免注入
	   channelId=URLEncoder.encode(channelId,"UTF-8");
	   //避免注入
	   if(StringUtils.isBlank(channelId)||channelId.length()>30){
		   //把siteInfo放入ThreadLocal
		   SiteUtils.setSiteInfo(map);
		   return true;
	   }
	   
	   map.put("channelId", channelId);
	   
	   //把siteInfo放入ThreadLocal
	   SiteUtils.setSiteInfo(map);
	   return true;
	   
   }
   
   
   @Override
   public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
	   SiteUtils.removeSiteInfo();
   }



	public Integer getFirewallLockCount() {
		return firewallLockCount;
	}



	public void setFirewallLockCount(Integer firewallLockCount) {
		this.firewallLockCount = firewallLockCount;
	}



	public Integer getFirewallLockSecond() {
		return firewallLockSecond;
	}



	public void setFirewallLockSecond(Integer firewallLockSecond) {
		this.firewallLockSecond = firewallLockSecond;
	}



	public List<String> getWhiteList() {
		return whiteList;
	}



	public void setWhiteList(List<String> whiteList) {
		this.whiteList = whiteList;
	}



	public List<String> getBlackList() {
		return blackList;
	}



	public void setBlackList(List<String> blackList) {
		this.blackList = blackList;
	}



	public Integer getFirewallLockedMinute() {
		return firewallLockedMinute;
	}



	public void setFirewallLockedMinute(Integer firewallLockedMinute) {
		this.firewallLockedMinute = firewallLockedMinute;
	}

	
}
