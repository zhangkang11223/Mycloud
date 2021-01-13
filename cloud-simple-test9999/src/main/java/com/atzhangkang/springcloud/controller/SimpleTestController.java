package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.service.CatchCallableExceptionService;
import com.atzhangkang.springcloud.service.HeaderService;
import com.atzhangkang.springcloud.service.ThirdPartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/3
 */

@Slf4j
@RestController
@RequestMapping(value = "/simpleTest")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SimpleTestController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 获取到request
     */
    private final HttpServletRequest request;

    private final CatchCallableExceptionService simpleTestService;

    private final HeaderService headerService;

    private final ThirdPartService thirdPartService;

    /**
     * 获取端口号
     */
    @GetMapping(value = "/getCurrentPort")
    public CommonResult<String> getCurrentPort() {
        return new CommonResult<>(200, "get current port success!", serverPort);
    }

    /**
     * 获取请求的Headers信息
     */
    @GetMapping(value = "/getHeaderInfo")
    public CommonResult<String> getHeaderInfo() {
        headerService.getHeaderInfo(request);
        return new CommonResult<>(200, "get header info  success!");
    }

    /**
     *  调用第三方get请求
     */
    @GetMapping(value = "/sendGetRequest")
    public CommonResult<Integer> sendGetRequest(
        @RequestParam( value = "userName") String userName,
        @RequestParam(value = "portalToken") String portalToken) throws Exception {
        Integer userId = thirdPartService.sendGetRequest(portalToken, userName);
        return new CommonResult<>(200, "send request success!", userId);
    }

    /**
     *  调用第三方post请求
     */
    @GetMapping(value = "/sendPostRequest")
    public CommonResult<String> sendPostRequest(
            @RequestParam( value = "userName") String userName,
            @RequestParam(value = "portalToken") String portalToken,
            @RequestParam(value = "pageSize") Integer pageSize) throws Exception {
        String resultBody = thirdPartService.sendPostRequest(portalToken, userName, pageSize);
        return new CommonResult<>(200, "send request success!", resultBody);
    }

    /**
     * 测试线程池中捕获callable线程抛出的异常
     */
    @GetMapping(value = "/testCatchCallableException")
    public void testCatchCallableException() {
        simpleTestService.testCatchCallableException();
    }
}