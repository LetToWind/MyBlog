package com.ltw.user.service;

import com.ltw.user.dao.dto.User;
import com.ltw.user.vo.SignUpInput;
import com.ltw.user.vo.UserQuery;

public interface IUserService {

    User queryUserByUserExample(UserQuery query);

    Integer signUp(SignUpInput signUpInput);

    String login(SignUpInput signUpInput);
}
