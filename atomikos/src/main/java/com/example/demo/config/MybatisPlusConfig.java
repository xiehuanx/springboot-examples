package com.example.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus 配置
 * @author XiaoBingBy
 * @date 2018-08-30 10:41
 * @since 1.0
 */
@Configuration
/*@MapperScans(value = {@MapperScan("com.example.demo.mapper.xiehuan"),
        @MapperScan("com.example.demo.mapper.xiex")
})*/
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
