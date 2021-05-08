package com.example.shareIngJdbc;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shareIngJdbc.config.cos.CosConfigProperties;
import com.example.shareIngJdbc.entity.ApiAbilitySpec;
import com.example.shareIngJdbc.entity.SysUser;
import com.example.shareIngJdbc.service.ApiAbilitySpecService;
import com.example.shareIngJdbc.service.SysUserService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.List;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private String bucketName;

    @Autowired
    private CosConfigProperties cosConfigProperties;

    @Autowired
    private ApiAbilitySpecService apiAbilitySpecService;

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void test10(){
        List<ApiAbilitySpec> apiAbilitySpecs = apiAbilitySpecService.list();
        System.out.println(apiAbilitySpecs);
    }

    @Test
    public void contextLoads() {

        List<SysUser> sysUsers = sysUserService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, 1));
        System.out.println(sysUsers);
        IPage<com.example.shareIngJdbc.entity.SysUser> page=new Page<>();
        page.setSize(1);
        page.setPages(1);
        IPage<com.example.shareIngJdbc.entity.SysUser> page1 = sysUserService.page(page,new LambdaQueryWrapper<SysUser>().eq(SysUser::getId,1));
        //System.out.println(sysUsers);
        System.out.println(page1);
/*        sysUsers.forEach(e->{
            boolean b = sysUserService.save(e);
            System.out.println(b);
        });*/


    }

    @Test
    public  void test1(){
        String key = stringRedisTemplate.opsForValue().get("key");
        if("".equals(key)||key==null){
            System.out.println("key is null ");
            stringRedisTemplate.opsForValue().set("key","value",60*10, TimeUnit.SECONDS);
        }else {
            System.out.println(key);
        }


    }

    /**
     * {@link String}
     */
    @Test
    public void test2(){

        SysUser sysUser = new SysUser();
        sysUser.setId(12);
        sysUser.setName("121");
        redisTemplate.opsForValue().set("user",sysUser);
        SysUser user = (SysUser) redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }


    @Test
    public void test3() throws MessagingException {

        String mail="1487471733@qq.com";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setFrom(new InternetAddress(from));

        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        //邮件的标题
        mimeMessage.setSubject("注册验证");
        //邮件的文本内容
        double code= Math.random();
        mimeMessage.setContent("<html><head><title>编辑邮件正文</title><style>html{word-wrap:break-word;}body{color:#000000;font-size:14px;font-family:Arial;line-height:1.7;padding:8px 10px;margin:0;background-color:#fff;}pre{white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;font-family:arial;}span.typoRed{border-bottom:2px dotted #ff0000;cursor:pointer;}.nui-scroll{overflow:auto}.nui-scroll::-webkit-scrollbar{width: 8px;height:8px}.nui-scroll::-webkit-scrollbar-track{border-radius:10px;-webkit-box-shadow:inset 0 0 6px rgba(0,0,0,0);}.nui-scroll::-webkit-scrollbar-track:hover{-webkit-box-shadow:inset 0 0 6px rgba(0,0,0,0.4);background-color:rgba(0,0,0,0.01)}.nui-scroll::-webkit-scrollbar-track:active{-webkit-box-shadow:inset 0 0 6px rgba(0,0,0,0.4);background-color:rgba(0,0,0,0.05)}.nui-scroll::-webkit-scrollbar-thumb{background-color: rgba(0, 0, 0, 0.05);border-radius:10px;-webkit-box-shadow:inset 1px 1px 0 rgba(0,0,0,.1)}.nui-scroll:hover::-webkit-scrollbar-thumb{background-color: rgba(0, 0, 0, 0.2);border-radius:10px;-webkit-box-shadow:inset 1px 1px 0 rgba(0,0,0,.1)}.nui-scroll::-webkit-scrollbar-thumb:hover{background-color:rgba(0, 0, 0, 0.4);-webkit-box-shadow:inset 1px 1px 0 rgba(0,0,0,.1)}.nui-scroll::-webkit-scrollbar-thumb:active{background:rgba(0,0,0,0.6);}</style></head><body class=\"nui-scroll\" contenteditable=\"true\"><div style=\"text-align: center;\"><span style=\"font-size: 24px;\"><b>注册验证</b></span></div><div style=\"text-align: left;\"><span style=\"font-size: 18px;\">\u200B欢迎注册 验证码:&nbsp;"+code+"   验证码有效期为十分钟</span></div></body></html>", "text/html;charset=UTF-8");
        mailSender.send(mimeMessage); //发送邮件

    }


    @Test
    public void test4(){
        String time="20181008112003";
        String time2="20181008114448";


        DateTime yyyyMMddHHmmss = DateUtil.parse(time2, "yyyyMMddHHmmss");
        System.out.println("开始时间："+yyyyMMddHHmmss);
        Date date = DateUtil.date();
        System.out.println("结束时间："+date);
        DateTime dateTime = new DateTime(date);

        boolean expired = DateUtil.isExpired(yyyyMMddHHmmss, DateField.MINUTE, 3, dateTime);
        System.out.println(expired);
    }

    @Test
    public void test5(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDxmQr6Uwqxf2DfLyNdDcqsjNo35uXqcZK", "gq9GAsNflkX9eoutgLn24tWwDSUEoCON");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = "xioyan-1251811015";
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = new File("E://123.xlsx");
        // 指定要上传到 COS 上对象键
        // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
        String key = "123";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        System.out.println(putObjectResult);
    }

    @Test
    public void  test6(){
        System.out.println(cosConfigProperties.getRegion());
    }
}
