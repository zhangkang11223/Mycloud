package com.atzhangkang.springcloud.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/21
 */
public class RabbitMqUtil {

    private static ConnectionFactory connectionFactory;

    static {
        // 创建mq的连接工厂，该工厂属于重量级工厂，类加载的时候只创建一次
        connectionFactory = new ConnectionFactory();
        // 设置连接rabbitmq的主机
        connectionFactory.setHost("192.168.1.44");
        // 设置端口号,rabbitmq做tcp通信的时候使用5672端口号
        connectionFactory.setPort(5672);
        // 设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        // 设置访问虚拟主机的用户名密码
        connectionFactory.setUsername("zk");
        connectionFactory.setPassword("123");
    }

    /**
     * 定义提供连接对象的方法
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭通道和连接
     */
    public static void closeChannelAndConnection(Channel channel, Connection connection){
        try {
            // 关闭通道
            if (channel != null) {
                channel.close();
            }
            // 关闭连接
            if (connection != null) {
                connection.close();
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
