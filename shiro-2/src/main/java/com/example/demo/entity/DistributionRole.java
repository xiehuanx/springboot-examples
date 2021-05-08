package com.example.demo.entity;

import java.util.List;

/**
 * 分配角色权限实体
 *
 */
public class DistributionRole {

    private Long id;

    private List<UserRole> userRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
