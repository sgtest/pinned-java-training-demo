package com.lz.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * @author lizhi
 * @create 2023-11-16
 **/
@Configuration
@MapperScan(basePackages = "com.lz.demo.mapper1",sqlSessionFactoryRef = "sessionFactory1",sqlSessionTemplateRef = "sqlSessionTemplate1")
public class MybatisConfig1 {

    @Autowired
    @Qualifier("ds1")
    DataSource dataSource1;

    @Bean
    SqlSessionFactory sessionFactory1(){
        SqlSessionFactory sessionFactory=null;
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource1);
        try {
             sessionFactory = bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate1(){
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sessionFactory1());
        return sqlSessionTemplate;
    }
}
