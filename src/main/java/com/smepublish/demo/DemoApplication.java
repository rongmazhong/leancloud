package com.smepublish.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * DemoApplication 启动
 *
 * @author MZRong
 * @date 2019/1/16 15:46
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.smepublish.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

