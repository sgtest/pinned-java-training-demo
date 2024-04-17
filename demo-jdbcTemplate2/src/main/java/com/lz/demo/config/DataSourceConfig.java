package com.lz.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author lizhi
 * @create 2023-11-15
 **/
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.one")
    public DataSource dataSource01(){
        HikariDataSource dataSource01 = new HikariDataSource();
        return dataSource01;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.two")
    public DataSource dataSource02(){
        HikariDataSource dataSource02 = new HikariDataSource();
        return dataSource02;
    }
}
