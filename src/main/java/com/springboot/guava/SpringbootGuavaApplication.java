package com.springboot.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootGuavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGuavaApplication.class, args);
    }

}
