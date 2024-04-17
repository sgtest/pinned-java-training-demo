package com.lz.demo.demos.web.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author lizhi
 * @create 2024-03-13
 **/
@Service
public class UserService {

    //角色admin可以访问
    @PreAuthorize("hasRole('admin')")
    public String hello1(){
        return "hello1111111";
    }

    //角色user可以访问(Security默认会在角色前加“ROLE_”)
    @Secured("ROLE_user")
    public String hello2(){
        return "hello22222";
    }

    //角色admin或user可以访问
    @PreAuthorize("hasAnyRole('admin','user')")
    public String hello3(){
        return "hello333333";
    }
}
