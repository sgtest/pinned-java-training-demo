package com.stytem.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Token 认证管理器
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    //SecurityConfig 中注入
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    //SecurityConfig 中注入
    UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //设置使用密码模式，获取 token 的 clientID
                .withClient("password")
                //获取 token的 grant_type
                .authorizedGrantTypes("password","refresh_token")
                //token 过期时间 1800 秒
                .accessTokenValiditySeconds(1800)
                .resourceIds("rid")
                //获取 token 的 scope
                .scopes("all")
                //获取 token 的 secret 密码
                .secret(passwordEncoder().encode("123"));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //token 存储方式 redisFactory 方式存储
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                //认证管理 Manager
                .authenticationManager(authenticationManager)
                //用户角色 Service 配置
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //security 认证总是开启 token 认证
        security.allowFormAuthenticationForClients();
    }
}
