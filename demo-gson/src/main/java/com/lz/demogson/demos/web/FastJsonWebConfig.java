//package com.lz.demogson.demos.web;
//
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.nio.charset.Charset;
//
///**
// * @author lizhi
// * @create 2023-10-13
// **/
//@Configuration
//public class FastJsonWebConfig {
//
//    @Bean
//    FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig=fastJsonHttpMessageConverter.getFastJsonConfig();
//        fastJsonConfig.setCharset(Charset.defaultCharset());
//        fastJsonConfig.setDateFormat("yyyy-MM-dd");
////        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        fastJsonHttpMessageConverter.setDefaultCharset(Charset.defaultCharset());
//        return fastJsonHttpMessageConverter;
//    }
//}
