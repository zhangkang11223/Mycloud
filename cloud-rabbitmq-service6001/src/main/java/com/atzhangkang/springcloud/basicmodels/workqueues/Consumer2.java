package com.atzhangkang.springcloud.basicmodels.workqueues;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/21
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbaitMqUtil.getConnection();
        if (connection == null) {
            throw new Exception("failed to get rabbitmq connection");
        }
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只能消费一个消息
        channel.queueDeclare("work", true, false, false, null);
        /**
         * 参数1：队列名称
         * 参数2： 自动确认：true表示消费者自动向mq确认消息已被消费，消息队列会把消息给consumer,若此时consumer宕机，消费者还未消费的消息就丢失了
         *        因此不建议使用自动确认，可能会造成消息丢失。
         *        false不会确认已被消费，也就是说，即使这个消息被消费了，也不会从消息队列中删除
         */
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费之_2 : " + new String(body, StandardCharsets.UTF_8));
                /**
                 * 参数1：确认队列中那个具体消息
                 * 参数2： 是否开启多消同时息确认
                 */
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
