package com.stytem.demo.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lizhi
 * @create 2024-04-01
 **/
@Configuration
public class ShiroConfig {

    @Bean
    Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        realm.setUserDefinitions("stytem=123,user \n root=123,admin");
        realm.setRoleDefinitions("admin=read,write \n user=read");
        return realm;
    }

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/doLogin","anon");
        definition.addPathDefinition("/**","authc");
        return definition;
    }
}
