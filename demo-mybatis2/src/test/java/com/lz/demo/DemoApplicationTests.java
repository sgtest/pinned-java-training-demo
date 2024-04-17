package com.lz.demo;

import com.lz.demo.dto.User;
import com.lz.demo.mapper1.UserMapper1;
import com.lz.demo.mapper2.UserMapper2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserMapper1 userMapper1;

    @Autowired
    UserMapper2 userMapper2;

    @Test
    void contextLoads() {
        List<User> list1=userMapper1.getAllUsers();
        System.out.println("list1.size="+list1.size());
        for (User user:list1){
            System.out.println(user.toString());
        }

        System.out.println("-------------");

        List<User> list2=userMapper2.getAllUsers();
        System.out.println("list2.size="+list2.size());
        for (User user:list2){
            System.out.println(user.toString());
        }
    }

}
