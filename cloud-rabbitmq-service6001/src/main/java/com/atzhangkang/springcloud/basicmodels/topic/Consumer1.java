package com.atzhangkang.springcloud.basicmodels.topic;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/30
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbaitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("myTopic", "topic");
        String queue = channel.queueDeclare().getQueue();
        // routingKey有且仅有2个单词，以user开头，第二个单词可以是任意不为空
        // 例如： user.save  user.abv
        // 错误: user     user.abc.def
        channel.queueBind(queue,"myTopic", "user.*");

        channel.basicConsume(queue,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费之1 ： " + new String(body, StandardCharsets.UTF_8));
            }
        });
    }
}
