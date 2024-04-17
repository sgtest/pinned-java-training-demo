package com.lz.demo.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhi
 * @create 2024-04-10
 **/
@RestController
public class HelloController {


    @GetMapping("/user/hello")
    public String userHello(Authentication authentication){
        return "hello user: "+authentication.getName();
    }

    @GetMapping("/admin/hello")
    public String adminHello(Authentication authentication){
        return "hello admin: "+authentication.getName();
    }

}
