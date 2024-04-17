package com.lz.demo.service;

import com.lz.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.List;

/**
 * @author lizhi
 * @create 2023-11-07
 **/
@Service
public class UserService {

    @Autowired
    @Qualifier("jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;

    @Resource(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;


    public List<User> getAllUsers01(){
       List<User> userList= jdbcTemplateOne.query("select * from user",new BeanPropertyRowMapper<>(User.class));
       return userList;
    }

    public List<User> getAllUsers02(){
       List<User> userList= jdbcTemplateTwo.query("select * from user",new BeanPropertyRowMapper<>(User.class));
       return userList;
    }
}
