package com.atzhangkang.springcloud.service;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author tule
 * @version 1.0
 * @date 2020/12/3
 */
@Service
@Slf4j
public class CatchCallableExceptionService {

    /**
     * 测试线程池中捕获callable线程抛出的异常
     */
    public void testCatchCallableException() {

        Callable<Double> callable = () -> {
            log.info(Thread.currentThread().getName() + " : enter callable!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " : try catch execute end!");
            throw new Exception("token expired");
            // return Math.random();
        };
        // 以原子读写的对象引用变量
        AtomicReference<String> exceptionStr = new AtomicReference<>("");
        ThreadFactoryBuilder myPool = new ThreadFactoryBuilder().setNamePrefix("myPool");
        myPool.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            exceptionStr.set(e.getMessage());
        });
        ThreadFactory namedThreadFactory = myPool.build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), namedThreadFactory,
                //丢弃任务并抛出RejectedExecutionException异常
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++ ) {
            Future<Double> submit = singleThreadPool.submit(callable);
            //get()会阻塞后续代码
            //System.out.println(submit.get());
        }
        // 正在运行的线程不会立即关闭，等待中的线程不会再执行
        singleThreadPool.shutdown();
    }
}
