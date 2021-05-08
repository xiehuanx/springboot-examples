package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * user_role 用户角色关联表 服务实现类
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
	
}
