package com.lz.demo;

import com.lz.demo.dto.User;
import com.lz.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserService userService;


    @Test
    void query() {
        List<User> list01=userService.getAllUsers01();
        for (User user:list01){
            System.out.println(user);
        }
        System.out.println("list01.size="+list01.size());
        System.out.println("----------");

        List<User> list02=userService.getAllUsers02();
        for (User user:list02){
            System.out.println(user);
        }
        System.out.println("list02.size="+list02.size());
    }

}
