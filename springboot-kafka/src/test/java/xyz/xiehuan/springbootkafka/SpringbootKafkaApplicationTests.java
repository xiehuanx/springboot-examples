package xyz.xiehuan.springbootkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;
import org.apache.zookeeper.server.quorum.ObserverZooKeeperServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.unit.DataSize;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
class SpringbootKafkaApplicationTests {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired(required = false)
    private KafkaProducer<String, String> kafkaProducer;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private  KafkaProperties kafkaProperties;

//    @Autowired
//    private ProducerConfig producerConfig;

    @Autowired
    private KafkaConsumer<String, Object> kafkaConsumer;


    @Test
    void contextLoads() throws ExecutionException, InterruptedException {


        DataSize batchSize = kafkaProperties.getProducer().getBatchSize();

        //Long producerConfigLong = producerConfig.getLong(ProducerConfig.MAX_BLOCK_MS_CONFIG);

        System.out.println(batchSize);


        // kafkaTemplate.b

//        SendResult sender = (SendResult)kafkaTemplate.send("topic1", "ceshi", "测试测试").get();
//        ProducerRecord producerRecord = sender.getProducerRecord();
//        System.out.println(sender);
        //applicationContext.getBean("111");
        ProducerRecord<String, String> stringStringProducerRecord = new ProducerRecord<>("demo2", 1,"1","ce");
        kafkaProducer.send(stringStringProducerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {

            }
        });

        ProducerRecord<String, String> stringStringProducerRecord2 = new ProducerRecord<>("demo2", 1,"6","ce111");
        kafkaProducer.send(stringStringProducerRecord2);


        ProducerRecord<String, String> stringStringProducerRecord3 = new ProducerRecord<>("demo2", 1,"11","ce111");
        kafkaProducer.send(stringStringProducerRecord3);

        ProducerRecord<String, String> stringStringProducerRecord4 = new ProducerRecord<>("demo2", 1,"16","ce111");
        kafkaProducer.send(stringStringProducerRecord4);

        ProducerRecord<String, String> stringStringProducerRecord5 = new ProducerRecord<>("demo2", 1,"21","ce111");
        kafkaProducer.send(stringStringProducerRecord5);

        ProducerRecord<String, String> stringStringProducerRecord6 = new ProducerRecord<>("demo2", 1,"26","ce111");
        kafkaProducer.send(stringStringProducerRecord6);

    }

}
