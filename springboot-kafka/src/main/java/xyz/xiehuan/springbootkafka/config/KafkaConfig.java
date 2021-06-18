package xyz.xiehuan.springbootkafka.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.HashMap;
import java.util.Map;


/**
 * @author XiaoBingBy
 * @since 2020-05-19 18:32
 */
@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public KafkaProducer getProducer(){
        Map<String, Object> stringObjectMap = kafkaProperties.getProducer().buildProperties();
        stringObjectMap.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        stringObjectMap.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 600 * 1000);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(stringObjectMap);
        return producer;
    }

//    @Bean
//    public ProducerConfig getProducerConfig(){
//        Map<String, Object> stringObjectMap = kafkaProperties.getProducer().buildProperties();
//        stringObjectMap.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 600 * 1000);
//        ProducerConfig producerConfig = new ProducerConfig(stringObjectMap);
//        return producerConfig;
//    }

    @Bean
    public KafkaConsumer getConsumer() {
        Map<String, Object> stringObjectMap = kafkaProperties.getConsumer().buildProperties();
        stringObjectMap.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        KafkaConsumer consumer = new KafkaConsumer(stringObjectMap);
        return consumer;
    }

}
