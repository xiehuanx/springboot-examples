package com.example.shareIngJdbc;

import com.example.shareIngJdbc.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ShareingjdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareingjdbcApplication.class, args);
        //applicationContext.getBean(OrderService.class).demo();
    }
}
