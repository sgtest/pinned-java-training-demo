package com.lz.demo.controller;

import com.lz.demo.dto.User;
import com.lz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhi
 * @create 2023-12-04
 **/
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public User getUser(){
        User user = userService.getUser();
        return user;
    }
}
