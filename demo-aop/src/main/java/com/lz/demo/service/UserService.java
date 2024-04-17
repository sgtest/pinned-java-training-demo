package com.lz.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author lizhi
 * @create 2023-11-03
 **/
@Service
public class UserService {


    public String getUserById(String id){
        System.out.println("userId="+id);
        return "user"+id;
    }

    public void deleteUserById(String id){
        System.out.println("delete userId="+id);
    }
}
