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
public class ConsumerFanout {

    /**
     * consumer_1
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建一个临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")  // 绑定的交换机
            )
    })
    public void receive1(String message) {
        System.out.println("consumer1 : " + message);
    }


    /**
     * consumer_2
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建一个临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")  // 绑定的交换机
            )
    })
    public void receive2(String message) {
        System.out.println("consumer2 : " + message);
    }
}
