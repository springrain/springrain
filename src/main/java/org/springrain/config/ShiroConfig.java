package org.springrain.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


/**
 * 配置 shiro<br>
 * shiroFilter是入口的filter,springboot已经默认拦截所有请求<br>
 * springboot会把所有的filter列为平级,造成shiro的子拦截器和shiroFilter同级,造成访问异常,所以shiro的子Filter需要手动disable
 * @author caomei
 *
 */
@Configuration("configuration-ShiroConfig")
@Lazy(true)//变量 cacheTimeout会引起依赖注入,所以需要懒加载
public class ShiroConfig {
    
    @Value("${springrain.session.timeout}")
    private Long sessionTimeout=1800000L;
    

    
    
    
    
    @Resource
    private Realm shiroDbRealm;

    @Resource
    private Filter framefwlog;
    @Resource
    private Filter frameperms;
    @Resource
    private Filter frontuser;
    @Resource
    private Filter siteuser;
    @Resource
    private Filter systemuser;
    @Resource
    private Filter keepone;
    @Resource
    private Filter statichtml;
    @Resource
    private Filter firewall;
    @Resource
    private Filter wxmpautologin;
    
    


    
    
    private static final String shiroFilterName="shiroFilter";
    

    /**
     * shiro的主拦截器
     * @return
     */
    
    @Bean(shiroFilterName)
    public ShiroFilterFactoryBean shirFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 设置自定义的过滤器,也就是子拦截器
        shiroFilterFactoryBean.setFilters(getFilters());
        // 设置拦截规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(getFilterChainDefinitionMap());
        // 设置登录地址
        shiroFilterFactoryBean.setLoginUrl("/system/login");
        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/errorpage/unauth");
        
