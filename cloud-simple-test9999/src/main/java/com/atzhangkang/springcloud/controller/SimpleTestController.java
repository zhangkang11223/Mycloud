package com.atzhangkang.springcloud.controller;

import com.atzhangkang.springcloud.entities.CommonResult;
import com.atzhangkang.springcloud.service.CatchCallableExceptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * 这里可以获取到request
     */
    private final HttpServletRequest request;

    private final CatchCallableExceptionService simpleTestService;


    @GetMapping(value = "/getCurrentPort")
    public CommonResult<String> getCurrentPort() {
        return new CommonResult<>(200, "get current port success!", serverPort);
    }

    @GetMapping(value = "/getHeaderInfo")
    public CommonResult<String> getHeaderInfo() {
        //******************获取cookies信息************************************
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                System.out.println(cookie.getName() + " " + cookie.getValue());
            }
        }

        //******************获取请求头(header)信息*****************************
        Enumeration<String> headerNames = request.getHeaderNames();
        // 判断是否还有下一个元素
        while(headerNames.hasMoreElements()) {
            // 获取headerNames集合中的请求头
            String nextElement = headerNames.nextElement();
            // 通过请求头得到请求内容
            String header2 = request.getHeader(nextElement);
            System.out.println("请求头=========={}" + nextElement + "VALUE:" + header2);
        }

        //******************获取请求头(header)信息*****************************
        String token = request.getHeader("cookie");
        System.out.println(token);

        return new CommonResult<>(200, "get header info  success!");
    }


    /**
     * 测试线程池中捕获callable线程抛出的异常
     */
    @GetMapping(value = "/testCatchCallableException")
    public void testCatchCallableException() {
        simpleTestService.testCatchCallableException();
    }
}