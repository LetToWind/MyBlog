package com.ltw.myblog.entity.input;

import lombok.Data;

@Data
public class SignUpInput {

    private String username;
    private String password;
    private String verifyCode;

}
