package com.stytem.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;


/**
 * @author lizhi
 * @create 2024-04-01
 **/
@RestController
public class HelloController {

    @GetMapping("/login")
    public String login(){
        return "doLogin....";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username,String password){
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            System.out.println("success");
        }catch (Exception e){
            e.printStackTrace();
            return "login failed";
        }
        return "login success "+ ((String) subject.getPrincipal());
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello shiro";
    }
}
