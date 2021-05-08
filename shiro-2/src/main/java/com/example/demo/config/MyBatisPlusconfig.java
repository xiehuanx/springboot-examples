package com.example.demo.config;

import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: xie
 * Email: 1487471733@qq.com
 * Date: 2017/6/15
 * Time: 10:48
 * Describe:
 */
@Configuration
@MapperScan("com.example.demo.mapper*")
public class MyBatisPlusconfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType(DBType.MYSQL.getDb());
/*        GlobalConfiguration gc = new GlobalConfiguration();
        gc.setDbType("mysql");
        gc.setIdType(IdType.INPUT.getKey());
        gc.setDbColumnUnderline(true);
        mybatisPlus.setGlobalConfig(gc);*/
        return page;
    }


}
