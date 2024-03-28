package org.javaboy.test.service;

import org.javaboy.test.model.Role;
import org.javaboy.test.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class RoleService{

    @Autowired
    RoleMapper roleMapper;
    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }
}