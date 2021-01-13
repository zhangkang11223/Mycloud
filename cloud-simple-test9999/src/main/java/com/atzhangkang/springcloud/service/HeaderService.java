package com.atzhangkang.springcloud.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/13
 */
public class HeaderService {
    public void  getHeaderInfo(HttpServletRequest request) {
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
    }
}
