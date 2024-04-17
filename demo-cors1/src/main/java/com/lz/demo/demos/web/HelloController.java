package com.lz.demo.demos.web;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhi
 * @create 2023-10-19
 **/
@RestController
//@CrossOrigin(value = "http://localhost:8082")
public class HelloController {


    @GetMapping("/hello1")
    public String hello1(){
        return "get-hello-cors1";
    }
}
