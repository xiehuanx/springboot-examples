package xyz.xiehuan.springbootkafka.thread;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * author :xiehuan
 * Email: 1487471733@qq.com
 * Date: 2021/6/11
 * Describe:
 */
public class ConsumerRunnable implements Runnable{


    private final KafkaConsumer<String, String> consumer;

    private final String threadName = Thread.currentThread().getName();

    public ConsumerRunnable(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }


    @Override
    public void run() {

        System.out.println(System.currentTimeMillis() + " " + consumer.groupMetadata().groupId() + " " + threadName);
        consumer.assignment().forEach(e -> System.out.println(e.topic() + " 测试"));
    }
}
