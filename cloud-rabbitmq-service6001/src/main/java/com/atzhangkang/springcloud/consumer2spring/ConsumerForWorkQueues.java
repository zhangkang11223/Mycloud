package com.atzhangkang.springcloud.consumer2spring;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/30
 */

@Component
public class ConsumerForWorkQueues {
    /**
     * RabbitListener注解写在方法上，不需要再RabbitHandle
     * 消费者_1
     */
    @RabbitListener(queuesToDeclare = @Queue(value ="work"))
    public void receive1(String message) {
        System.out.println("消费者_1" + message);
    }

    /**
     * 消费者_2
     */
    @RabbitListener(queuesToDeclare = @Queue(value ="work"))
    public void receive2(String message) {
        System.out.println("消费者_2" + message);
    }
}
