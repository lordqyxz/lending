package com.p2p.lending;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.p2p.lending.dao")
public class LendingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LendingApplication.class, args);
    }

}
