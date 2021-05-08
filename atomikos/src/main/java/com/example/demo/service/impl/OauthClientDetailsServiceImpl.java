package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.xiex.OauthClientDetails;
import com.example.demo.mapper.xiex.OauthClientDetailsMapper;
import com.example.demo.service.IOauthClientDetailsService;
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
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

}
