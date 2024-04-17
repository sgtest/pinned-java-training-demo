package com.lz.demo.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhi
 * @create 2023-10-17
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public void hello(){
        int i=1/0;
    }
}
