package com.lz.demo.demos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhi
 * @create 2023-11-01
 **/
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public void hello(){
        System.out.println("userService.hello() = " + userService.hello());
    }
}
