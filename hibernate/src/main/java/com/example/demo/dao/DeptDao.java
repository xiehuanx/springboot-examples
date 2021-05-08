package com.example.demo.dao;

import com.example.demo.entity.DeptEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2017/12/16
 * Time: 22:16
 * Describe:
 */
public interface DeptDao  extends CrudRepository<DeptEntity,Long>{

    List<DeptEntity> findDeptEntityByDeptid(String  id);

    DeptEntity findByDeptid(String id);

    DeptEntity findById(int id);
}
