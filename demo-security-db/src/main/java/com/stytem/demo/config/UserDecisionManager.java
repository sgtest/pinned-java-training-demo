package com.stytem.demo.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author lizhi
 * @create 2024-03-28
 **/
@Component
public class UserDecisionManager implements AccessDecisionManager {

    /**
     *
     * @param authentication the caller invoking the method (not null)
     *                       当用户前登录信息
     * @param object the secured object being called
     *               是FilterInvocation实例,和SecurityRoleFilter类中getAttributes方法的object是同一个
     * @param configAttributes the configuration attributes associated with the secured
     *                         是SecurityRoleFilter类中getAttributes返回的对应角色集合
     * object being invoked
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        for (ConfigAttribute attribute : configAttributes) {
            //当请求的菜单无角色要求
            if ("ROLE_LOGIN".equals(attribute.getAttribute())){
                //判断该用户是否登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("该用户未登录！");
                }else {
                    return;
                }
            }

            //用户包含的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            //遍历用户角色
            for (GrantedAuthority authority : authorities) {
                //如果用户拥有的角色包含当前菜单对应的角色之一,则通过
                if (authority.getAuthority().equals(attribute.getAttribute())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("非法请求！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
