package xyz.xiehuan.quartz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xiehuan.quartz.entity.SysUser;

import java.util.List;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/11/9
 * Time: 15:05
 * Describe:
 */
public interface UserRepository extends JpaRepository<SysUser,Integer> {
    /**
     * 根据id  查询数据
     * @param id
     * @return
     */
    List<SysUser> findAllById(Integer id);


}
