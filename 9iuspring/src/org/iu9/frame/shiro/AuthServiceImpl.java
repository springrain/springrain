package org.iu9.frame.shiro;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.iu9.frame.common.BaseLogger;
import org.iu9.frame.util.Finder;
import org.iu9.frame.util.OrderedProperties;
import org.iu9.testdb1.entity.Menu;
import org.iu9.testdb1.entity.Role;
import org.iu9.testdb1.service.IUserRoleMenuService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
/**
*
shiro所支持的广义权限字串表达式，共有三种：
1、简单方式
比如：subject.isPermitted("editNews")，表示判断某操作者是否有【编辑新闻】的权限。
2、细粒度方式
比如：subject.isPermitted("News:create")，表示判断某操作者是否有【新建新闻】的权限。
3、实例级访问方式
比如：subject.isPermitted("News:edit:10")，表示判断某操作者是否有【编辑id号是10新闻】的权限。
上面3种方式中，可以用*表示所有，例如："News:*"为对所有新闻的操作，"*:create"对所有事务都可以新增。还可以用 逗号 表示或都，"News:edit:10,11"表示可对10，11号新闻进行编辑。
如果要写页面权限，可参照如下配置：
/index.jsp = anon
/admin/** = authc, roles[admin]
/docs/** = authc, perms[document:read]
/** = authc
* 
* */
@Component("authService")
public class AuthServiceImpl extends BaseLogger implements
		IAuthService {
	// 注意/r/n前不能有空格
	private static final String CRLF = "\r\n";
	private static final String LAST_AUTH_STR = "/** =authc\r\n";


	@Resource
	private ShiroFilterFactoryBean shiroFilterFactoryBean;

	@Resource
	IUserRoleMenuService userRoleMenuService;

	@Override
	public String loadFilterChainDefinitions() {

		StringBuffer sb = new StringBuffer("");
		//固定的url权限
	  String fixedAuthRule=	getFixedAuthRule();
	  if(StringUtils.isNotBlank(fixedAuthRule)){
		sb.append(fixedAuthRule);
	  }
	  String dynaAuthRule = getDynaAuthRule();
	  if(StringUtils.isNotBlank(dynaAuthRule)){
		sb.append(getDynaAuthRule());
	  }
		//sb.append(getRestfulOperationAuthRule());
		//sb.append(LAST_AUTH_STR);

		return sb.toString();
	}

	// 生成restful风格功能权限规则

	private String getRestfulOperationAuthRule() {
		Finder finder = new Finder("");
		List<Menu> menus = null;
		try {
			menus = userRoleMenuService.queryForList(finder, Menu.class);
		} catch (Exception e) {
			logger.error(e);

		}

		Set<String> restfulUrls = new HashSet<String>();
		for (Menu m : menus) {
			restfulUrls.add(m.getPageurl());
		}
		StringBuffer sb = new StringBuffer("");
		for (Iterator<String> urls = restfulUrls.iterator(); urls.hasNext();) {
			String url = urls.next();
			if (!url.startsWith("/")) {
				url = "/" + url;
			}
			sb.append(url).append("=").append("authc, rest[").append(url)
					.append("]").append(CRLF);
		}
		return sb.toString();

	}

	// 根据角色，得到动态权限规则
	private String getDynaAuthRule() {

		StringBuffer sb = new StringBuffer("");
		Map<String, Set<String>> rules = new HashMap<String, Set<String>>();
		List<Role> roles = null;
		try {
			roles = userRoleMenuService.findAllRoleAndMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}
		for (Role r : roles) {
			List<Menu> menus = r.getMenus();
			if (CollectionUtils.isEmpty(menus)) {
				continue;
			}
			for (Menu m : menus) {
				String url = m.getPageurl();
				if (!url.startsWith("/")) {
					url = "/" + url;
				}
				if (!rules.containsKey(url)) {
					rules.put(url, new HashSet<String>());
				}
				rules.get(url).add((r.getCode()));
			}

		}

		for (Map.Entry<String, Set<String>> entry : rules.entrySet()) {
			sb.append(entry.getKey()).append(" = ")
					.append("authc,roleOrFilter").append(entry.getValue())
					.append(CRLF);
		}

		return sb.toString();
	}

	// 得到固定权限验证规则串
	private String getFixedAuthRule() {

		StringBuffer sb = new StringBuffer("");

		ClassPathResource cp = new ClassPathResource(
				"fixed_shiro_auth.properties"); 
		//对资源文件的内容排序
		Properties properties = new OrderedProperties();
		try {
			properties.load(cp.getInputStream());
		} catch (IOException e) {
			logger.error("fixed_shiro_auth.properties error!", e);
			throw new RuntimeException(
					"load fixed_shiro_auth.properties error!");
		}
		for (Iterator its = properties.keySet().iterator(); its.hasNext();) {
			String key = (String) its.next();
			sb.append(key).append(" = ")
					.append(properties.getProperty(key).trim()).append(CRLF);

		}
		return sb.toString();

	}

	@Override
	// 此方法加同步锁
	public synchronized void reCreateFilterChains() {

		AbstractShiroFilter shiroFilter = null;
		try {
			shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
					.getObject();
		} catch (Exception e) {
			logger.error("getShiroFilter from shiroFilterFactoryBean error!", e);
			throw new RuntimeException(
					"get ShiroFilter from shiroFilterFactoryBean error!");
		}

		PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver();
		DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
				.getFilterChainManager();

		// 清空老的权限控制
		manager.getFilterChains().clear();

		shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
		shiroFilterFactoryBean
				.setFilterChainDefinitions(loadFilterChainDefinitions());
		// 重新构建生成
		Map<String, String> chains = shiroFilterFactoryBean
				.getFilterChainDefinitionMap();
		for (Map.Entry<String, String> entry : chains.entrySet()) {
			String url = entry.getKey();
			String chainDefinition = entry.getValue().trim().replace(" ", "");
			manager.createChain(url, chainDefinition);
		}

	}

}
