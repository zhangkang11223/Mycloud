package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.log.LogAnnotation;
import com.atzhangkang.springcloud.server.SpringTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/3
 */
@Slf4j
@RestController
@RequestMapping(value = "/springTest")
public class SpringTestController {

    @Value("${server.port}")
    private String serverPort;

    private final SpringTestService springTestService;

    public SpringTestController(SpringTestService springTestService) {
        this.springTestService = springTestService;
    }

    @GetMapping(value = "/getCurrentPort")
    public CommonResult<String> getCurrentPort() {
        return new CommonResult<>(200, "get current port success!", serverPort);
    }
    
    @LogAnnotation(operateType = "测试日志插入")
    @GetMapping(value = "/testLogInsert")
    public CommonResult<String> testLogInsert() {
        log.info("will execute the method testLogInsert");

        springTestService.doSomeThing();

        log.info("finished execute the method testLogInsert");
        return new CommonResult<>(200, "nothing happened!");
    }
}
