package com.example.demo.service;

import com.example.demo.entity.AlterRolePermission;
import com.example.demo.entity.Permission;
import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.TreeNode;
import com.example.demo.entity.ZNodes;

import java.util.List;

/**
 * <p>
 * permission 权限表 服务类
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> findPermissionListByUserId(Long userId);

    List<Permission> findMenuListByUserId(Long userId);

    List<TreeNode> createTree(Long userId);

    public List<TreeNode> listTree(Long userId);

    /**
     * 删除权限
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 传入用户ID 查找出所有权限,选择用户拥有的权限
     * @return
     */
    List<ZNodes> getUserByIdPermission(Long roleId);

    /**
     * 传入角色信息和权限信息进行更新
     * @param alterRolePermission
     * @return
     */
    boolean updateRolePermission(AlterRolePermission alterRolePermission);
	
}
