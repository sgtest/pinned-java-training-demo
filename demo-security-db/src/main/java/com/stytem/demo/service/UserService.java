package com.stytem.demo.service;

import com.stytem.demo.bean.Role;
import com.stytem.demo.bean.User;
import com.stytem.demo.mapper.impl.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhi
 * @create 2024-03-27
 **/
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.loadUserByUsername(username);
        if (user ==null){
            throw new UsernameNotFoundException("账户不存在！");
        }
        List<Role> roles=userMapper.selectRolesById(user.getId());
        user.setRoles(roles);
        return user;
    }
}
