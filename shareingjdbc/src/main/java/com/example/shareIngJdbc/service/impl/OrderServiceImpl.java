package com.example.shareIngJdbc.service.impl;

import com.example.shareIngJdbc.dao.OrderItemDao;
import com.example.shareIngJdbc.entity.tOrder;
import com.example.shareIngJdbc.dao.OrderDao;
import com.example.shareIngJdbc.entity.tOrderItem;
import com.example.shareIngJdbc.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xie
 * @since 2018-09-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, tOrder> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;


    public  void demo(){

        List<Long> orderIds = new ArrayList<Long>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 10; i++) {
            tOrder tOrder = new tOrder();
            //tOrder.setOrderId(i);
            tOrder.setUserId(51);
            tOrder.setStatus("INSERT_TEST");
            orderDao.insert(tOrder);
            Long orderId = tOrder.getOrderId();
            orderIds.add(orderId);

            tOrderItem item = new tOrderItem();
            //item.setOrderId(i);
            item.setOrderId(orderId);
            item.setUserId(51);
            orderItemDao.insert(item);
        }
        System.out.println(orderItemDao.selectList(null));
        System.out.println("2.Delete--------------");
        for (Long each : orderIds) {
            orderDao.deleteById(each);
            orderItemDao.deleteById(each);
        }
        System.out.println(orderItemDao.selectList(null));
    }

}
