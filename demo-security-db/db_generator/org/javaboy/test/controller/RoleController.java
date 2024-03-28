package org.javaboy.test.controller;

import org.javaboy.test.model.Role;
import org.javaboy.test.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
public class RoleController{

    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
}