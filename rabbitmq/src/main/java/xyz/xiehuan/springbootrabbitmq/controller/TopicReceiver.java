package xyz.xiehuan.springbootrabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/13
 * Time: 13:04
 * Describe:
 */

@Component
public class TopicReceiver {

/*    @RabbitHandler
    @RabbitListener(queues = "topic.message")
    public void process(String message) {
        System.out.println("Topic Receiver1  : " + message);
    }*/
}
