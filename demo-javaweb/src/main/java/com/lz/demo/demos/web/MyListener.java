package com.lz.demo.demos.web;

import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;

/**
 * @author lizhi
 * @create 2023-11-02
 **/
@WebListener
public class MyListener extends RequestContextListener {

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        System.out.println("init RequestListener");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        System.out.println("destroy RequestListener");
    }
}
