package com.ltw.user.dao.dto;

import lombok.Data;

/**
 * 用户和角色之间是多对多关系，需要一张表实现转化
 * 但角色与权限之间就不需要了，如果控制好角色和权限的创建应该足够了
 */
@Data
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

}
