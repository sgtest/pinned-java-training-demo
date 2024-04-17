package com.lz.demo.service;

import com.lz.demo.demos.web.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author lizhi
 * @create 2024-03-01
 **/
@Service
public class UserService {

    @Cacheable(cacheNames = "cache-demo")
    //@Cacheable标记在方法上，表示方法的结果需要缓存，每次请求都会查询是否有该缓存，如果有则直接返回，如果没有则执行该方法
    //cacheNames = "cache-demo" 表示缓存名，每个@Cacheable注解需要添加缓存名
    public User getUserById(String id){
        System.out.println("getUserById 已执行 id："+id);
        User user = new User();
        user.setId(id);
        user.setAge(18);
        user.setName("zhangsan");
        return user;
    }

    @Cacheable(cacheNames = "cache-demo")
    //默认情况下，当方法有多个参数时，多个参数都将作为缓存的key
    public User getUserById2(String id,String name){
        System.out.println("getUserById2 已执行 id："+id);
        User user = new User();
        user.setId(id);
        user.setAge(18);
        user.setName(name);
        return user;
    }


    @CacheEvict(cacheNames = "cache-demo")
    //删除缓存
    public void deleteUser(String id){
        System.out.println("deleteUser By Id:"+id);
    }

    @CachePut(cacheNames = "cache-demo",key = "#user.id")
    //更新缓存，如果缓存不存在，则新增缓存，如果存在则更新
    public User updateUser(User user){
        return user;
    }


    @Cacheable(cacheNames = "cache-demo",key = "#id")
    //使用某个参数作为缓存的key : #参数
    //@Cacheable(cacheNames = "cache-demo",key = "#root.method.name") 使用方法名作为key
    public User getUserById3(String id,String name){
        System.out.println("getUserById3 已执行 id："+id);
        User user = new User();
        user.setId(id);
        user.setAge(18);
        user.setName(name);
        return user;
    }

}
