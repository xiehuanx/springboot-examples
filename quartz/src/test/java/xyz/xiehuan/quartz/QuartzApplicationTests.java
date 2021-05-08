package xyz.xiehuan.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.xiehuan.quartz.entity.TaskEntity;
import xyz.xiehuan.quartz.service.ITaskService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class QuartzApplicationTests {


    @Autowired
    private ITaskService iTaskService;

    @Test
    public void contextLoads() {
        HashMap hashMap=new HashMap();
        hashMap.put("f","f");
        hashMap.put("y","y");
        hashMap.put("d","d");
        hashMap.put("a","a");

        hashMap.put("3","3");

        hashMap.put("2","2");
        hashMap.put("9","9");
        hashMap.put("4","4");
        Iterator map1it=hashMap.entrySet().iterator();
        while(map1it.hasNext())
        {
            Map.Entry<String, String> entry=(Map.Entry<String, String>) map1it.next();
            System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
        }

    }

    @Test
    public void test4() {
        LinkedHashMap linkedHashMap=new LinkedHashMap();

        linkedHashMap.put("f","f");
        linkedHashMap.put("y","y");
        linkedHashMap.put("d","d");
        linkedHashMap.put("a","a");

        linkedHashMap.put("3","3");

        linkedHashMap.put("2","2");
        linkedHashMap.put("9","9");
        linkedHashMap.put("4","4");
        Iterator map1it=linkedHashMap.entrySet().iterator();
        while(map1it.hasNext())
        {
            Map.Entry<String, String> entry=(Map.Entry<String, String>) map1it.next();
            System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
        }

    }


    @Test
    public void test3() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(2l);
        taskEntity.setJobClass("xyz.xiehuan.quartz.job.TestJob");
        taskEntity.setJobName("app2");
        taskEntity.setJobGroup("app");
        taskEntity.setCronExpression("0/5 * * * * ? *");
        iTaskService.addTask(taskEntity);


    }



}
