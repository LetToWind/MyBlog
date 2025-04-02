package com.ltw.user.service;

import com.ltw.user.vo.SignUpInput;

public interface IUserService {

    Integer signUp(SignUpInput signUpInput);

    String login(SignUpInput signUpInput);
}
