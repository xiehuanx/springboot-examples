package com.example.shareIngJdbc.config.cos;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/8
 * Time: 15:42
 * Describe:
 */
@ConfigurationProperties(prefix = "qcloud")
public class CosConfigProperties {

    /**
     * enable
     */
    private boolean enable;

    /**
     * APPID 是腾讯云账户的账户标识之一
     */
    private long appId;

    /**
     * SecretId & SecretKey 合称为云 API 密钥
     * SecretId 用于标识 API 调用者身份
     */
    private String secretId;

    /**
     * SecretId & SecretKey 合称为云 API 密钥
     * SecretKey 是用于加密签名字符串和服务器端验证签名字符串的密钥
     */
    private String secretKey;

    /**
     * 设置bucket所在的区域，比如广州(gz), 天津(tj)
     */
    private String region;

    /**
     * Bucket 名称
     */
    private String bucketName;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
