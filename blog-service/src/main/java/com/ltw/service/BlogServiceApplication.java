package com.ltw.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan
@ComponentScan(basePackages = {"com.ltw.common","com.ltw.blogservice"})
public class BlogServiceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
