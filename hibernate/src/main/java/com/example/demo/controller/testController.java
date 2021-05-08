package com.example.demo.controller;

import com.example.demo.dao.DeptDao;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.DeptEntity;
import com.example.demo.entity.UserEntity;
import org.assertj.core.internal.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2017/12/15
 * Time: 22:47
 * Describe:
 */
@RestController
public class testController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DeptDao deptDao;


    @GetMapping("/123")
    public String test(){
        return "sdsd";

    }

    @GetMapping("/test")
    public List<UserEntity> test1(){
        Iterable<UserEntity> userEntityIterable= userRepository.findAll();

        List<UserEntity> userRepositoryList= new ArrayList<>();

        userEntityIterable.forEach(item->{
            userRepositoryList.add(item);
        });
        return  userRepositoryList;
    }



    @GetMapping("/test2")
    public List<DeptEntity> test2(){
        Iterable<DeptEntity> userEntityIterable= deptDao.findAll();

        List<DeptEntity> userRepositoryList= new ArrayList<>();

        userEntityIterable.forEach(item->{
            userRepositoryList.add(item);
        });
        return  userRepositoryList;
    }
}
