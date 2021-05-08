package com.example.demo.service.impl;

import com.example.demo.annotation.DataSource;
import com.example.demo.entity.xiehuan.Notice;
import com.example.demo.mapper.xiehuan.NoticeMapper;
import com.example.demo.service.INoticeService;
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
@DataSource(name="xiehuan")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
