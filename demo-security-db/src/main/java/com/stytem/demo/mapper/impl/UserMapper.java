package com.stytem.demo.mapper.impl;

import com.stytem.demo.bean.Role;
import com.stytem.demo.bean.User;

import java.util.List;

public interface UserMapper{
    List<User> getAllUsers();

    User loadUserByUsername(String username);

    List<Role> selectRolesById(Integer username);
}