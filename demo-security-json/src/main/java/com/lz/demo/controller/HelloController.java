package com.lz.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author lizhi
 * @create 2024-04-07
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Principal principal) {
        return "hello " + principal.getName();
    }
}
