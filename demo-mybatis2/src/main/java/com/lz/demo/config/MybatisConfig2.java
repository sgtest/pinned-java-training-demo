package com.lz.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author lizhi
 * @create 2023-11-16
 **/
@Configuration
@MapperScan(basePackages = "com.lz.demo.mapper2",sqlSessionFactoryRef = "sessionFactory2",sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MybatisConfig2 {

    @Autowired
    @Qualifier("ds2")
    DataSource dataSource2;

    @Bean
    SqlSessionFactory sessionFactory2(){
        SqlSessionFactory sessionFactory=null;
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource2);
        try {
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate2(){
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sessionFactory2());
        return sqlSessionTemplate;
    }
}