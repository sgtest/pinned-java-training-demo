package org.javaboy.test.service;

import org.javaboy.test.model.Menu;
import org.javaboy.test.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class MenuService{

    @Autowired
    MenuMapper menuMapper;
    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }
}