package com.lz.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author lizhi
 * @create 2023-11-03
 **/
@Aspect
@Component
public class LogAspect {

    //执行com.lz.demo.service包下所有类的所有方法
    @Pointcut("execution(* com.lz.demo.service.*.*(..))")
    public void pcl(){

    }

    //方法执行前
    @Before("pcl()")
    public void before(JoinPoint jp){
        String name=jp.getSignature().getName();
        System.out.println(name+" 方法开始执行了");
    }

    //方法执行后
    @After("pcl()")
    public void after(JoinPoint jp){
        String name=jp.getSignature().getName();
        System.out.println(name+" 方法结束了");
    }

    //方法执行返回return时
    @AfterReturning(value = "pcl()" ,returning = "s")
    public void afterReturn(JoinPoint jp,String s){
        String name=jp.getSignature().getName();
        System.out.println(name+" 方法返回值："+s);
    }

    @AfterThrowing(value = "pcl()",throwing = "e")
    public void throwing(JoinPoint jp,Exception e){
        String name=jp.getSignature().getName();
        System.out.println(name+" 方法 抛出了异常");
    }

    @Around("pcl()")
    public Object around(ProceedingJoinPoint pjp){
        try {
            String name=pjp.getSignature().getName();
            System.out.println("环绕方法"+name+"开始");
            //类似反射中的 invoke方法
            Object proceed=pjp.proceed();
            System.out.println("环绕方法"+name+"结束");

            if (proceed instanceof String){
                System.out.println("环绕方法"+name+"返回值"+proceed);
            }
            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
