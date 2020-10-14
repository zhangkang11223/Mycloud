package com.atzhangkang.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

public interface LoadBalancer {

    ServiceInstance getInstance();
}
