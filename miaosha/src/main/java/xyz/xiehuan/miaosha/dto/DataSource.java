package xyz.xiehuan.miaosha.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/31
 * Time: 9:56
 * Describe:
 */
@Data
public class DataSource {

    private String url;

    private String driverClassName;

    private String username;

    private String password;



}
