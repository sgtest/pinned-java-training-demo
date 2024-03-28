package org.javaboy.test.mapper;

import org.javaboy.test.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper{
    List<User> getAllUsers();
}