package xyz.xiehuan.miaosha.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import xyz.xiehuan.miaosha.entity.Seckill;
import xyz.xiehuan.miaosha.service.SeckillService;

import java.util.List;

/**
 * <p>
 * 秒杀库存表 前端控制器
 * </p>
 *
 * @author xie
 * @since 2018-10-29
 */
@RestController
@RequestMapping("/sys/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/all")
    public List<Seckill> list(){
        return seckillService.list(null);
    }

}
