package com.example.demo;
import java.time.LocalDateTime;

import com.example.demo.config.multipleDataSources.DynamicRoutingDataSource;
import com.example.demo.entity.xiehuan.Notice;
import com.example.demo.entity.xiex.SysUser;
import com.example.demo.service.INoticeService;
import com.example.demo.service.ISysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
  @Autowired
  private ISysUserService sysUserService;
  @Autowired
  private INoticeService noticeService;

  @Test
  void contextLoads() {
    //DynamicDataSourceContextHolder.setDataSourceRouterKey("xiex");
    List<SysUser> list = sysUserService.list();
    System.out.println(list);
    //DynamicDataSourceContextHolder.setDataSourceRouterKey("xiehuan");
    List<Notice> list1 = noticeService.list();
    System.out.println(list);

    //String[] strings= {"com.example.demo.mapper.xiehuan","com.example.demo.mapper.xiex"};
  }

  @Test
  void  setSysUserService(){
    SysUser sysUser = new SysUser();
    sysUser.setUserId(12);
    sysUser.setLoginName("dsad");
    sysUser.setUserName("dsad");
    sysUser.setUserNickname("sadsa");
    sysUser.setUserPassword("dasdsa");
    sysUser.setUserPasswordHis("xzczxc");
    sysUser.setUserSalt("");
    sysUser.setUserEmail("");
    sysUser.setUserPhone("");
    sysUser.setUserAddress("");
    sysUser.setUserStatus("");
    sysUser.setCreateUser("");
    sysUser.setUpdateUser("");
    DynamicRoutingDataSource.setDataSource("xiex");
    sysUserService.save(sysUser);
    Notice notice = new Notice();
    notice.setContents("dsadsa");
    notice.setLevel("1");
    notice.setDate(LocalDateTime.now());
    notice.setStatus("1");
    notice.setStatusdate(LocalDateTime.now());
    DynamicRoutingDataSource.setDataSource("xiehuan");
    noticeService.save(notice);
    int i =1/0;

  }

}
