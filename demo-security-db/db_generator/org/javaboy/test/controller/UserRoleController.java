package org.javaboy.test.controller;

import org.javaboy.test.model.UserRole;
import org.javaboy.test.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
public class UserRoleController{

    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/userroles")
    public List<UserRole> getAllUserRoles(){
        return userRoleService.getAllUserRoles();
    }
}