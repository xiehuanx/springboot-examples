package com.example.demo.service;

import com.example.demo.entity.DistributionRole;
import com.example.demo.entity.Role;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * role 角色表 服务类
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
public interface RoleService extends IService<Role> {

    /**
     * 删除角色
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 分配权限操作
     * @param distributionRole
     * @return
     */
    boolean distributionRole(DistributionRole distributionRole);
	
}
