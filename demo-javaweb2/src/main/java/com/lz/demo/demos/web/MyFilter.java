package com.lz.demo.demos.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author lizhi
 * @create 2023-11-02
 **/
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("myFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
