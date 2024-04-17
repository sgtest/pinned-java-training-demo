package com.lz.demo.demos;

import com.lz.demo.demos.dto.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhi
 * @create 2023-11-13
 **/
public class UserService {

    public List getUsers(){
        List<User> list=new ArrayList<>();
        for (int i=0;i<=100;i++){
            User user=new User();
            user.setAge(10+i);
            user.setName(Thread.currentThread().getName() +"-->"+i);
            list.add(user);
        }
        return list;
    }
}
