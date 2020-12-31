package com.atzhangkang.springcloud.test;

import com.atzhangkang.springcloud.RabbitMqMain6001;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 所有的生产者不会创建交换机和队列，均由消费者创建
 * @author tule
 * @version 1.0
 * @date 2020/12/30
 */
@SpringBootTest(classes = RabbitMqMain6001.class)
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //point2point: 点对点模型，例如：Hello World
    @Test
    public void testPoint2point() {
        rabbitTemplate.convertAndSend("hello", "Hello World!");
    }

    // workQueue: 一对多模型，消费者轮训执行发送来的消息
    @Test
    public void testWorkQueue() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "第"+i+"次work queue!");
        }
    }

    // fanout : 广播模型，一对多，所有消费者均执行消息
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs","", " Fanout message!");
    }

    // routing : 路由模式  消费者执行routingKey相同的key
    @Test
    public void testRouting() {
        rabbitTemplate.convertAndSend("my_direct", "error", "发送info的key基于路由信息");
    }

    // topic : 动态路由，订阅模式  消费者执行routingKey能匹配的key

    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("my_topic", "order.123","user.save 路由信息");
    }
}
