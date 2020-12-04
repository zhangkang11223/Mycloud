package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/3 15:33
 */
@Slf4j
@RestController
@RequestMapping(value = "/springTest")
public class SpringTestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/getCurrentPort")
    public CommonResult<String> getCurrentPort() {
        return new CommonResult<>(200, "get current port success!", serverPort);
    }
}
