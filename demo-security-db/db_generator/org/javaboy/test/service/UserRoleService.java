package org.javaboy.test.service;

import org.javaboy.test.model.UserRole;
import org.javaboy.test.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserRoleService{

    @Autowired
    UserRoleMapper userRoleMapper;
    public List<UserRole> getAllUserRoles(){
        return userRoleMapper.getAllUserRoles();
    }
}