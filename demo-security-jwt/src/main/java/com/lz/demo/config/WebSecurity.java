package com.lz.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .withUser("stytem").password(passwordEncoder().encode("123")).roles("user");
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
                //登录其他配置为true
                .permitAll()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                //csrf攻击配置 disable为禁用
                .csrf().disable();
    }
}
