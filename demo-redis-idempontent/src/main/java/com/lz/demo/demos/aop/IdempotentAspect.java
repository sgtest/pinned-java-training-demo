package com.lz.demo.demos.aop;

import com.lz.demo.demos.exception.IdempotentException;
import com.lz.demo.demos.service.TokenService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhi
 * @create 2024-02-01
 **/
@Component
@Aspect
public class IdempotentAspect {

    @Autowired
    TokenService tokenService;

    @Pointcut(value = "@annotation(com.lz.demo.demos.anno.Idempotent)")
    public void pcl(){
    }

    @Before("pcl()")
    public void before() throws IdempotentException {
        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        tokenService.checkToken(request);
    }
}
