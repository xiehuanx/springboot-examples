package xyz.xiehuan.springbootkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;
import org.apache.zookeeper.server.quorum.ObserverZooKeeperServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class SpringbootKafkaApplicationTests {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired(required = false)
    private KafkaProducer<String, String> kafkaProducer;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private KafkaConsumer<String, Object> kafkaConsumer;


    @Test
    void contextLoads() throws ExecutionException, InterruptedException {

       // kafkaTemplate.b

//        SendResult sender = (SendResult)kafkaTemplate.send("topic1", "ceshi", "测试测试").get();
//        ProducerRecord producerRecord = sender.getProducerRecord();
//        System.out.println(sender);
        //applicationContext.getBean("111");
        ProducerRecord<String, String> stringStringProducerRecord = new ProducerRecord<>("topic1", "1212121");
        kafkaProducer.send(stringStringProducerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {

            }
        });

        try {

            while (true) {
                ConsumerRecords<String, Object> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(10));
                for (ConsumerRecord<String, Object> consumerRecord : consumerRecords) {
                    Headers headers = consumerRecord.headers();
                    int partition = consumerRecord.partition();
                    String key = consumerRecord.key();
                    long offset = consumerRecord.offset();
                    String topic = consumerRecord.topic();
                    Object value = consumerRecord.value();

                    System.out.println("");

                }
                kafkaConsumer.commitAsync();
            }
        }catch (Exception e) {

        }finally {
            kafkaConsumer.commitSync();
        }



    }

}
