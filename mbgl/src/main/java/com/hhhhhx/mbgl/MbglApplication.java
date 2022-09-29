package com.hhhhhx.mbgl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hhhhhx.mbgl.mapper")
public class MbglApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbglApplication.class, args);
    }
}
