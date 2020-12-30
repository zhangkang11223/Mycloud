package com.atzhangkang.springcloud.basicmodels.fanout;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/25
 */
public class Consumer3 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbaitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //通道绑定交换机
        channel.exchangeDeclare("logs", "fanout");

        //临时队列
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列
        channel.queueBind(queueName,"logs","");

        //消费消息
        //Ctrl + o 重写当前类的某个方法
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3 : " + new String(body, StandardCharsets.UTF_8));
            }
        });

    }
}
