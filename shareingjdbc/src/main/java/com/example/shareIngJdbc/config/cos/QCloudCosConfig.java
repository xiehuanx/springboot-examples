package com.example.shareIngJdbc.config.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/8
 * Time: 13:02
 * Describe:
 */
@Configuration
@EnableConfigurationProperties(CosConfigProperties.class)
public class QCloudCosConfig {

    @Autowired
    private CosConfigProperties cosConfigProperties;

    @Bean
    public COSClient cosClient(){

        COSCredentials cred = new BasicCOSCredentials(cosConfigProperties.getSecretId(),cosConfigProperties.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(cosConfigProperties.getRegion()));
        COSClient cosclient = new COSClient(cred, clientConfig);
        return cosclient;
    }
}
