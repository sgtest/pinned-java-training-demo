package com.lz.demo;

import com.lz.demo.demos.web.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void hello(){
        System.out.println("userService.hello() = " + userService.hello());
    }

}
