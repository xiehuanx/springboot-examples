package xyz.xiehuan.quartz.job;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/11/13
 * Time: 15:15
 * Describe:
 */
public class TestJob implements Job, Serializable {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {



        System.out.println("12121212");

    }
}
