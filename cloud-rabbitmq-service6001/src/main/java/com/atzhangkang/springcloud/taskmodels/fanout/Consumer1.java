package com.atzhangkang.springcloud.taskmodels.fanout;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/25
 */
public class Consumer1 {
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

    }
}
