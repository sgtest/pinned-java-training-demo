package com.lz.demo.demos.web;

import com.lz.demo.demos.anno.Idempotent;
import com.lz.demo.demos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhi
 * @create 2024-01-31
 **/
@RestController
public class IdemController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/create")
    public String createToken(){
       return tokenService.createToken();
    }

    @PostMapping("/hello1")
    @Idempotent
    public String hello1(){
        return "hello1";
    }

    @PostMapping("/hello2")
    public String hello2(){
        return "hello2";
    }
}
