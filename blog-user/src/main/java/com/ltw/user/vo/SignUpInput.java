package com.ltw.user.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class SignUpInput {

    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String phone;

    // 验证码，仅用于注册，获取验证码时这个字段为空，注册时则有值
    private String verifyCode;
}
