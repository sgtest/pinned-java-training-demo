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
//开启方法 访问权限 注解
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    //给用户设置密码加密，Security必须配置密码加密
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //配置登录用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //需要配置用户名、密码以及角色(UserDetailsBuilder)
        auth.inMemoryAuthentication()
                .withUser("root").password(passwordEncoder().encode("123")).roles("admin")
                .and()
                .withUser("stytem").password(passwordEncoder().encode("111")).roles("user");
    }

    //角色继承配置
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy=new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http请求认证
        http.authorizeRequests()
                //  /admin/  路径下所有请求都要有admin角色
                .antMatchers("/admin/**").hasRole("admin")
                //  /user/  路径下所有请求有admin或者user角色之一即可
                .antMatchers("/user/**").hasAnyRole("admin","user")
                //其他任何请求都需要认证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //登录请求路径
                .loginProcessingUrl("/doLogin")
                //登陆页面(前后端分离可以不配)
                .loginPage("/login")
                //登陆表单username字段改为uname(非必须)
                .usernameParameter("uname")
                //登陆表单password字段改为passwd(非必须)
                .passwordParameter("passwd")
                //登录成功跳转页面(前后端分离可以不配),服务端跳转
//                .successForwardUrl("/index")
                //登录成功跳转URL,重定向,会记录访问请求的路径
//                .defaultSuccessUrl("/index")
                //登录成功并返回hanlder(一般用于前后端分离，返回登录成功状态)
                .successHandler(
                    //Lambda写法
                    (req,resp,auth)->{
                        resp.setCharacterEncoding("utf-8");
                        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        Map<String, String> map = new HashMap<>();
                        map.put("status","200");
                        map.put("msg","登录成功");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                    //一般写法
//                    new AuthenticationSuccessHandler() {
//                        @Override
//                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//                        }
//                    }
                )
                //登录失败并返回hanlder(一般用于前后端分离，返回登录失败状态)
                .failureHandler((req, resp, e) -> {
                    resp.setCharacterEncoding("utf-8");
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    Map<String, String> map = new HashMap<>();
                    map.put("status","401");
                    if (e instanceof LockedException){
                        map.put("msg","登录失败，账户被锁定！");
                    } else if (e instanceof BadCredentialsException) {
                        map.put("msg","登录失败，用户或密码输入错误！");
                    } else if (e instanceof DisabledException) {
                        map.put("msg","登录失败，账户被禁用！");
                    } else if (e instanceof AccountExpiredException) {
                        map.put("msg","登录失败，账户过期！");
                    } else if (e instanceof CredentialsExpiredException) {
                        map.put("msg","登录失败，密码过期");
                    } else {
                        map.put("msg","登录失败！");
                    }
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //登录其他配置为true
                .permitAll()
                .and()
                //配置注销
                .logout()
                //配置注销路径或接口
                .logoutUrl("/logout")
                //注销并返回hanlder(一般用于前后端分离，返回注销状态)
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.setCharacterEncoding("utf-8");
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    Map<String, String> map = new HashMap<>();
                    map.put("status","200");
                    map.put("msg","注销成功");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .and()
                //csrf攻击配置 disable为禁用
                .csrf().disable();
    }
}
