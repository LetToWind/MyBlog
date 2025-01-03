package com.ltw.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltw.user.dao.dto.User;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Long id);

    int insert(User row);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User row);

    User selectByUsername(String username);
}