package com.lz.demo;

import com.lz.demo.demos.web.User;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        ArrayList<User> list = new ArrayList<>();
        for (int i=1;i<=10;i++){
            User user = new User();
            user.setId(String.valueOf(i));
            user.setAddress("i:"+i+",stytem1@163.com");
            user.setName("i:"+i+",name:stytem");
            user.setAge(i+20);
            list.add(user);
        }

        mongoTemplate.insertAll(list);
        User user=new User();
        user.setName("1");
        mongoTemplate.remove(user);
    }

}
