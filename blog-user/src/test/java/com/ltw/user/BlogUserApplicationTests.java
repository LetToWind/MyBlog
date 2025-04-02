package com.ltw.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ltw.common.util.JwtUtil;
import com.ltw.user.dao.dto.BlogUser;
import com.ltw.user.dao.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BlogUserApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        BlogUser admin = userMapper.selectByUsername("admin");
        System.out.println(admin);
    }

    /**
     * 权且作记忆不做使用，为什么只能命名为ew，而且感觉不符合开发规范
     */
    @Test
    void updateTest() {
        LambdaQueryWrapper<BlogUser> userLambdaQueryWrapper = new LambdaQueryWrapper<BlogUser>().like(BlogUser::getUsername,"admin");
        userMapper.updatePhoneByWrapper(userLambdaQueryWrapper,"12345678");
    }

    @Test
    void testJwt(){
        // JWT头部分信息【Header】
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        // 载核【Payload】
        Map<String, Object> payload = new HashMap<>();
        payload.put("sub", "1234567890");
        payload.put("name","John Doe");
        payload.put("admin",true);

        // 声明Token失效时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,300);// 300s

        // 生成Token
        String token = Jwts.builder()
                .setHeader(header)// 设置Header
                .setClaims(payload) // 设置载核
                .setExpiration(instance.getTime())// 设置生效时间
                .signWith(SignatureAlgorithm.HS256,"LetToWindSignKeyMustBeGreaterThanOrEqual256bit") // 签名,这里采用私钥进行签名,不要泄露了自己的私钥信息
                .compact(); // 压缩生成xxx.xxx.xxx

        System.out.println(token);
        Claims bt = JwtUtil.parseJWT("LetToWindSignKeyMustBeGreaterThanOrEqual256bit", token);
        System.out.println(bt);
    }

}
