package com.ltw.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltw.user.service.RoleService;
import com.ltw.user.dao.dto.Role;
import com.ltw.user.dao.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author zengd
* @description 针对表【t_role】的数据库操作Service实现
* @createDate 2025-02-20 20:23:23
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




