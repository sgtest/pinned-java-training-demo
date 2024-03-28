package com.stytem.demo.controller;

import com.stytem.demo.service.MenuService;
import com.stytem.demo.bean.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizhi
 * @create 2024-03-27
 **/
@RestController
public class Controller {

    @Autowired
    MenuService menuService;

    @GetMapping("/hello")
    public String hello(){
        return "hello security_db";
    }

    @GetMapping("/db/hello")
    public String dbHello(){
        return "hello db()";
    }

    @GetMapping("/admin/hello")
    public String adminHello(){
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String userHello(){
        return "hello user";
    }

    @GetMapping("/getAllMenu")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenus();
    }
}
