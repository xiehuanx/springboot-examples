package xyz.xiehuan.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.xiehuan.jpa.dao.UserRepository;
import xyz.xiehuan.jpa.entity.SysUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class JpaApplicationTests {

    @Autowired
    private UserRepository  userRepository;

    @Test
    public void contextLoads() {

        List<SysUser> allById = userRepository.findAllById(1);
        System.out.println(allById);

        List<SysUser> all = userRepository.findAll(Sort.by(Sort.Order.asc("name")));

        List<String> collect = all.parallelStream().map(SysUser::getName).collect(Collectors.toList());



        System.out.println(collect);

    }

    @Test
    public  void test1(){
        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        SysUser sysUser2 = new SysUser();
        sysUser2.setId(2);
        SysUser sysUser3 = new SysUser();
        sysUser3.setId(3);
        SysUser sysUser4 = new SysUser();
        sysUser4.setId(4);
        SysUser sysUser5 = new SysUser();
        sysUser5.setId(5);

        List<SysUser> sysUsers=new ArrayList<>();

        sysUsers.add(sysUser);
        sysUsers.add(sysUser2);
        sysUsers.add(sysUser3);
        sysUsers.add(sysUser4);
        sysUsers.add(sysUser5);


        long count = sysUsers.parallelStream().count();

        log.info("数量是：{}",count);
        Map<Integer, Double> collect = sysUsers.parallelStream().collect(Collectors.groupingBy(SysUser::getId, Collectors.summingDouble(SysUser::getId)));

        log.info("map ：{}",collect);

        Double collect1 = sysUsers.parallelStream().collect(Collectors.averagingInt(SysUser::getId));

        log.info("Double ：{}",collect1);


    }

}
