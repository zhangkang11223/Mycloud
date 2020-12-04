package com.atzhangkang.springcloud.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/4
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    /**
     * @LogAnnotation注解切点
     */
    @Pointcut("@annotation(com.atzhangkang.springcloud.log.LogAnnotation)")
    public void logAnnotationAspect(){
    }



    /**
     * 前置通知: 用于拦截@LogAnnotation注解，记录用户的前置操作
     */
    @Before("logAnnotationAspect()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            // ========控制台输出=========
            log.info("==============前置通知开始==============");
            log.info("开始执行方法：" + (joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName() + "()"));
            log.info("方法详细描述：" + getDescriptionFromAnnotation(joinPoint));
            log.info("开始执行时间：" + Instant.now());
            log.info("==============前置通知结束==============");
        } catch (Exception e) {
            // 记录本地异常日志
            log.error("==============前置通知异常==============");
            log.error("前置通知异常信息：{}", e.getMessage());
        }
    }

    /**
     * 后置通知: 用于拦截@LogAnnotation注解，记录用户的后置操作
     */
    @AfterThrowing("logAnnotationAspect()")
    public void doAfter(JoinPoint joinPoint) {
        try {
            log.info("==============后置通知开始==============");
            log.info("方法执行完成：" + (joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName() + "()"));
            log.info("执行完成时间：" + Instant.now());
            log.info("==============后置通知结束==============");
        } catch (Exception e) {
            // 记录本地异常日志
            log.error("==============后置通知异常==============");
            log.error("后置异常信息：{}", e.getMessage());
        }
    }



    /**
     *  用于Controller层注解中对方法的描述信息
     */
    private String getDescriptionFromAnnotation(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        // 目标方法名
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(LogAnnotation.class).operateType();
                    break;
                }
            }
        }
        return description;
    }
}
