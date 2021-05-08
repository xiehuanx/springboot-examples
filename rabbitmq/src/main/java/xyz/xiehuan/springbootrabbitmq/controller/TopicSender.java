package xyz.xiehuan.springbootrabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/13
 * Time: 13:02
 * Describe:
 */
@RestController
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/send")
    public void send() {
        String context = "hi, i am message all";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("topicExchange", "topic.1", context);
    }
    @RequestMapping("/send1")
    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("topicExchange", "topic.message", context);
    }
    @RequestMapping("/send2")
    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("topicExchange", "topic.messages", context);
    }

    @RequestMapping("/demo")
    public String demo(){
        return "23232";
    }
}
