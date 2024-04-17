package com.lz.demo.demos.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author lizhi
 * @create 2023-10-27
 **/
@Configuration
public class CorsConfig{

    @Bean
    CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration cft=new CorsConfiguration();
        cft.addAllowedHeader("*");
        cft.addAllowedMethod("*");
        cft.addAllowedOrigin("http://localhost:8082");
        source.registerCorsConfiguration("/**",cft);
        return new CorsFilter(source);
    }
}
