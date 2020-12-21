package com.atzhangkang.springcloud.taskmodels.workqueues;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/21
 */
public class ProducerOfWorkQueues {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbaitMqUtil.getConnection();
        // 获取通道对象
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare("work", true, false, false, null);

        for(int i = 0; i < 10 ; i++) {
            //生产消息队列
            channel.basicPublish("","work", null, (i+"hello work queues").getBytes());
        }

        // 关闭资源
        RabbaitMqUtil.closeChannelAndConnection(channel, connection);
    }
}
