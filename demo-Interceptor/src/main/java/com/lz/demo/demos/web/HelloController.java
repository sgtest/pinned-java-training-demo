package com.lz.demo.demos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhi
 * @create 2023-11-01
 **/
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        System.out.println("hello world 00");
        return "hello";
    }

    @GetMapping("hello1")
    public String hello1(){
        System.out.println("hello world 11");
        return "hello1";
    }
}
