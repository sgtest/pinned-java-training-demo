package com.stytem.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhi
 * @create 2024-03-29
 **/
@RestController
public class HelloController {


    @GetMapping("/admin/hello")
    public String adminHello(Authentication authentication){
        String userName=authentication.getName();
        return "hello admin "+userName;
    }

    @GetMapping("/user/hello")
    public String userHello(Authentication authentication){
        return "hello user "+authentication.getName();
    }

    @GetMapping("/hello")
    public String hello(Authentication authentication){
        return "hello "+authentication.getName();
    }


}
