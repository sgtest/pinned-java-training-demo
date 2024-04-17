package com.lz.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author lizhi
 * @create 2023-11-15
 **/
@Configuration
public class JdbcTemplateConfig {

    @Resource(name = "dataSource01")
    DataSource dataSource01;

    @Resource(name = "dataSource02")
    DataSource dataSource02;

    @Bean
    JdbcTemplate jdbcTemplateOne(){
        return new JdbcTemplate(dataSource01);
    }

    @Bean
    JdbcTemplate jdbcTemplateTwo(){
        return new JdbcTemplate(dataSource02);
    }
}
