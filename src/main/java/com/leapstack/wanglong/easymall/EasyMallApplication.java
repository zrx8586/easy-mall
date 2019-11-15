package com.leapstack.wanglong.easymall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.leapstack.wanglong.easymall.dao.mapper")
public class EasyMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyMallApplication.class, args);
    }

}
