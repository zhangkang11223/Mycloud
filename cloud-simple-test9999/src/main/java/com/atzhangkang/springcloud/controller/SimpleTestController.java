package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.service.CatchCallableExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/3 15:33
 */

@Slf4j
@RestController
@RequestMapping(value = "/simpleTest")
public class SimpleTestController {

    @Value("${server.port}")
    private String serverPort;

    private final CatchCallableExceptionService simpleTestService;

    public SimpleTestController(CatchCallableExceptionService simpleTestService) {
        this.simpleTestService = simpleTestService;
    }

    @GetMapping(value = "/getCurrentPort")
    public CommonResult<String> getCurrentPort() {
        return new CommonResult<>(200, "get current port success!", serverPort);
    }

    /**
     * 测试线程池中捕获callable线程抛出的异常
     */
    @GetMapping(value = "/testCatchCallableException")
    public void testCatchCallableException() {
        simpleTestService.testCatchCallableException();
    }
}