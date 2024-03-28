package org.javaboy.test.controller;

import org.javaboy.test.model.MenuRole;
import org.javaboy.test.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
public class MenuRoleController{

    @Autowired
    MenuRoleService menuRoleService;

    @GetMapping("/menuroles")
    public List<MenuRole> getAllMenuRoles(){
        return menuRoleService.getAllMenuRoles();
    }
}