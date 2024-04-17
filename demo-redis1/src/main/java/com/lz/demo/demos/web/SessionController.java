package com.lz.demo.demos.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author lizhi
 * @create 2024-01-30
 **/
@RestController
public class SessionController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    public String set(String name, HttpSession session){
        session.setAttribute("name",name);
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session){
        String name= (String) session.getAttribute("name");
        return "name:"+name+",port:"+port;
    }
}
