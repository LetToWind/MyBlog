package com.ltw.blogservice;

import com.ltw.blogservice.dao.dto.CommentExample;
import com.ltw.blogservice.dao.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogServiceApplication {

    @Autowired
    private CommentMapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }

}
