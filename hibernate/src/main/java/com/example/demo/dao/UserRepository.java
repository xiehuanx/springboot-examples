package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2017/12/16
 * Time: 21:40
 * Describe:
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {



}
