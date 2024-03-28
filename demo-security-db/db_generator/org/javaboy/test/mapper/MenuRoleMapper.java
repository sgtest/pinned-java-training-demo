package org.javaboy.test.mapper;

import org.javaboy.test.model.MenuRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MenuRoleMapper{
    List<MenuRole> getAllMenuRoles();
}