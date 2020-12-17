package com.xxl.job.core.handler.impl;

import com.xxl.job.core.handler.IJobHandler;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author xuxueli 2019-12-11 21:12:18
 */
public class MethodJobHandler extends IJobHandler {

    private final Object target;
    private final Method method;
    private Method initMethod;
    private Method destroyMethod;

    public MethodJobHandler(Object target, Method method, Method initMethod, Method destroyMethod) {
        this.target = target;
        this.method = method;

        this.initMethod = initMethod;
        this.destroyMethod = destroyMethod;
    }

    @Override
    public void execute() throws Exception {

        var zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm:ss", Locale.CHINA);
        System.out.println(zhFormatter.format(ZonedDateTime.now()) + Thread.currentThread().getName() + "=============execute  start ===========");
        method.invoke(target);
        System.out.println(zhFormatter.format(ZonedDateTime.now()) + Thread.currentThread().getName() + "=============execute   end  ===========");

    }

    @Override
    public void init() throws Exception {
        if(initMethod != null) {
            initMethod.invoke(target);
        }
    }

    @Override
    public void destroy() throws Exception {
        if(destroyMethod != null) {
            destroyMethod.invoke(target);
        }
    }

    @Override
    public String toString() {
        return super.toString()+"["+ target.getClass() + "#" + method.getName() +"]";
    }
}
