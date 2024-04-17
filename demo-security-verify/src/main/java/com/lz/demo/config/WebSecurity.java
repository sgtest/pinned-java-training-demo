package com.lz.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhi
 * @create 2024-03-11
 **/
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    //给用户设置密码加密，Security必须配置密码加密
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //注入自定义 AuthenticationProvider
    @Bean
    MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider provider = new MyAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    //配置登录用户
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //内存中添加增加user
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("root").password(passwordEncoder().encode("123")).roles("admin").build());
        manager.createUser(User.withUsername("stytem").password(passwordEncoder().encode("123")).roles("user").build());
        return manager;
    }


    //配置authenticationManager
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        ProviderManager manager = new ProviderManager(myAuthenticationProvider());
        return manager;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http请求认证
        http.authorizeRequests()
                //  /admin/  路径下所有请求都要有admin角色
                .antMatchers("/admin/**").hasRole("admin")
                //  /user/  路径下所有请求有admin或者user角色之一即可
                .antMatchers("/user/**").hasAnyRole("admin", "user")
                //vf接口不做拦截
                .antMatchers("/vf").permitAll()
                //其他任何请求都需要认证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //登录请求路径
                .loginProcessingUrl("/doLogin")
                //登陆页面(前后端分离可以不配)
                .loginPage("/login")
                //登录成功并返回hanlder(一般用于前后端分离，返回登录成功状态)
                .successHandler((req, resp, auth) -> {
                    resp.setCharacterEncoding("utf-8");
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    Map<String, String> map = new HashMap<>();
                    map.put("status", "200");
                    map.put("msg", "登录成功");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //登录失败并返回hanlder(一般用于前后端分离，返回登录失败状态)
                .failureHandler((req, resp, e) -> {
                    resp.setCharacterEncoding("utf-8");
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    Map<String, String> map = new HashMap<>();
                    map.put("status", "401");
                    map.put("msg", e.getMessage());
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //登录其他配置为true
                .permitAll()
                .and()
                .csrf().disable();
    }
}
