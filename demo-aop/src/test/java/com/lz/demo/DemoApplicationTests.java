package com.lz.demo;

import com.lz.demo.service.UserService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        userService.getUserById("122");
    }

}
