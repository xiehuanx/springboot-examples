package com.example.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * user 用户表 前端控制器
 * </p>
 *
 * @author xie
 * @since 2017-09-21
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @RequestMapping("/list")
    public String user(Model model){
        EntityWrapper<Role> wrapper = new EntityWrapper<>();
        wrapper.or("sort", true);
        List<Role> allRole = roleService.selectList(wrapper);
        model.addAttribute("allRole", allRole);
        return "/admin/user/list";
    }



    @RequestMapping("/listuser")
    @ResponseBody
    public List<User> userList(){

        return userService.selectList(null);

    }

	
}
