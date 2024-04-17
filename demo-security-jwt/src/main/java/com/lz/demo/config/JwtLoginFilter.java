package com.lz.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lz.demo.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 用户登录的过滤器
 * <p>
 * 自定义 JwtLoginFilter 继承自 AbstractAuthenticationProcessingFilter，并实现其中的三个默认方法。
 * attemptAuthentication 方法中，我们从登录参数中提取出用户名密码，然后调用 AuthenticationManager.authenticate() 方法去进行自动校验。
 * 第二步如果校验成功，就会来到 successfulAuthentication 回调中，在 successfulAuthentication 方法中，将用户角色遍历然后用一个 , 连接起来，然后再利用 Jwts 去生成 token，按照代码的顺序，生成过程一共配置了四个参数，分别是用户角色、主题、过期时间以及加密算法和密钥，然后将生成的 token 写出到客户端。
 * 第二步如果校验失败就会来到 unsuccessfulAuthentication 方法中，在这个方法中返回一个错误提示给客户端即可。
 **/
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    //构造方法
    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        User user = null;
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            //如果传送方式是json数据格式
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } else {
            //如果是form表单形式
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user = new User();
            user.setPassword(password);
            user.setUsername(username);
        }

        //获取授权Manager管理器
        AuthenticationManager authenticationManager = getAuthenticationManager();
        //获取授权Authentication实例，UsernamePasswordAuthenticationToken为通过账号密码授权Token
        //校验账号密码，校验成功则跳到successfulAuthentication，校验失败则跳到unsuccessfulAuthentication
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return authentication;
    }

    //登录成功处理
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //获取用户角色集合
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        //组装集合权限
        StringBuffer sf = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            sf.append(authority.getAuthority()).append(",");
        }
        //JWT生成Token
        String Jwt = Jwts.builder()
                .claim("authorities", sf)//配置用户角色
                .setSubject(authResult.getName())//配置 Subject，赋值用户名
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))//设置 token 过期时间 30 分钟
                //对用户信息加密
                //第一个参数使用 HS512 方式加密，第二参数为 BASE64 私钥 key
                .signWith(SignatureAlgorithm.HS512, "stytem@123")
                .compact();
        //响应返回token字符串
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(Jwt));
        out.flush();
        out.close();
    }

    //登录失败处理
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, String> map = new HashMap<>();
        map.put("msg", "登录失败！");
        out.write(new ObjectMapper().writer().writeValueAsString(map));
        out.flush();
        out.close();
    }
}
