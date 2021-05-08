package com.example.demo.service.impl;

import com.example.demo.entity.DistributionRole;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * role 角色表 服务实现类
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public boolean distributionRole(DistributionRole distributionRole) {
        return false;
    }
}
