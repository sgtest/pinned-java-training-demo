package com.lz.demogson.demos.web;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.cglib.core.Converter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author lizhi
 * @create 2023-10-13
 **/
@Configuration
public class FastJsonWebConfig2 implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig=fastJsonHttpMessageConverter.getFastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset());
        fastJsonConfig.setDateFormat("yyyy-MM-dd");
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonHttpMessageConverter.setDefaultCharset(Charset.defaultCharset());
        converters.add(fastJsonHttpMessageConverter);
    }
}
