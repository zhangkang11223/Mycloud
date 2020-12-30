package com.atzhangkang.springcloud.consumer2spring;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/30
 */
@Component
public class ConsumerRouting {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,      // 创建临时队列
                    exchange = @Exchange(value = "my_direct", type = "direct"), // 创建交换机
                    key = {"info", "error", "warning"}     // 创建routeKeys
            )
    })
    public void receive1(String message) {
        System.out.println("consumer_1 : " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,      // 创建临时队列
                    exchange = @Exchange(value = "my_direct", type = "direct"), // 创建交换机
                    key = {"error"}     // 创建routeKeys
            )
    })
    public void receive2(String message) {
        System.out.println("consumer_2 : " + message);
    }
}
