package com.ltw.user.dao.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_permission")
public class Permission implements Serializable {
    private Long id;

    private String permissionName;

    private String permissionDesc;

    private Integer roleId;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private static final long serialVersionUID = 1L;

}