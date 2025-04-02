package com.ltw.common.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {
    
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("测试api")
                .version("1.0")
                .description("hello-knife4j"));
    }

    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("用户管理")
                .pathsToMatch("/user/**")
                .build();
    }
}
