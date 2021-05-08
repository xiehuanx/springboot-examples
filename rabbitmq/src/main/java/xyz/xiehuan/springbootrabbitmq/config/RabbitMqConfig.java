package xyz.xiehuan.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/13
 * Time: 11:50
 * Describe:
 */
@Configuration
public class RabbitMqConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitMqConfig.message);
    }
    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitMqConfig.messages);
    }

    /**
     * 创建交换器
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 对列绑定并关联到ROUTINGKEY
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 对列绑定并关联到ROUTINGKEY
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
   }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
        }
    }

}
