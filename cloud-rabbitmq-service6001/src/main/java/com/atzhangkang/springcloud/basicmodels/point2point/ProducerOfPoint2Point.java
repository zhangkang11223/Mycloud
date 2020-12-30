package com.atzhangkang.springcloud.basicmodels.point2point;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

/**
 * 生产者 ->  队列  <-  消费者
 * 一个生产者对应一个消费者
 * @author tule
 * @version 1.0
 * @date 2020/12/18
 */
public class ProducerOfPoint2Point {
    @Test
    public void testSendMessage() throws Exception {
        // 通过工具类获取连接对象
        Connection connection = RabbaitMqUtil.getConnection();
        if (connection == null) {
            throw new Exception("failed to get rabbitmq connection");
        }
        // 获取连接中的通道对象
        Channel channel = connection.createChannel();

        // 通道绑定对应的消息队列
        // queue : 队列名称，如果队列在rabbitmq中不存在，就自动创建
        // durable : 队列是否要持久化，配置true，mq重启后该队列依旧存在，不保证消息持久化。配置false，mq重启后删除该队列
        // exclusive : 前连接可用是否独占队列，配置true，该队列只允许当前连接可用，其他连接不可用。配置false，其他连接也可用
        // autoDelete : 消费完成后，是否自动删除队列 true自动删除，false不自动删除。配置true，消费者与队列断开连接才删除
        // arguments : 额外附加参数
        channel.queueDeclare("hello", false, false, false, null);

        // 给指定队列发送消息
        // 参数1 : 交换机名称，因为点对点模式不需要交换机，直接发送消息。该参数为“”,使用rabbitmq提供的默认交换机
        // 参数2 : 要发布消息的队列名称
        // 参数3 : 发布消息时的属性，其中的MessageProperties.PERSISTENT_TEXT_PLAIN : 表示消息持久化到当前消息队列中
        // 参数4 : 要发布的内容，数据类型是字节数组
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello 1112233".getBytes());

        // 关闭连接
        RabbaitMqUtil.closeChannelAndConnection(channel, connection);

    }
}