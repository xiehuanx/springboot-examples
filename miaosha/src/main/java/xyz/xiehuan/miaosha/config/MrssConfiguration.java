package xyz.xiehuan.miaosha.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import xyz.xiehuan.miaosha.dto.Mrss;
import xyz.xiehuan.miaosha.dto.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/30
 * Time: 20:07
 * Describe:
 */
@Configuration
public class MrssConfiguration implements EnvironmentAware {

    List<Mrss> mrssList=new ArrayList<>();

    public List<Mrss> mrssList(){

        return mrssList;
    }

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "mrss.";
        String names = environment.getProperty(prefix + "names");
        for(String each :names.split(",")) {
            Mrss mrss = Binder.get(environment).bind("mrss." + each, Mrss.class).orElse(null);
            mrssList.add(mrss);
        }
    }


}
