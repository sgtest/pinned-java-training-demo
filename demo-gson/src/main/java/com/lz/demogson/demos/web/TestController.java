package com.lz.demogson.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lizhi
 * @create 2023-10-13
 **/
@RestController
public class TestController {

    @RequestMapping("/user1")
    public List<User> getList(){
        List<User> list=new ArrayList<>();
        for (int i=0;i<=2;i++){
            User user=new User();
            user.setName("user李"+i);
            user.setAge(20+i);
            user.setBirthDate(new Date());
            list.add(user);
        }
        return list;
    }
}