        return shiroFilterFactoryBean;
    }

    
    /**
     * 权限管理器
     * @return
     */
    @Bean("securityManager")
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //数据库认证的实现
        securityManager.setRealm(shiroDbRealm);
        //session 管理器
        securityManager.setSessionManager(sessionManager());
        //缓存管理器
        securityManager.setCacheManager(shiroCacheManager());
        return securityManager;
    }
    
    /**
     * session管理器
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        //URL重写中去掉jsessionId
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //超时时间
        sessionManager.setGlobalSessionTimeout(sessionTimeout);
        //定时检查失效的session,默认true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 删除过期的session,默认true
        sessionManager.setDeleteInvalidSessions(true);
        //相隔多久检查一次session的有效性,使用默认的60分钟
       // sessionManager.setSessionValidationInterval(cacheTimeOut);
        //session存储的实现
        sessionManager.setSessionDAO(shiroSessionDAO());
        //sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
        sessionManager.setSessionIdCookie(shiroSessionIdCookie());
        
        return sessionManager;
    }
    /**
     * session存储的实现
     * @return
     */
    @Bean("shiroSessionDAO")
    public SessionDAO shiroSessionDAO() {
        EnterpriseCacheSessionDAO sessionDAO=new EnterpriseCacheSessionDAO();
        return sessionDAO;
    }
    /**
     * sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID 
     * @return
     */
    @Bean("shiroSessionIdCookie")
    public SimpleCookie shiroSessionIdCookie() {
        SimpleCookie sessionIdCookie=new SimpleCookie();
        //cookie的name,对应的默认是 JSESSIONID
        sessionIdCookie.setName("SHAREJSESSIONID");
        //more secure, protects against XSS attacks
        sessionIdCookie.setHttpOnly(true);
        //jsessionId的path为 / 用于多个系统共享jsessionId
        sessionIdCookie.setPath("/");
        
        return sessionIdCookie;
    }
    
    /**
     * 单机session
     * @return
     */
    @Bean("shiroCacheManager")
    public CacheManager shiroCacheManager() {
        MemoryConstrainedCacheManager cacheManager=new MemoryConstrainedCacheManager();
        return cacheManager;
    }
    
    
    
    
    /**
     * 集群session 
     * @return
     */
    
    /*
     
      //集群模式下,需要redis作为cache,注入redissonClient,单机模式需要注释掉
    @Resource
    private RedissonClient redissonClient;
     
    @Bean("shiroCacheManager")
    public CacheManager shiroCacheManager() {
        ShiroRedisCacheManager cacheManager=new ShiroRedisCacheManager();
        cacheManager.setRedissonClient(redissonClient);
        return cacheManager;
    }
    
    */
    
    

    /**
     * 自定义的拦截器
     * 
     * @return
     */
    private Map<String, Filter> getFilters() {
        Map<String, Filter> filters = new HashMap<>();
        //访问日志记录的过滤器
        filters.put("framefwlog", framefwlog);
        //权限校验的过滤器
        filters.put("frameperms", frameperms);
        //前台用户过滤器
        filters.put("frontuser", frontuser);
        //网站后台用户过滤器
        filters.put("siteuser", siteuser);
        //后台用户过滤器
        filters.put("systemuser", systemuser);
        //踢出上个账户的过滤器
        filters.put("keepone", keepone);
        //静态化过滤器
        filters.put("statichtml", statichtml);
        //防火墙过滤器
        filters.put("firewall", firewall);
        //微信登录验证过滤器
        filters.put("wxmpautologin", wxmpautologin);

        return filters;
    }

    /**
     * url拦截规则,访问地址的过滤规则,从上至下,从左至右的优先级,如果有匹配的规则,就会返回,不会再进行匹配
     * 
     * @return
     */
    private Map<String, String> getFilterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/layui/**", "noSessionCreation");
        filterChainDefinitionMap.put("/js/**", "noSessionCreation");
        filterChainDefinitionMap.put("/u/**", "noSessionCreation");
        filterChainDefinitionMap.put("/css/**", "noSessionCreation");
        filterChainDefinitionMap.put("/images/**", "noSessionCreation");
        filterChainDefinitionMap.put("/img/**", "noSessionCreation");
        filterChainDefinitionMap.put("/favicon.ico", "noSessionCreation");
        filterChainDefinitionMap.put("/upload/**", "noSessionCreation");
        filterChainDefinitionMap.put("/ueditor/**", "user");
        filterChainDefinitionMap.put("/errorpage/*", "noSessionCreation");
        filterChainDefinitionMap.put("/mp/**", "firewall");
        filterChainDefinitionMap.put("/f/pc/**", "firewall");
        filterChainDefinitionMap.put("/f/mp/**", "firewall,wxmpautologin");
        filterChainDefinitionMap.put("/tokenerror", "firewall,user");
        filterChainDefinitionMap.put("/getCaptcha", "firewall");
        filterChainDefinitionMap.put("/login", "firewall");
        filterChainDefinitionMap.put("/index", "firewall,user,keepone");
        filterChainDefinitionMap.put("/logout", "firewall,frontuser");
        filterChainDefinitionMap.put("/s/*/login", "firewall");
        filterChainDefinitionMap.put("/s/*/index", "firewall,siteuser,keepone");
        filterChainDefinitionMap.put("/s/*/logout", "firewall,siteuser");
        filterChainDefinitionMap.put("/s/*/menu/leftMenu", "firewall,siteuser,keepone");
        filterChainDefinitionMap.put("/s/**", "firewall,siteuser,keepone,frameperms");
        filterChainDefinitionMap.put("/system/login", "firewall");
        filterChainDefinitionMap.put("/system/index ", "firewall,systemuser,framefwlog,keepone");
        filterChainDefinitionMap.put("/system/logout", "firewall,systemuser");
        filterChainDefinitionMap.put("/system/menu/leftMenu", "firewall,systemuser,keepone");
        filterChainDefinitionMap.put("/system/**", "firewall,systemuser,keepone,frameperms");
        
        
        //spring boot 的actuator 插件访问,需要进行权限控制
        filterChainDefinitionMap.put("/actuator/**", "firewall,systemuser,keepone");
        
        filterChainDefinitionMap.put("/**", "firewall,user,keepone,frameperms");
        return filterChainDefinitionMap;
    }
    

    
   
    @Bean("lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /*
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        //advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    
        
     */
    
    

    
    /**
     * 更新 shiroFilter的根拦截器,具体实现是 org.apache.shiro.spring.web.ShiroFilterFactoryBean.SpringShiroFilter,使用AbstractShiroFilter类型
     * @param filter
     * @return
     */
    
    @Bean("updateSpringShiroFilter")
    //@DependsOn({shiroFilterName})
    public FilterRegistrationBean<AbstractShiroFilter> updateSpringShiroFilter(AbstractShiroFilter filter) {
        FilterRegistrationBean<AbstractShiroFilter> registration = new FilterRegistrationBean<AbstractShiroFilter>(filter);
        Set<String> urlPatterns = new LinkedHashSet<>();
        urlPatterns.add("/*");
        //urlPatterns.add("/errorpage/*");
       registration.setUrlPatterns(urlPatterns);
      // registration.addInitParameter("targetFilterLifecycle", "true");
       registration.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR);
        return registration;
    }

}
