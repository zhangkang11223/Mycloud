package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/simpleTest")
public class SimpleTestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/getCurrentPort")
    public CommonResult<String> getCurrentPort() {
        return new CommonResult<>(200, "get current port success!", serverPort);
    }
}