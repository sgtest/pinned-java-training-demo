package com.stytem.demo.service;

import com.stytem.demo.bean.Menu;
import com.stytem.demo.mapper.impl.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhi
 * @create 2024-03-28
 **/
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;


    @Cacheable(cacheNames = "MenuCahche" ,key = "#root.methodName")
    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }

    @CachePut(cacheNames = "MenuCahche",key = "#root.methodName")
    public List<Menu> putMenus(){
       return menuMapper.getAllMenus();
    }

}
