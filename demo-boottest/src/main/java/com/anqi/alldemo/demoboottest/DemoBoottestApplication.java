package com.anqi.alldemo.demoboottest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoBoottestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBoottestApplication.class, args);
    }

}
