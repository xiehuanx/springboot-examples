package com.example.demo.mapper;

import com.example.demo.entity.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * permission 权限表 Mapper 接口
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findPermissionListByUserId(Long userId);

    List<Permission> findMenuListByUserId(Long userId);

    List<Permission> findUserMenuListType1(Permission permission);

}