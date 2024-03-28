package org.javaboy.test.mapper;

import org.javaboy.test.model.Role;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RoleMapper{
    List<Role> getAllRoles();
}