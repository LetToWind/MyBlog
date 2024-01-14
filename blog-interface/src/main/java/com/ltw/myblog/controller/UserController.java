package com.ltw.myblog.controller;

import com.ltw.common.dto.Result;
import com.ltw.myblog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主要管登录和登出
 * 要分几种登录，1，游客，2，注册用户，3，管理员
 * 这里只负责登入和登出
 * 游客没有登出
 */
@Controller
@RequestMapping("/login-front")
public class UserController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public Result<String> login(String username, String password) {
        String token = loginService.login(username, password);
        return Result.success(token);
    }

    @RequestMapping("/logout")
    public Result logout() {
        return Result.success();
    }
}
