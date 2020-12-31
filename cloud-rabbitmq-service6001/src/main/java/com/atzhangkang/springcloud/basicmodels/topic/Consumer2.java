package com.atzhangkang.springcloud.basicmodels.topic;

import com.atzhangkang.springcloud.utils.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/30
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("myTopic", "topic");
        String queue = channel.queueDeclare().getQueue();
        // user.#表示：routingKey以user开头，后面可以有0个或多个单词
        // 例如：user   user.abc  user.abc.def
        channel.queueBind(queue,"myTopic", "user.#");

        channel.basicConsume(queue,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费之2 ： " + new String(body, StandardCharsets.UTF_8));
            }
        });
    }
}
