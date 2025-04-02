package com.ltw.user.dao.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_user")
@Builder
public class BlogUser implements Serializable {
    private Long id;

    private String loginName;
    @ApiModelProperty(value = "用户名")
    private String username;

    private String password;

    private String avatar;

    private String email;

    private String phone;

    private Long roleId;

    private String salt;

    private static final long serialVersionUID = 1L;

}