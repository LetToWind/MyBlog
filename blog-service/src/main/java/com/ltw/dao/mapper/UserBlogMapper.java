package com.ltw.dao.mapper;

import com.ltw.dao.dto.UserBlog;
import com.ltw.dao.dto.UserBlogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface UserBlogMapper {
    long countByExample(UserBlogExample example);

    int deleteByExample(UserBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBlog record);

    int insertSelective(UserBlog record);

    List<UserBlog> selectByExampleWithBLOBs(UserBlogExample example);

    List<UserBlog> selectByExample(UserBlogExample example);

    UserBlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBlog record, @Param("example") UserBlogExample example);

    int updateByExampleWithBLOBs(@Param("record") UserBlog record, @Param("example") UserBlogExample example);

    int updateByExample(@Param("record") UserBlog record, @Param("example") UserBlogExample example);

    int updateByPrimaryKeySelective(UserBlog record);

    int updateByPrimaryKeyWithBLOBs(UserBlog record);

    int updateByPrimaryKey(UserBlog record);
}