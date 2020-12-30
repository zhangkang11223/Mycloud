package com.atzhangkang.springcloud.basicmodels.workqueues;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 *                 <- 消费者1
 * 生产者  ->  队列
 *                 <- 消费者2
 *
 * 一个生产者，多个消费者
 * Workqueue中的consumers 默认是轮循获取消息队列中的消息，可通过修改实现“按劳分配”
 * @author tule
 * @version 1.0
 * @date 2020/12/21
 */
public class ProducerOfWorkQueues {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbaitMqUtil.getConnection();
        if (connection == null) {
            throw new Exception("failed to get rabbitmq connection");
        }
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
