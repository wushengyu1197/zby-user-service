package com.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.shopping.mapper"})
public class ZbyUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZbyUserServiceApplication.class, args);
    }

}
