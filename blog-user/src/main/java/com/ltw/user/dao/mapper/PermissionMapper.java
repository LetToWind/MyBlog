package com.ltw.user.dao.mapper;

import com.ltw.user.dao.dto.Permission;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission row);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission row);
}