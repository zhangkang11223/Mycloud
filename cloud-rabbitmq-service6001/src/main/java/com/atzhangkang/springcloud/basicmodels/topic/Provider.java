package com.atzhangkang.springcloud.basicmodels.topic;

import com.atzhangkang.springcloud.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 动态路由模式，可以根据通配符来完成routingKey的适配
 *                                                   消息队列    <——            消费者1
 *            生产者  ——>            交换机      ——>
 *                                                   消息队列    <——            消费者2
 *   生产携带routingKey的消息      (type :topic)                        消费者根据通配符来完成routingKey的适配
 *
 * @author tule
 * @version 1.0
 * @date 2020/12/30
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("myTopic", "topic");

        String routingKey = "use";

        channel.basicPublish("myTopic", routingKey, null,
                ("topic 动态路由模型, routingKey : [" +routingKey+"]").getBytes());

        RabbitMqUtil.closeChannelAndConnection(channel, connection);
    }
}
