package com.stytem.demo.config;

import com.stytem.demo.bean.Menu;
import com.stytem.demo.bean.Role;
import com.stytem.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 这个类的作用是根据用户传来的请求地址，分析请求需要的角色，并将所需要的角色放在 Collection中
 *
 * @author lizhi
 * @create 2024-03-28
 **/
@Component
public class SecurityRoleFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    // AntPathMatcher 是一个正则匹配工具
    AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * 获取请求URL对应的Role角色
     *
     * @param object the object being secured
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object 是一个FilterInvocation实例，获取请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus = menuService.getAllMenus();
        for (Menu menu : menus) {
            //遍历Menu，并对用Menu中的正则匹配请求url
            if (antPathMatcher.match(menu.getPattern(), requestUrl)) {
                //返回url对应的角色集合
                List<Role> roles = menu.getRoles();
                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    //只返回角色（如:ROLE_admin）代码
                    rolesStr[i] = roles.get(i).getName();
                }
                //org.springframework.security.access 包，返回对应的ConfigAttribute角色集合
                return SecurityConfig.createList(rolesStr);
            }
        }
        // 没有匹配上的，只要登录之后就可以访问，这里“ROLE_LOGIN”只是一个标记，有待进一步处理。
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    //是否支持这种方式
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
