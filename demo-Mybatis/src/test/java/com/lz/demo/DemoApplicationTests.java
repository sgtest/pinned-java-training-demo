package com.lz.demo;

import com.lz.demo.dto.User;
import com.lz.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> list=userMapper.getAllUsers();
        for (User user:list){
            System.out.println(user.toString());
        }
    }

}
