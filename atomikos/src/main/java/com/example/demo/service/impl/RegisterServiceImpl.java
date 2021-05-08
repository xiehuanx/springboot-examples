package com.example.demo.service.impl;

import com.example.demo.entity.xiehuan.Register;
import com.example.demo.mapper.xiehuan.RegisterMapper;
import com.example.demo.service.IRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiex
 * @since 2020-08-02
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements IRegisterService {

}
