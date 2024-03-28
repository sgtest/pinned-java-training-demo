package org.javaboy.test.controller;

import org.javaboy.test.model.Menu;
import org.javaboy.test.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
public class MenuController{

    @Autowired
    MenuService menuService;

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }
}