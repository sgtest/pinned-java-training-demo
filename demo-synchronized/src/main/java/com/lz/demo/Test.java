package com.lz.demo;

import com.lz.demo.demos.UserService;
import com.lz.demo.demos.dto.User;

import java.util.List;

/**
 * @author lizhi
 * @create 2023-11-13
 **/
public class Test {


    public static void main(String[] args) {
        UserService userService=new UserService();

        Thread thread1=new Thread(()->{
            synchronized(userService){
                List<User> list=userService.getUsers();
                System.out.println(list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(list.get(0).toString());
            }
        });


        Thread thread2=new Thread(()->{
            synchronized(userService) {
                List<User> list = userService.getUsers();
                System.out.println(list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(list.get(0).toString());
            }
        });
        thread1.start();
        thread2.start();
    }

}
