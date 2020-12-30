package com.atzhangkang.springcloud.basicmodels.direct;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
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
        Connection connection = RabbaitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机与生产者保持一致
        channel.exchangeDeclare("logs_direct", "direct");

        //创建临时队列
        String queue = channel.queueDeclare().getQueue();

        //基于routeKey绑定交换机和队列,可基于业务规则绑定多个
        channel.queueBind(queue, "logs_direct", "error");
        channel.queueBind(queue, "logs_direct", "info");
        channel.queueBind(queue, "logs_direct", "warning");

        //获取消费的消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者2 ：" + new String(body, StandardCharsets.UTF_8));
            }
        });
    }
}
