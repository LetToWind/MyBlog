package com.ltw.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltw.common.web.exception.BaseException;
import com.ltw.user.dao.dto.BlogUser;
import com.ltw.user.dao.dto.Role;
import com.ltw.user.dao.mapper.RoleMapper;
import com.ltw.user.dao.mapper.UserMapper;
import com.ltw.user.service.IUserService;
import com.ltw.user.service.RoleService;
import com.ltw.user.vo.SignUpInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, BlogUser> implements IUserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

    @Override
    public Integer signUp(SignUpInput signUpInput) {

        BlogUser blogUser = BlogUser.builder().build();
        blogUser.setId(IdWorker.getId());
        blogUser.setUsername(signUpInput.getUsername());
        blogUser.setPassword(signUpInput.getPassword());
        return userMapper.insert(blogUser);
    }

    @Override
    public String login(SignUpInput signUpInput) {
        BlogUser blogUser = userMapper.selectByUsername(signUpInput.getUsername());
        if(blogUser != null && blogUser.getPassword().equals(signUpInput.getPassword())) {
            return "OK";
        }
        else {
            throw new BaseException("登陆失败");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser blogUser = userMapper.selectByUsername(username);
        //此处简化开发，不再设置role -> permission，直接将role当做permission使用，当然，想实现前者也不难就是了
        Role role = roleService.getById(blogUser.getRoleId());
        return new User(
                blogUser.getUsername(),
                blogUser.getPassword(),
                CollectionUtil.newHashSet(new SimpleGrantedAuthority(role.getRoleName()))
        );
    }
}
