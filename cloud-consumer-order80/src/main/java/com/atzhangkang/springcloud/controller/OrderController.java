package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.entities.Payment;
import com.atzhangkang.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

@RestController
@Slf4j
public class OrderController {
   /**
    * 当PAYMENT模块是集群部署的时候，URL写该模块的服务名称，必须与负载均衡策略搭配使用
    * 比如：在配置类ApplicationContextConfig中,给RestTemplate添加@LoadBalanced注解
    */
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    /**
     * 当PAYMENT模块是单机的情况下，URL可以为固定值：http://ip:port
     */
    //public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> crate(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+ id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/discovery")
    public CommonResult<Payment> discovery() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/discovery", CommonResult.class);
    }


    @GetMapping(value = "/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable("id") Long id) {
        // getForEntity和postForEntity可以获得一些http请求的表头信息
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        } else {
            return new CommonResult<>(400,"failed to excute getForEntity");
        }
    }

    @GetMapping(value = "/consumer/payment/postForEntity/create")
    public CommonResult<Payment> postForEntity(Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        } else {
            return new CommonResult<>(400,"failed to excute postForEntity");
        }
    }

    /**
     * 手写负载均衡轮训规则
     */
    @GetMapping(value = "/consumer/payment/getCurrentPort")
    public CommonResult getCurrentPort() {
        ServiceInstance instance = loadBalancer.getInstance();
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/getCurrentPort", CommonResult.class);
    }
}