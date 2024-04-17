package com.stytem.demo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lizhi
 * @create 2024-04-01
 **/
@Configuration
public class ShiroConfig {

    @Bean
    ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    @Bean
    SecurityManager securityManager(){
        DefaultWebSecurityManager webSecurityManager=new DefaultWebSecurityManager();
        webSecurityManager.setRealm(shiroRealm());
        return webSecurityManager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/doLogin","anon");
        map.put("/**","authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}
