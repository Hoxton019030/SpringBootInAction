package com.example.springbootinaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInActionApplication.class, args);
    }

}
