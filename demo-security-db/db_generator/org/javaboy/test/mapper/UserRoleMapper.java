package org.javaboy.test.mapper;

import org.javaboy.test.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserRoleMapper{
    List<UserRole> getAllUserRoles();
}