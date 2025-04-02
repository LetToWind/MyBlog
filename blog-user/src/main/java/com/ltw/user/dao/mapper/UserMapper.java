package com.ltw.user.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltw.user.dao.dto.BlogUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<BlogUser> {
    int deleteByPrimaryKey(Long id);

    int insert(BlogUser row);

    BlogUser selectByPrimaryKey(Long id);

    List<BlogUser> selectAll();

    int updateByPrimaryKey(BlogUser row);

    BlogUser selectByUsername(String username);


    int updatePhoneByWrapper(@Param("ew")LambdaQueryWrapper<BlogUser> wrapper, @Param("phone") String phone);
}