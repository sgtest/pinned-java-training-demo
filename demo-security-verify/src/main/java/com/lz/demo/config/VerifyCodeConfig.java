package com.lz.demo.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author lizhi
 * @create 2024-04-15
 **/
@Configuration
public class VerifyCodeConfig {

    @Bean
    Producer verifyCode() {
        Properties properties = new Properties();
        //设置验证码图片的宽
        properties.setProperty("kaptcha.image.width", "150");
        //设置验证码图片的高
        properties.setProperty("kaptcha.image.height", "50");
        //设置验证码图片的随机字符集
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789abcdefghijklmnopqrstuvwxyz");
        //设置验证码图片的字符集长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
