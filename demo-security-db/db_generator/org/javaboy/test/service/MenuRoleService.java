package org.javaboy.test.service;

import org.javaboy.test.model.MenuRole;
import org.javaboy.test.mapper.MenuRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class MenuRoleService{

    @Autowired
    MenuRoleMapper menuRoleMapper;
    public List<MenuRole> getAllMenuRoles(){
        return menuRoleMapper.getAllMenuRoles();
    }
}