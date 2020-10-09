package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    // PAYMENT模块是集群部署的时候，URL写该模块的服务名称
    // 必须与负载均衡策略搭配使用(比如：给RestTemplate添加@LoadBalanced注解)
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    // PAYMENT模块是单机的情况下，URL可以为固定值：http://ip:port
    // public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> crate(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+ id, CommonResult.class);
    }


}
