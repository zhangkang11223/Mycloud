package com.atzhangkang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/31
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosPaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentMain9001.class,args);
    }
}
