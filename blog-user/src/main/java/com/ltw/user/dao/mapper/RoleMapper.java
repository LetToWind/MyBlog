package com.ltw.user.dao.mapper;

import com.ltw.user.dao.dto.Role;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role row);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role row);
}