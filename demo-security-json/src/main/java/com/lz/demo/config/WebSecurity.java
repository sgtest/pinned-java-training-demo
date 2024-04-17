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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhi
 * @create 2024-03-11
 **/
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {


    @Bean
    JSONAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JSONAuthenticationFilter filter = new JSONAuthenticationFilter();
        filter.setAuthenticationSuccessHandler((req,resp,auth)->{
            resp.setCharacterEncoding("utf-8");
            resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> map = new HashMap<>();
            map.put("status","200");
            map.put("msg","登录成功");
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        });
        filter.setAuthenticationFailureHandler((req,resp,auth)->{
            resp.setCharacterEncoding("utf-8");
            resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> map = new HashMap<>();
            map.put("status","401");
            map.put("msg","登录失败");
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        });
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf().disable();
        http.addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
