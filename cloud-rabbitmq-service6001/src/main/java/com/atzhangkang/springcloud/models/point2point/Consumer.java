package com.atzhangkang.springcloud.models.point2point;

import com.atzhangkang.springcloud.utils.RabbaitMqUtil;
import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/21
 */
public class Consumer {

    /**
     * consumer端一直消费消费者端发送过来的消息
     */
    public static void main(String[] args) throws Exception {
        //通过工具类获取连接对象
        Connection connection = RabbaitMqUtil.getConnection();
        if (connection == null) {
            throw new Exception("failed to get rabbitmq connection");
        }
        // 获取连接中的通道对象
        Channel channel = connection.createChannel();

        /**
         * 对于queueDeclare()方法中的参数
         * consumer端要和provider端保持一直
         */
        channel.queueDeclare("hello", false, false, false, null);

        /**
         * 消费消息
         * 参数1 : 消费那个队列的消息 消息名称
         * 参数2 : 开始消息的自动确认机制
         * 参数3 : 消费回调接口
         */

        channel.basicConsume("hello", true, new DefaultConsumer(channel){

            /**
             *
             * @param consumerTag 消费标签
             * @param envelope 消息传递过程中信封
             * @param properties 消息传递过程中属性
             * @param body 消息队列中取出的消息
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("zk : " +  new String(body, StandardCharsets.UTF_8));
            }
        });

        // 不建议关闭通道和连接，因为关闭会导致主线程结束，没来及处理回调就已经退出了
        //channel.close();
        // 关闭连接
        //connection.close();
    }
}
