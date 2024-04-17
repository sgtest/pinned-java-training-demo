package com.lz.demo.demos.interceptor;

import com.lz.demo.demos.anno.Idempotent;
import com.lz.demo.demos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.logging.Handler;

/**
 * 拦截器，主要拦截@Idempotent注解的方法
 * @author lizhi
 * @create 2024-01-31
 **/
@Component
public class IdemInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //此拦截handler是否是Method,如果是则拦截;如果不是Method方法，则不拦截
        if (handler instanceof HandlerMethod){
            Method method=((HandlerMethod) handler).getMethod();

            if (method.isAnnotationPresent(Idempotent.class)){
                tokenService.checkToken(request);
            };
        }
        return true;
    }
}
