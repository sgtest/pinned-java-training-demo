package com.lz.demo.demos.web;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhi
 * @create 2023-11-01
 **/
class UserControllerTest {

    @Autowired
    UserService userService;

    public void hello(){
        System.out.println("userService.hello() = " + userService.hello());
    }
}