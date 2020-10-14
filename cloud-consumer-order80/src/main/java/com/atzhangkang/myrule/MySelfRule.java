package com.atzhangkang.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载轮训规则
 * 这里的轮训规则类不能与启动类OrderMain80同包，因为：同包会被@CpnpomentScan注解扫描到
 * 导致我们自定义的类会被所有的Ribbon客户端所共享，达不到特殊化的目的
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        /**
         * return new RoundRobinRule(); //轮询
         * return new RetryRule(); //先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试
         * return new WeightedResponseTimeRule (); //对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
         * return new BestAvailableRule (); //会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
         * return new AvailabilityFilteringRule (); //先过滤掉故障实例，再选择并发较小的实例
         * return new ZoneAvoidanceRule(); //默认规则，复合判断server所在区域的性能和server的可用性选择服务器
         */
        return new RandomRule(); //随机
    }
}
