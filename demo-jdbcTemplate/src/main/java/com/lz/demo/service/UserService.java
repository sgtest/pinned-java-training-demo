package com.lz.demo.service;

import com.lz.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

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
    JdbcTemplate jdbcTemplate;

    public int insert(User user){
        return jdbcTemplate.update("insert into user(username,address,birthday) value(?,?,?)",user.getUsername(),user.getAddress(),user.getBirthday());
    }

    public int insert2(User user){
        GeneratedKeyHolder keyHolder=new GeneratedKeyHolder();

        int i=jdbcTemplate.update((connection)->{
            PreparedStatement ps=connection.prepareStatement("insert into user(username,address,birthday) value(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getAddress());
            ps.setDate(3,new Date(user.getBirthday().getTime()));
            return ps;
        },keyHolder);

        user.setId(keyHolder.getKey().intValue());
        return i;
    }

    public int deleteById(int id){
        return jdbcTemplate.update("delete from user where id=?",id);
    }

    public int update(User user){
        if (user.getId()>0)
        return jdbcTemplate.update("UPDATE user SET username = ? WHERE id = ?",user.getUsername(),user.getId());
        return 0;
    }

    public List<User> getAllUsers(){
        List<User> users=jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                String username=rs.getString("username");
                String address=rs.getString("address");
                java.util.Date birthDay=rs.getDate("birthday");
                int id=rs.getInt("id");

                User user=new User();
                user.setUsername(username);
                user.setAddress(address);
                user.setBirthday(birthDay);
                user.setId(id);
                return user;
            }
        });
       return users;
    }

    public List<User> getAllUsers2(){
       List<User> userList= jdbcTemplate.query("select * from user",new BeanPropertyRowMapper<>(User.class));
       return userList;
    }
}
