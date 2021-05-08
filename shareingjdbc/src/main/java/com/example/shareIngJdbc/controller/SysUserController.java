package com.example.shareIngJdbc.controller;


import com.example.shareIngJdbc.entity.SysUser;
import com.example.shareIngJdbc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xie
 * @since 2018-09-01
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/list")
    public List<SysUser> sysUserList(){


        return sysUserService.list(null);
    }
    //TODO
    @RequestMapping("/insert")
    public boolean insert(){
        SysUser sysUser = new SysUser();
        sysUser.setName("测试");
        boolean b = sysUserService.saveOrUpdate(sysUser);
        return b;
    }

    @RequestMapping("/update")
    public boolean update(){
        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        sysUser.setName("update");
        boolean b = sysUserService.saveOrUpdate(sysUser);
        return b;
    }

    @RequestMapping("/delete")
    public boolean delete(){

        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        boolean b = sysUserService.removeById(1);
        return b;
    }

}

