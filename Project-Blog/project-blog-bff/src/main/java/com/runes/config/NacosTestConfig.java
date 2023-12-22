package com.runes.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 *
 * @author 陈宇锋
 * @date 2023/12/22
 */
@Configuration
public class NacosTestConfig implements InitializingBean {

    @Value(value = "${ribbon.ServerListRefreshInterval}")
    private String getValue;
    @Resource  //(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(getValue);
        stringRedisTemplate.opsForValue().set("2","yi");
    }
}
