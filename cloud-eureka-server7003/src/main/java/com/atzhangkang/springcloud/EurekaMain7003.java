package com.atzhangkang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaMain7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7003.class,args);
    }
}

//访问http://eureka7003.com:7003/
