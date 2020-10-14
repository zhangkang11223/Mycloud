package com.atzhangkang.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    @Resource
    private  DiscoveryClient discoveryClient;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            //当入参current和atomicInteger的当前值相等，返回true
        } while (!atomicInteger.compareAndSet(current, next)); //while为false跳出循环
        return next;
    }

    /**
     * 自定义轮训算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器的下标
     * 每次重启服务或请求数达到int的最大值，rest接口计数从0开始
     */
    @Override
    public ServiceInstance getInstance() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("ClOUD-PAYMENT-SERVICE");
        int i = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(i);
    }
}
