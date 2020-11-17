package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.service.SimpleTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/simpleTest")
public class SimpleTestController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private SimpleTestService simpleTestService;

    @GetMapping(value = "/getCurrentPort")
    public CommonResult<String> getCurrentPort() throws InterruptedException {
        System.out.println("start 0");
        //throw new Exception("1111111");
        simpleTestService.testException();
        System.out.println("start 1");
        return new CommonResult<>(200, "get current port success!", serverPort);
    }

    @GetMapping(value = "/getMyPort")
    public CommonResult<String> getMyPort() throws Exception {
        throw new Exception("asdasdasd");
        //return new CommonResult<>(200, "get current port success!", serverPort);
    }
}