package com.lz.demo;

import com.lz.demo.demos.web.User;
import com.lz.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        for (int i = 0; i < 5; i++) {
            User user = userService.getUserById("99");
            System.out.println("u:"+user);
        }
    }

    @Test
    void test(){
        User u1 = userService.getUserById2("99", "lisi");
        System.out.println("u1"+u1);
        User u2 = userService.getUserById2("99", "lisi");
        System.out.println("u2"+u2);

    }

}
