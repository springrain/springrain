package org.springrain.system.shiro.freemarkertag;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.SimpleHash;

/**
 * Shortcut for injecting the tags into Freemarker
 *
 * <p>Usage: cfg.setSharedVeriable("shiro", new ShiroTags());</p>
 */
@Component("shiroTags")
public class ShiroTags extends SimpleHash {
    
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public ShiroTags() {
        put("authenticated", new AuthenticatedTag());
        put("guest", new GuestTag());
        put("hasAnyRoles", new HasAnyRolesTag());
        put("hasPermission", new HasPermissionTag());
        put("hasRole", new HasRoleTag());
        put("lacksPermission", new LacksPermissionTag());
        put("lacksRole", new LacksRoleTag());
        put("notAuthenticated", new NotAuthenticatedTag());
        put("principal", new PrincipalTag());
        put("user", new UserTag());
    }
	
	@PostConstruct
    public void registerFreeMarkerVariable() {
	    freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", this);
    }
	
	
}