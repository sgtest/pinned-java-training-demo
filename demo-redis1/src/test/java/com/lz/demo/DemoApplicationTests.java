package com.lz.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lz.demo.demos.web.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setAge(18);
        user.setName("stytem");
        user.setAddress("stytem.cn");

        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("a",user);

        User user1= (User) ops.get("a");
        System.out.println(user1);
    }

    @Test
    void Test1() throws JsonProcessingException {
        User user = new User();
        user.setAge(20);
        user.setName("stytem1");
        user.setAddress("www.stytem.cn");

        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(user);
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("s1",s);

        String s1 = ops.get("s1");
        User user1 = om.readValue(s1, User.class);

        System.out.println(user1);

    }

}
