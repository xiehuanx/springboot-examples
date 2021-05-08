package com.example.demo;

import com.example.demo.dao.DeptDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private DeptDao deptDao;

	@Test
	public void contextLoads() {

		System.out.println(deptDao.findDeptEntityByDeptid(1+""));
		System.out.println(deptDao.findById(1));


	}

}
