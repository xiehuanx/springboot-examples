package com.example.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.entity.*;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.mapper.RolePermissionMapper;
import com.example.demo.service.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.service.RolePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * permission 权限表 服务实现类
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private RolePermissionService rolePermissionService;


    @Override
    public List<Permission> findPermissionListByUserId(Long userId) {
        return permissionMapper.findPermissionListByUserId(userId);
    }

    @Override
    public List<Permission> findMenuListByUserId(Long userId) {
        return permissionMapper.findMenuListByUserId(userId);
    }

    @Override
    public List<TreeNode> createTree(Long userId) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Permission> menuList = this.findMenuListByUserId(userId);
        for (Permission permission : menuList) {
            TreeNode temp = new TreeNode();
            BeanUtils.copyProperties(permission, temp);
            treeNodes.add(temp);
        }
        return treeNodes;
    }

    @Override
    public List<TreeNode> listTree(Long userId) {
        List<TreeNode> treeNodes = this.createTree(userId);
        for (TreeNode item : treeNodes) {
            Permission userInfo = new Permission();
            userInfo.setId(userId);
            userInfo.setPid(item.getId());
            List<Permission> permissionListByUserId = permissionMapper.findUserMenuListType1(userInfo);
            List<TreeNode> treeNodesTems = new ArrayList<>();
            for (Permission permission : permissionListByUserId) {
                TreeNode temp = new TreeNode();
                BeanUtils.copyProperties(permission, temp);
                treeNodesTems.add(temp);
            }
            item.setChildren(treeNodesTems);
        }
        return treeNodes;
    }

    @Transactional
    @Override
    public boolean deleteById(String id) {
        String[] split = id.split(",");
        List<String> userId = new ArrayList<>();
        for (String item:split) {
            userId.add(item);
        }
        Integer integer = permissionMapper.deleteBatchIds(userId);
       /* if (integer != split.length) {
            new RuntimeException("删除失败");
        }*/
        return true;
    }

    @Override
    public List<ZNodes> getUserByIdPermission(Long roleId) {
        //查询所有权限
        EntityWrapper<Permission> permissionEntityWrapper = new EntityWrapper<>();
        permissionEntityWrapper.eq("status", "1");
        permissionEntityWrapper.orderBy("sort", true);
        List<Permission> permissions = permissionMapper.selectList(permissionEntityWrapper);
        List<ZNodes> nodes = new ArrayList<>();
        for (Permission permission : permissions) {
            ZNodes zNodes = new ZNodes();
            zNodes.setId(permission.getId());
            zNodes.setpId(permission.getPid());
            if (permission.getType() == 0) {
                zNodes.setParent(true);
                zNodes.setOpen(true);
            }
            zNodes.setName(permission.getName());

            nodes.add(zNodes);
        }

        /**
         * 查询roleId角色拥有那些权限
         */
        EntityWrapper<RolePermission> rolePermissionEntityWrapper = new EntityWrapper<>();
        rolePermissionEntityWrapper.eq("rid", roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionEntityWrapper);

        for (ZNodes node : nodes){
            for (RolePermission rolePermission : rolePermissions) {
                if (node.getId() == rolePermission.getPid()) {
                    node.setChecked(true);
                }
            }
        }
        return nodes;
    }


    @Transactional
    @Override
    public boolean updateRolePermission(AlterRolePermission alterRolePermission) {
        //删除原来数据
        EntityWrapper<RolePermission> rolePermissionEntityWrapper = new EntityWrapper<>();
        rolePermissionEntityWrapper.eq("rid", alterRolePermission.getId());
        rolePermissionService.delete(rolePermissionEntityWrapper);

        //添加节点数据
        List<RolePermission> rolePermissions = alterRolePermission.getRolePermissions();
        for (RolePermission rolePermission:rolePermissions) {
            rolePermission.setRid(alterRolePermission.getId());
        }
        boolean b = rolePermissionService.insertBatch(rolePermissions);
        return b;
    }
}
