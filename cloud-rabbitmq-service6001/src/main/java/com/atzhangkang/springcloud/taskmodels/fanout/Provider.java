package com.atzhangkang.springcloud.taskmodels.fanout;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 *                   —>  队列1  <-  消费者1
 * 生产者  —>  交换机
 *                   ->  队列2  <-  消费者2
 *
 *                   ......
 * 这里的消费者对应的队列是临时队列
 * 广播模型的交换机会给所有和它绑定的队列发送消息
 * 广播模型常用于注册业务： 当注册成功后，生产一个消息，发给交换机，交换机决定把消息发给哪些队列
 * 由消费者：发送邮件消费者，添加积分消费者，发短信通知消费者...等等不同的消费者执行不同的业务操作
 * @author tule
 * @version 1.0
 * @date 2020/12/25
 */
public class Provider {
    public static void main(String[] args) throws IOException {

        Connection connection = RabbaitMqUtil.getConnection();
        // 生产者通过通道把消息发送给交换机
        Channel channel = connection.createChannel();

        /**
         * 将通道声明指定的交换机,若交换机不存在，会创建对应的交换机
         * 参数1 ； 交换机名称
         * 参数2 ： 交换机类型 fanout 广播类型，是写死的
         */
        channel.exchangeDeclare("logs","fanout");

        //routingKey对于发布订阅模型是没作用的
        channel.basicPublish("logs","",null,"fanout message".getBytes());

        RabbaitMqUtil.closeChannelAndConnection(channel, connection);

    }
}
