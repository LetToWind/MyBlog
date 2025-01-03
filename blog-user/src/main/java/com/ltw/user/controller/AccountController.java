package com.ltw.user.controller;

import com.ltw.common.web.dto.Result;
import com.ltw.user.service.IUserService;
import com.ltw.user.vo.SignUpInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AccountController {

        @Qualifier("userServiceImpl")
        @Autowired
        private IUserService userService;

        /**
         * 注册 目前只考虑用户名密码
         * @return
         */
        @PostMapping("/signUp")
        public Result<String> signUp(@RequestBody SignUpInput signUpInput){
                Integer signUp = userService.signUp(signUpInput);
                if(signUp == 0){
                        return Result.fail("注册失败");
                }
                return Result.success("注册成功");
        }

        @PostMapping("/login")
        public Result<String> login(@RequestBody SignUpInput signUpInput){
                userService.login(signUpInput);
                return Result.success("登录成功");
        }

}
