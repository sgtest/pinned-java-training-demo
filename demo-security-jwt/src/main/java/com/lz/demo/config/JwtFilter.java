package com.lz.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 其他接口请求过滤器
 **/
public class JwtFilter extends GenericFilterBean {

    /**
     * token 处理过滤器，将 token 字符串转化成 UsernamePasswordAuthenticationToken，并交给 Security 处理
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        //获取请求头中的Token
        String jwtToken = req.getHeader("authorization");
        System.out.println(jwtToken);
        //解析token得到Claims实例
        Claims claims = Jwts.parser().setSigningKey("stytem@123").parseClaimsJws(jwtToken.replace("Bearer", ""))
                .getBody();
        //从claims获取当前登录用户名
        String username = claims.getSubject();
        //从claims获取当前登录用户登录权限
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(((String) claims.get("authorities")));
        //创建UsernamePasswordAuthenticationToken放到当前的 Context 中，交给 Security 管理器验证
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(req, response);
    }
}
