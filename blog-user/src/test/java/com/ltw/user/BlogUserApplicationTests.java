package com.ltw.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ltw.user.dao.dto.User;
import com.ltw.user.dao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogUserApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        User admin = userMapper.selectByUsername("admin");
        System.out.println(admin);
    }

    /**
     * 权且作记忆不做使用，为什么只能命名为ew，而且感觉不符合开发规范
     */
    @Test
    void updateTest() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>().like(User::getUsername,"admin");
        userMapper.updatePhoneByWrapper(userLambdaQueryWrapper,"12345678");
    }

}
