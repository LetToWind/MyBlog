package com.runes.commponent.security.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

@Configuration
public class BaseConfig {
    @Bean
    @ConditionalOnMissingBean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return  new JPAQueryFactory(entityManager);
    }

}
