package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.annotation.DataSource;
import com.example.demo.entity.xiex.SysUser;
import com.example.demo.mapper.xiex.SysUserMapper;
import com.example.demo.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XiaoBingBy
 * @since 2020-08-01
 */
@Service
@DataSource(name="xiex")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
