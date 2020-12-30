package com.atzhangkang.springcloud.consumer2spring;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @RabbitListener表示这个类监听了一个消息队列
 * 参数queuesToDeclare如果这个队列不存在就生成一个队列
 * @Queue的默认值 持久化 非独占 不自动删除的队列
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello", durable = "true", autoDelete = "true"))
public class ConsumerForP2P {

    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("message============== : " + message);
    }
}
