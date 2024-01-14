package com.ltw.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyBlogApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyBlogApplication.class, args);
	}

}
