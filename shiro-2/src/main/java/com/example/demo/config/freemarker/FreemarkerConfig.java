package com.example.demo.config.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Author: xie
 * Email: 1487471733@qq.com
 * Date: 2017/9/23
 * Time: 15:18
 * Describe:
 */
@Configuration
public class FreemarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("shiro", new ShiroTags());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
