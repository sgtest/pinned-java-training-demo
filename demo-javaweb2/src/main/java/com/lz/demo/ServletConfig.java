package com.lz.demo;

import com.lz.demo.demos.web.MyFilter;
import com.lz.demo.demos.web.MyListener;
import com.lz.demo.demos.web.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author lizhi
 * @create 2023-11-02
 **/
@Configuration
public class ServletConfig {

    @Bean
    FilterRegistrationBean<MyFilter> filterRegistration(){
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<MyFilter>();
        MyFilter filter=new MyFilter();
        bean.setFilter(filter);
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.setOrder(98);
        return bean;
    }

    @Bean
    ServletRegistrationBean<MyServlet> servletServletRegistration(){
        ServletRegistrationBean<MyServlet> bean = new ServletRegistrationBean<>();
        MyServlet myServlet=new MyServlet();
        bean.setServlet(myServlet);
        bean.setOrder(97);
        bean.setUrlMappings(Arrays.asList("/hello"));
        return bean;
    }

    @Bean
    ServletListenerRegistrationBean<MyListener> servletListenerRegistration(){
        ServletListenerRegistrationBean<MyListener> bean = new ServletListenerRegistrationBean<>();
        MyListener myListener=new MyListener();
        bean.setListener(myListener);
        bean.setOrder(90);
        return bean;
    }

}
