package com.example.shareIngJdbc.service.impl;

import com.example.shareIngJdbc.entity.tOrderItem;
import com.example.shareIngJdbc.dao.OrderItemDao;
import com.example.shareIngJdbc.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xie
 * @since 2018-09-01
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, tOrderItem> implements OrderItemService {

}
