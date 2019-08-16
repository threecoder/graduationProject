package com.lutayy.campbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lutayy.campbackend.dao")
@SpringBootApplication
public class CampBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampBackendApplication.class, args);
    }

}
