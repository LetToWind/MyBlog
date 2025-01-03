package com.ltw.user.dao.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_role")
public class Role implements Serializable {
    private Long id;

    private String roleName;

    private String roleDesc;

    private List<Permission> permissionList;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private static final long serialVersionUID = 1L;


}