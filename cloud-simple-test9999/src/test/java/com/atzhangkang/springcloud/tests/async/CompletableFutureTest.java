package com.atzhangkang.springcloud.tests.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * runAsync方法不支持返回值。
 * supplyAsync可以支持返回值。
 */
public class CompletableFutureTest {
    // runAsync无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });

        future.get();
    }

    // supplyAsync有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        // 返回值time
        long time = future.get();
        System.out.println("time = "+time);
    }
}
