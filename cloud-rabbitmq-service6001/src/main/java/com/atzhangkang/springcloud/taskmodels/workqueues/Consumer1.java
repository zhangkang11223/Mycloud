package com.atzhangkang.springcloud.taskmodels.workqueues;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/21
 */
public class Consumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbaitMqUtil.getConnection();
        if (connection == null) {
            throw new Exception("failed to get rabbitmq connection");
        }
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费之_1 : " + new String(body, StandardCharsets.UTF_8));
            }
        });
    }
}