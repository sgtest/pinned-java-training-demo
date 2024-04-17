package com.lz.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * @author lizhi
 * @create 2023-11-16
 **/
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    DataSource ds1(){
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    DataSource ds2(){
        return new HikariDataSource();
    }
}
