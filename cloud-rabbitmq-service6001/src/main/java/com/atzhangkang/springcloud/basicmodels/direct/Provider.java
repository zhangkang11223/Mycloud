package com.atzhangkang.springcloud.basicmodels.direct;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 动态路由模式，可以根据通配符来完成routingKey的适配
 *                                                   消息队列    <——            消费者1
 *            生产者  ——>            交换机      ——>
 *                                                   消息队列    <——            消费者2
 *   生产携带routingKey的消息      (type :direct)                    消费者根据routingKeys是否包消息中的routingKey
 *
 * @author tule
 * @version 1.0
 * @date 2020/12/30
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbaitMqUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();

        /**
         * 声明交换机
         * 参数1： 交换机名称
         * 餐数2： 交换机类型
         * */
        channel.exchangeDeclare("logs_direct", "direct");
        // 定义消息
        String routingKey = "error";

        channel.basicPublish("logs_direct", routingKey,
                null, ("这是direct模型发布的基于routingKey : ["+routingKey+"] 发送的消息！").getBytes());

        //关闭资源
        RabbaitMqUtil.closeChannelAndConnection(channel, connection);
    }
}
