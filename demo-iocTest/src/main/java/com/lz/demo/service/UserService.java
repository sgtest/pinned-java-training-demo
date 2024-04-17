package com.lz.demo.service;

import com.lz.demo.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhi
 * @create 2023-12-04
 **/
@Service
public class UserService {

    public User getUser(){
        User user=new User();
        user.setAge(1);
        user.setName("123213");
        return user;
    }
}
