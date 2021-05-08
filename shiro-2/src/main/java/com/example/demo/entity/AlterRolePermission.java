package com.example.demo.entity;

import java.util.List;

/**
 * 更改权限接受数据
 *
 */
public class AlterRolePermission {

    private Long id;

    private List<RolePermission> rolePermissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
