package org.javaboy.test.service;

import org.javaboy.test.model.User;
import org.javaboy.test.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserService{

    @Autowired
    UserMapper userMapper;
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }
}