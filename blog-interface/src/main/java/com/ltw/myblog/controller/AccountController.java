package com.ltw.myblog.controller;

import com.ltw.common.web.dto.Result;
import com.ltw.myblog.entity.input.AccountUpdateInput;
import com.ltw.myblog.entity.input.SignUpInput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主要管理注册和注销
 * 目前先思考一个问题，如何搭建一个登录页面？
 * 登录页面一般有注册接口，这就是注册接口
 * 注册时应写入一个手机号，用户名和密码，然后发送短信验证码，通过验证码完成注册
 * 那么请求体就是包含了手机号，用户名和密码，验证码
 * 验证码是通过发送验证码按钮生成的。生成时要根据当前用户的信息生成验证码，此时已经落库
 * 之后再点击注册，如果验证码正确，则注册成功，否则注册失败
 * 那么验证码是否要作为一个单独字段落库？应该不用，只要根据手机号用户名生成，那么相当于验证码隐藏在这些字段中了
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/signUp")
    public Result<String> signUp(@RequestBody SignUpInput input){


        return null;
    }

    @RequestMapping("/verifyCode")
    public Result<String> verifyCode(@RequestBody SignUpInput input){

        return Result.success();
    }


}
