package com.ltw.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ltw.common.web.exception.BaseException;
import com.ltw.user.dao.dto.User;
import com.ltw.user.dao.mapper.UserMapper;
import com.ltw.user.service.IUserService;
import com.ltw.user.vo.SignUpInput;
import com.ltw.user.vo.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements IUserService, IService<User> {

    @Autowired
    UserMapper userMapper;


    @Override
    public User queryUserByUserExample(UserQuery query) {
       return null;
    }

    @Override
    public Integer signUp(SignUpInput signUpInput) {

        User user = User.builder().build();
        user.setId(IdWorker.getId());
        user.setUsername(signUpInput.getUsername());
        user.setPassword(signUpInput.getPassword());
        return userMapper.insert(user);
    }

    @Override
    public String login(SignUpInput signUpInput) {
        User user = userMapper.selectByUsername(signUpInput.getUsername());
        if(user != null && user.getPassword().equals(signUpInput.getPassword())) {
            return "OK";
        }
        else {
            throw new BaseException("登陆失败");
        }
    }

    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(User entity) {
        return false;
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<User> getOneOpt(Wrapper<User> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<User> getBaseMapper() {
        return null;
    }

    @Override
    public Class<User> getEntityClass() {
        return null;
    }
}
