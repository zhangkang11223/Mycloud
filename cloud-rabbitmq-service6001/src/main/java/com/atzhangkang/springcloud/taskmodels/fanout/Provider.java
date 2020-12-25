package com.atzhangkang.springcloud.taskmodels.fanout;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/25
 */
public class Provider {
    public static void main(String[] args) throws IOException {

        Connection connection = RabbaitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 将通道声明指定的交换机,若交换机不存在，会创建对应的交换机
         * 参数1 ； 交换机名称
         * 参数2 ： 交换机类型 fanout 广播类型
         */
        channel.exchangeDeclare("logs","fanout");

        //routingKey对于发布订阅模型是没作用的
        channel.basicPublish("logs","",null,"fanout message".getBytes());

        RabbaitMqUtil.closeChannelAndConnection(channel, connection);

    }
}
