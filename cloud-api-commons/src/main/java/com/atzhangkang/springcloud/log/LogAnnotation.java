package com.atzhangkang.springcloud.log;

import java.lang.annotation.*;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/4
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    //作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME)                     // 运行时可见
@Documented                                             //表明这个注解应该被 javadoc工具记录
public @interface LogAnnotation {

    /**
     * 记录日志的操作类型
     */
    String operateType();
}
