package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.entities.Payment;
import com.atzhangkang.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentServiceImpl paymentServiceImpl;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentServiceImpl.create(payment);
        log.info("insert success, id : {}", result );
        if (result > 0) {
            return  new CommonResult(200, "insert success!, server port is : " +serverPort, result);
        }
        return new CommonResult(400, "failed to insert data, server port is : " +serverPort);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id ){
        Payment payment = paymentServiceImpl.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "get data success!, server port is : " +serverPort, payment);
        }

        return new CommonResult(400, "failed to get data, server port is : " +serverPort);
    }
}