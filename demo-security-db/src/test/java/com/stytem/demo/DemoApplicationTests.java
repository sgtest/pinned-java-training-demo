package com.stytem.demo;

import com.stytem.demo.service.MenuService;
import com.stytem.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    MenuService menuService;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        List<Menu> menus = menuService.getAllMenus();
//        for (Menu menu : menus) {
//            System.out.println(menu);
//        }

        userService.loadUserByUsername("root");
    }

}
