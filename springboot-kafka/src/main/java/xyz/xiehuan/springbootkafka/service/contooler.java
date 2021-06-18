package xyz.xiehuan.springbootkafka.service;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.xiehuan.springbootkafka.thread.ConsumerGroup;

import javax.annotation.PostConstruct;
import java.util.Collections;

/**
 * author :xiehuan
 * Email: 1487471733@qq.com
 * Date: 2021/6/2
 * Describe:
 */
@RestController
public class contooler {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired(required = false)
    private KafkaProducer<String, Object> kafkaProducer;

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    // 发送消息
    @GetMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
    }

    @GetMapping("/kafka/transaction")
    public void sendMessage7(){
        // 声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations -> {
            return operations.send("topic1","test executeInTransaction");
            //throw new RuntimeException("fail");
        });

        kafkaProducer.beginTransaction();

        kafkaProducer.commitTransaction();

        kafkaProducer.close();

        kafkaProducer.abortTransaction();

        try {

            //kafkaProducer.se

        }catch (Exception e) {
            kafkaProducer.close();
        }



        // 不声明事务：后面报错但前面消息已经发送成功了
        //kafkaTemplate.send("topic1","test executeInTransaction");
        throw new RuntimeException("fail");
    }

    @PostConstruct
    public void demo() {
//        kafkaConsumer.subscribe(Collections.singletonList("topic1"));
//        ConsumerGroup consumerGroup = new ConsumerGroup(4, kafkaConsumer);
//        consumerGroup.execute();
    }

}
