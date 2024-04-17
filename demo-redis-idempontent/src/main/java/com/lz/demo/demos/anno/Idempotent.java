package com.lz.demo.demos.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用来实现拦截方法接口
 * @author lizhi
 * @create 2024-01-31
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
    public @interface Idempotent {
}
