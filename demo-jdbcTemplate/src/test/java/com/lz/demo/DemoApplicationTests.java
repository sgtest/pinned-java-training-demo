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
    void contextLoads() {
        User user=new User();
        user.setAddress("上海");
        user.setBirthday(new Date());
        user.setUsername("java");
        int i=userService.insert(user);
        System.out.println("i="+i);


    }

    @Test
    void addUser2() {
        User user=new User();
        user.setAddress("北京3");
        user.setBirthday(new Date());
        user.setUsername("king3");
        int i=userService.insert2(user);
        System.out.println("i="+i);
        System.out.println("id="+user.getId());
    }

    @Test
    void delete() {
        int i=userService.deleteById(5);
        System.out.println("i="+i);
    }

    @Test
    void update() {
        User user=new User();
        user.setId(4);
        user.setUsername("kkkkk");
        user.setAddress("北京111");
        int i=userService.update(user);
        System.out.println("i="+i);
    }

    @Test
    void query() {
        List<User> users=userService.getAllUsers();
        for (User user:users){
            System.out.println(user);
        }
        System.out.println();
    }

    @Test
    void query2() {
        List<User> users=userService.getAllUsers2();
        for (User user:users){
            System.out.println(user);
        }
        System.out.println();
    }

}
