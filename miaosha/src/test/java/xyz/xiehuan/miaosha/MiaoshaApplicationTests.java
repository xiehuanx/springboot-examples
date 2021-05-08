package xyz.xiehuan.miaosha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.xiehuan.miaosha.config.MrssConfiguration;
import xyz.xiehuan.miaosha.dto.DataSource;
import xyz.xiehuan.miaosha.dto.Mrss;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiaoshaApplicationTests {

    @Autowired
    private MrssConfiguration mrssConfiguration;

    @Test
    public void contextLoads() {

/*        Mrss mrss = new Mrss();
        mrss.setIp("121212");
        mrss.setName("demo");
        mrss.setPath("12");
        redisTemplate.opsForValue().set("mrss",mrss);

        Mrss mrss1 = (Mrss) redisTemplate.opsForValue().get("mrss");

        System.out.println(mrss1);*/

        List<Mrss> mrsses = mrssConfiguration.mrssList();

        System.out.println(mrsses);


    }



}
