package com.lz.demo.controller;

import com.google.code.kaptcha.Producer;
import com.lz.demo.config.VerifyCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lizhi
 * @create 2024-04-15
 **/
@RestController
public class Controller {

    @Autowired
    Producer producer;

    @GetMapping("/vf")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //响应格式为图片
        response.setContentType("image/jpeg");
        //获取验证码字符串
        String text = producer.createText();
        //session中设置验证码字符串，session中新增名字为verify_code的Attribute
        session.setAttribute("verify_code", text);
        //生成验证码图片
        BufferedImage image = producer.createImage(text);
        try(ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/admin/hello")
    public String HelloAdmin(Authentication authentication){
        return "hello admin: "+authentication.getName();
    }

    @GetMapping("/hello")
    public void hello(HttpServletRequest req){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        System.out.println(details.getRemoteAddress());
    }
}
