package com.lz.demo.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhi
 * @create 2024-04-15
 **/
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    //重写 additionalAuthenticationChecks 方法，添加验证码校验
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //获取当前 request 请求
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前请求的验证码 code
        String code = req.getParameter("code");
        //获取之前生成在 Session 中的验证码 verify_code
        String verify_code = (String) req.getSession().getAttribute("verify_code");
        //验证码比较，如果有误则抛异常，如果无误则进行账号密码校验
        if (code == null || verify_code == null || !code.equals(verify_code)) {
            throw new AuthenticationServiceException("验证码错误");
        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
